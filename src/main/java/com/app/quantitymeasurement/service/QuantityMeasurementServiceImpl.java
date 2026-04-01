package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.dto.QuantityDTO;
import com.app.quantitymeasurement.dto.QuantityMeasurementEntity;
import com.app.quantitymeasurement.dto.QuantityModel;
import com.app.quantitymeasurement.entity.QuantityMeasurementDTO;
import com.app.quantitymeasurement.exception.*;
import com.app.quantitymeasurement.quantity.Quantity;
import com.app.quantitymeasurement.repository.QuantityMeasurementRepository;
import com.app.quantitymeasurement.unit.*;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

	private final QuantityMeasurementRepository repository;

	public QuantityMeasurementServiceImpl(QuantityMeasurementRepository repository) {
		this.repository = repository;
	}

	private enum Operation {
		ADD, SUBTRACT, MULTIPLY, DIVIDE, COMPARE, CONVERT;
	}

	@Override
	public QuantityMeasurementDTO compare(QuantityDTO thisDTO, QuantityDTO thatDTO) {
		QuantityModel<IMeasurable> m1 = mapToModel(thisDTO);
		QuantityModel<IMeasurable> m2 = mapToModel(thatDTO);
		validateModels(m1, m2);

		Quantity<IMeasurable> q1 = new Quantity<>(m1.getValue(), m1.getUnit());
		Quantity<IMeasurable> q2 = new Quantity<>(m2.getValue(), m2.getUnit());

		boolean isEqual = q1.equals(q2);

		QuantityMeasurementEntity entity = new QuantityMeasurementEntity(thisDTO.getValue(),
				thisDTO.getUnit().toUpperCase(), thisDTO.getMeasurementType(), thatDTO.getValue(),
				thatDTO.getUnit().toUpperCase(), thatDTO.getMeasurementType(), Operation.COMPARE.name(), 0.0,
				thisDTO.getUnit().toUpperCase(), thisDTO.getMeasurementType(), isEqual ? "Equal" : "Not Equal", false,
				"null");

		repository.save(entity);
		return new QuantityMeasurementDTO().from(entity);
	}

	@Override
	public QuantityMeasurementDTO convert(QuantityDTO thisDTO, QuantityDTO thatDTO) {
		QuantityModel<IMeasurable> m1 = mapToModel(thisDTO);
		QuantityModel<IMeasurable> m2 = mapToModel(thatDTO);

		Quantity<IMeasurable> q1 = new Quantity<>(m1.getValue(), m1.getUnit());
		double convertedValue = q1.convertTo(m2.getUnit());

		QuantityMeasurementEntity entity = new QuantityMeasurementEntity(thisDTO.getValue(),
				thisDTO.getUnit().toUpperCase(), thisDTO.getMeasurementType(), thatDTO.getValue(),
				thatDTO.getUnit().toUpperCase(), thatDTO.getMeasurementType(), Operation.CONVERT.name(), convertedValue,
				thatDTO.getUnit().toUpperCase(), thisDTO.getMeasurementType(), "Converted", false, "null");

		repository.save(entity);
		return new QuantityMeasurementDTO().from(entity);
	}

	@Override
	public List<QuantityMeasurementDTO> getOperationHistory(String operation) {
		return new QuantityMeasurementDTO().fromList(repository.findByOperation(operation.toUpperCase()));
	}

	@Override
	public List<QuantityMeasurementDTO> getErrorHistory() {
		return new QuantityMeasurementDTO().fromList(repository.findByIsErrorTrue());
	}

	@Override
	public QuantityMeasurementDTO add(QuantityDTO d1, QuantityDTO d2) {
		return executeArithmetic(d1, d2, null, Operation.ADD);
	}

	@Override
	public QuantityMeasurementDTO add(QuantityDTO d1, QuantityDTO d2, QuantityDTO target) {
		return executeArithmetic(d1, d2, target, Operation.ADD);
	}

	@Override
	public QuantityMeasurementDTO subtract(QuantityDTO d1, QuantityDTO d2) {
		return executeArithmetic(d1, d2, null, Operation.SUBTRACT);
	}

	@Override
	public QuantityMeasurementDTO subtract(QuantityDTO d1, QuantityDTO d2, QuantityDTO target) {
		return executeArithmetic(d1, d2, target, Operation.SUBTRACT);
	}

	@Override
	public QuantityMeasurementDTO divide(QuantityDTO d1, QuantityDTO d2) {
		return executeArithmetic(d1, d2, null, Operation.DIVIDE);
	}

	@Override
	public List<QuantityMeasurementDTO> getMeasurementsByType(String type) {
		return new QuantityMeasurementDTO().fromList(repository.findByThisMeasurementType(type));
	}

	@Override
	public long getOperationCount(String operation) {
		return repository.countByOperationAndIsErrorFalse(operation.toUpperCase());
	}

	// Helper logic
	private QuantityModel<IMeasurable> mapToModel(QuantityDTO dto) {
		if (dto == null)
			throw new QuantityMeasurementException("Quantity data cannot be null");
		String type = dto.getMeasurementType();
		String unitName = dto.getUnit();
		IMeasurable unit;
		try {
			switch (type) {
			case "LengthUnit":
				unit = LengthUnit.valueOf(unitName.toUpperCase());
				break;
			case "VolumeUnit":
				unit = VolumeUnit.valueOf(unitName.toUpperCase());
				break;
			case "WeightUnit":
				unit = WeightUnit.valueOf(unitName.toUpperCase());
				break;
			case "TemperatureUnit":
				unit = TemperatureUnit.valueOf(unitName.toUpperCase());
				break;
			default:
				throw new QuantityMeasurementException("Invalid Category: " + type);
			}
		} catch (IllegalArgumentException e) {
			throw new InvalidUnitException("Unit not valid: " + unitName);
		}
		return new QuantityModel<>(dto.getValue(), unit);
	}

	private void validateModels(QuantityModel<?> m1, QuantityModel<?> m2) {
		if (m1.getUnit().getClass() != m2.getUnit().getClass()) {
			throw new CategoryMismatchException("Incompatible types");
		}
	}

	private QuantityMeasurementDTO executeArithmetic(QuantityDTO d1, QuantityDTO d2, QuantityDTO target,
			Operation opType) {
		QuantityModel<IMeasurable> m1 = mapToModel(d1);
		QuantityModel<IMeasurable> m2 = mapToModel(d2);
		QuantityModel<IMeasurable> mT = (target != null) ? mapToModel(target) : null;
		validateModels(m1, m2);

		Quantity<IMeasurable> q1 = new Quantity<>(m1.getValue(), m1.getUnit());
		Quantity<IMeasurable> q2 = new Quantity<>(m2.getValue(), m2.getUnit());
		Quantity<IMeasurable> result;

		if (opType == Operation.ADD) {
			result = (mT != null) ? q1.add(q2, mT.getUnit()) : q1.add(q2);
		} else if (opType == Operation.SUBTRACT) {
			result = (mT != null) ? q1.subtract(q2, mT.getUnit()) : q1.subtract(q2);
		} else {
			double divRes = q1.divide(q2);
			result = new Quantity<IMeasurable>(divRes, m1.getUnit());
		}

		QuantityMeasurementEntity entity = new QuantityMeasurementEntity(d1.getValue(), d1.getUnit().toUpperCase(),
				d1.getMeasurementType(), d2.getValue(), d2.getUnit().toUpperCase(), d2.getMeasurementType(),
				opType.name(), result.getValue(), result.getUnit().getUnitName(), d1.getMeasurementType(), "Success",
				false, "null");

		repository.save(entity);
		return new QuantityMeasurementDTO().from(entity);
	}
}