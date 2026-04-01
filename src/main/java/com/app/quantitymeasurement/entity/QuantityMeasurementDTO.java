package com.app.quantitymeasurement.entity;

import java.util.List;
import java.util.stream.Collectors;
import com.app.quantitymeasurement.dto.QuantityMeasurementEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuantityMeasurementDTO {
	public double thisValue;
	public String thisUnit;
	public String thisMeasurementType;
	public double thatValue;
	public String thatUnit;
	public String thatMeasurementType;
	public String operation;
	public double resultValue;
	public String resultUnit;
	public String resultString;
	public String resultMeasurementType;

	@JsonProperty("error")
	private boolean error; // Use single boolean field
	public String errorMessage;

	public QuantityMeasurementDTO from(QuantityMeasurementEntity entity) {
		if (entity == null)
			return this;
		this.resultValue = entity.getResultValue();
		this.resultUnit = entity.getResultUnit();
		this.resultMeasurementType = entity.getResultMeasurementType();
		this.resultString = entity.getResultString();
		this.error = entity.isError();
		this.errorMessage = entity.getErrorMessage();
		return this;
	}

	public List<QuantityMeasurementDTO> fromList(List<QuantityMeasurementEntity> entities) {
		if (entities == null)
			return List.of();
		return entities.stream().map(e -> new QuantityMeasurementDTO().from(e)).collect(Collectors.toList());
	}
}