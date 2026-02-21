package com.apps.quantitymeasurementapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    @Test
    void testConversion_FeetToInches() {
        assertEquals(12.0,
                Length.convert(1.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES),
                EPSILON);
    }

    @Test
    void testConversion_InchesToFeet() {
        assertEquals(2.0,
                Length.convert(24.0, Length.LengthUnit.INCHES, Length.LengthUnit.FEET),
                EPSILON);
    }

    @Test
    void testConversion_YardsToInches() {
        assertEquals(36.0,
                Length.convert(1.0, Length.LengthUnit.YARDS, Length.LengthUnit.INCHES),
                EPSILON);
    }

    @Test
    void testConversion_CentimetersToInches() {
        assertEquals(1.0,
                Length.convert(2.54, Length.LengthUnit.CENTIMETERS, Length.LengthUnit.INCHES),
                1e-3);
    }

    @Test
    void testConversion_ZeroValue() {
        assertEquals(0.0,
                Length.convert(0.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES),
                EPSILON);
    }

    @Test
    void testConversion_NegativeValue() {
        assertEquals(-12.0,
                Length.convert(-1.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES),
                EPSILON);
    }

    @Test
    void testRoundTripConversion() {
        double original = 5.0;
        double converted = Length.convert(original,
                Length.LengthUnit.FEET,
                Length.LengthUnit.INCHES);

        double back = Length.convert(converted,
                Length.LengthUnit.INCHES,
                Length.LengthUnit.FEET);

        assertEquals(original, back, EPSILON);
    }

    @Test
    void testInvalidUnit_Throws() {
        assertThrows(IllegalArgumentException.class,
                () -> Length.convert(1.0, null, Length.LengthUnit.FEET));
    }

    @Test
    void testNaN_Throws() {
        assertThrows(IllegalArgumentException.class,
                () -> Length.convert(Double.NaN,
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.INCHES));
    }

    @Test
    void testEqualityAcrossUnits() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);
        assertTrue(l1.equals(l2));
    }
}