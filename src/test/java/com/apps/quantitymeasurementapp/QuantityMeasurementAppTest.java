package com.apps.quantitymeasurementapp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class QuantityMeasurementAppTest {

    // Feet tests
    @Test
    void feetSameValue() {
        assertTrue(QuantityMeasurementApp.sameFeet(5.0, 5.0));
    }

    @Test
    void feetDifferentValue() {
        assertFalse(QuantityMeasurementApp.sameFeet(5.0, 3.0));
    }

    @Test
    void feetNullCheck() {
        QuantityMeasurementApp.Feet f = new QuantityMeasurementApp.Feet(1.0);
        assertFalse(f.equals(null));
    }

    @Test
    void feetSameReference() {
        QuantityMeasurementApp.Feet f = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet ref = f;
        assertTrue(f.equals(ref));
    }

    @Test
    void feetInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> new QuantityMeasurementApp.Feet(Double.NaN));
    }

    // Inches tests
    @Test
    void inchesSameValue() {
        assertTrue(QuantityMeasurementApp.sameInches(10.0, 10.0));
    }

    @Test
    void inchesDifferentValue() {
        assertFalse(QuantityMeasurementApp.sameInches(10.0, 5.0));
    }

    @Test
    void inchesNullCheck() {
        QuantityMeasurementApp.Inches i = new QuantityMeasurementApp.Inches(1.0);
        assertFalse(i.equals(null));
    }

    @Test
    void inchesSameReference() {
        QuantityMeasurementApp.Inches i = new QuantityMeasurementApp.Inches(1.0);
        QuantityMeasurementApp.Inches ref = i;
        assertTrue(i.equals(ref));
    }

    @Test
    void inchesInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> new QuantityMeasurementApp.Inches(Double.NaN));
    }
}