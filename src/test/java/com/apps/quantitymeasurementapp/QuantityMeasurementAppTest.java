package com.apps.quantitymeasurementapp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class QuantityMeasurementAppTest {

    // ================================
    // UC1 & UC2: Same-unit equality	
	
    // ================================

    @Test
    void feetSameValue() {
        assertTrue(QuantityMeasurementApp.sameFeet(5.0, 5.0));
    }

    @Test
    void feetDifferentValue() {
        assertFalse(QuantityMeasurementApp.sameFeet(5.0, 3.0));
    }

    @Test
    void inchesSameValue() {
        assertTrue(QuantityMeasurementApp.sameInches(12.0, 12.0));
    }

    @Test
    void inchesDifferentValue() {
        assertFalse(QuantityMeasurementApp.sameInches(10.0, 5.0));
    }

    // ================================
    // UC1 & UC2: Null and reference checks
    // ================================

    @Test
    void feetNullCheck() {
        QuantityLength f = new QuantityLength(1.0, LengthUnit.FEET);
        assertFalse(f.equals(null));
    }

    @Test
    void feetSameReference() {
        QuantityLength f = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength ref = f;
        assertTrue(f.equals(ref));
    }

    @Test
    void inchesNullCheck() {
        QuantityLength i = new QuantityLength(1.0, LengthUnit.INCH);
        assertFalse(i.equals(null));
    }

    @Test
    void inchesSameReference() {
        QuantityLength i = new QuantityLength(1.0, LengthUnit.INCH);
        QuantityLength ref = i;
        assertTrue(i.equals(ref));
    }

    // ================================
    // UC3: Cross-unit equality
    // ================================

    @Test
    void feetToInchEquivalent() {
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inches = new QuantityLength(12.0, LengthUnit.INCH);
        assertTrue(feet.equals(inches));
        assertTrue(inches.equals(feet)); // symmetry
    }

    @Test
    void feetToInchNonEquivalent() {
        QuantityLength feet = new QuantityLength(2.0, LengthUnit.FEET);
        QuantityLength inches = new QuantityLength(12.0, LengthUnit.INCH);
        assertFalse(feet.equals(inches));
    }

    @Test
    void compareQuantityMethod_SameUnit() {
        assertTrue(QuantityMeasurementApp.compareQuantity(1.0, LengthUnit.FEET, 1.0, LengthUnit.FEET));
        assertTrue(QuantityMeasurementApp.compareQuantity(12.0, LengthUnit.INCH, 12.0, LengthUnit.INCH));
    }

    @Test
    void compareQuantityMethod_CrossUnit() {
        assertTrue(QuantityMeasurementApp.compareQuantity(1.0, LengthUnit.FEET, 12.0, LengthUnit.INCH));
        assertFalse(QuantityMeasurementApp.compareQuantity(2.0, LengthUnit.FEET, 12.0, LengthUnit.INCH));
    }

    // ================================
    // UC3: Invalid input handling
    // ================================

    @Test
    void feetInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> new QuantityLength(Double.NaN, LengthUnit.FEET));
        assertThrows(IllegalArgumentException.class, () -> new QuantityLength(Double.POSITIVE_INFINITY, LengthUnit.FEET));
    }

    @Test
    void inchesInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> new QuantityLength(Double.NaN, LengthUnit.INCH));
        assertThrows(IllegalArgumentException.class, () -> new QuantityLength(Double.NEGATIVE_INFINITY, LengthUnit.INCH));
    }

    @Test
    void nullUnitCheck() {
        assertThrows(IllegalArgumentException.class, () -> new QuantityLength(1.0, null));
    }
}