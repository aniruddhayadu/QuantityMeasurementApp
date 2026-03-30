package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.dto.QuantityDTO;
import com.app.quantitymeasurement.entity.QuantityMeasurementDTO;
import com.app.quantitymeasurement.repository.QuantityMeasurementRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QuantityMeasurementServiceImplTest {

	@InjectMocks
	private QuantityMeasurementServiceImpl measurementService;

	@Mock
	private QuantityMeasurementRepository repository;

	@Test
	public void given1FeetAnd12Inches_WhenCompared_ShouldReturnEqual() {
		// 1. Arrange: Do alag DTOs banao kyunki service 2 arguments leti hai
		QuantityDTO thisDTO = new QuantityDTO();
		thisDTO.value = 1.0;
		thisDTO.unit = "FEET";
		thisDTO.measurementType = "LengthUnit";

		QuantityDTO thatDTO = new QuantityDTO();
		thatDTO.value = 12.0;
		thatDTO.unit = "INCHES";
		thatDTO.measurementType = "LengthUnit";

		// 2. Act: Direct do arguments pass karo
		QuantityMeasurementDTO result = measurementService.compare(thisDTO, thatDTO);

		// 3. Assert
		assertEquals("Equal", result.getResultString());
		assertFalse(result.isError());
	}
}