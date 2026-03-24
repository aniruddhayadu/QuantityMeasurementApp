package com.app.quantitymeasurement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.app.quantitymeasurement.controller.QuantityMeasurementController;
import com.app.quantitymeasurement.dto.QuantityDTO;
import com.app.quantitymeasurement.dto.QuantityInputDTO;
import com.app.quantitymeasurement.dto.QuantityModel;
import com.app.quantitymeasurement.entity.QuantityMeasurementDTO;
import com.app.quantitymeasurement.exception.CategoryMismatchException;
import com.app.quantitymeasurement.exception.QuantityMeasurementException;
import com.app.quantitymeasurement.unit.IMeasurable;
import com.app.quantitymeasurement.unit.LengthUnit;

@SpringBootTest
class QuantityMeasurementAppApplicationTests {

	@Autowired
	private QuantityMeasurementController controller;

	// 1. DTO Structure Test
	@Test
	public void testQuantityDTO_Structure() {
		QuantityDTO quantity = new QuantityDTO();
		quantity.value = 1.0;
		quantity.unit = "FEET";
		quantity.measurementType = "LengthUnit";

		assertEquals(1.0, quantity.value);
		assertEquals("FEET", quantity.unit);
	}

	// 2. Comparison Test (Check if your controller has 'compare' method)
	@Test
	void testController_CompareEquality_Success() {
		QuantityDTO d1 = new QuantityDTO();
		d1.value = 1.0;
		d1.unit = "FEET";
		d1.measurementType = "LengthUnit";

		QuantityDTO d2 = new QuantityDTO();
		d2.value = 12.0;
		d2.unit = "INCHES";
		d2.measurementType = "LengthUnit";

		QuantityInputDTO qt = new QuantityInputDTO();
		qt.setThisQuantityDTO(d1);
		qt.setThatQuantityDTO(d2);

		// FIX: Change 'performComparison' to your actual controller method name (e.g.,
		// compare)
		ResponseEntity<QuantityMeasurementDTO> response = controller.compare(qt);
		assertEquals("Equal", response.getBody().getResultString());
	}

	// 3. Exception Test
	@Test
	void testController_CrossCategory_Error() {
		QuantityDTO d1 = new QuantityDTO();
		d1.value = 1.0;
		d1.unit = "FEET";
		d1.measurementType = "LengthUnit";

		QuantityDTO d2 = new QuantityDTO();
		d2.value = 1.0;
		d2.unit = "LITRE";
		d2.measurementType = "VolumeUnit";

		QuantityInputDTO qt = new QuantityInputDTO();
		qt.setThisQuantityDTO(d1);
		qt.setThatQuantityDTO(d2);

		assertThrows(Exception.class, () -> controller.compare(qt));
	}
}