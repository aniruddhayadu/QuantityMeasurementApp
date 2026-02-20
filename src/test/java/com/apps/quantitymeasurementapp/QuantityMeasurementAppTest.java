package com.apps.quantitymeasurementapp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QuantityMeasurementMainTest {

	@Test
	public void testEquality_SameValue()  {
		QuantityMeasurementApp feet1 = new QuantityMeasurementApp(10.1);
		QuantityMeasurementApp feet2 = new QuantityMeasurementApp(10.1);
		assertTrue(feet1.equals(feet2));
	}
	
	@Test
	public void testEquality_DifferentValue() {
		QuantityMeasurementApp feet1 = new QuantityMeasurementApp(10.1);
		QuantityMeasurementApp feet2 = new QuantityMeasurementApp(1.1);
		assertFalse(feet1.equals(feet2));
	}

	@Test
	public void testEquality_NullComparison() {
		QuantityMeasurementApp feet1 = new QuantityMeasurementApp(1.0);
		QuantityMeasurementApp feet2 = null;
		assertFalse(feet1.equals(feet2));;
	}
	
	@Test
	public void testEquality_NonNumericInput() {
		assertThrows(IllegalArgumentException.class, ()-> {
			new QuantityMeasurementApp(Double.NaN);
		});
	}
	
	@Test
	public void testEquality_SameReference() {
		QuantityMeasurementApp feet1 = new QuantityMeasurementApp(1.0);
		QuantityMeasurementApp feet2 = feet1;
		assertTrue(feet1.equals(feet2));
	}
}