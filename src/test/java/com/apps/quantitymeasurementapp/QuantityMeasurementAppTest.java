package com.apps.quantitymeasurementapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    /* =========================
       Explicit Target – FEET
       ========================= */

    @Test
    void testAddition_ExplicitTargetUnit_Feet() {

        Length result = new Length(2.0, Length.LengthUnit.FEET);

        Length actual = new Length(1.0, Length.LengthUnit.FEET)
                .add(new Length(12.0, Length.LengthUnit.INCHES),
                        Length.LengthUnit.FEET);

        assertEquals(result, actual);
    }

    /* =========================
       Explicit Target – INCHES
       ========================= */

    @Test
    void testAddition_ExplicitTargetUnit_Inches() {

        Length result = new Length(24.0, Length.LengthUnit.INCHES);

        Length actual = new Length(1.0, Length.LengthUnit.FEET)
                .add(new Length(12.0, Length.LengthUnit.INCHES),
                        Length.LengthUnit.INCHES);

        assertEquals(result, actual);
    }

    /* =========================
       Explicit Target – YARDS
       ========================= */

    @Test
    void testAddition_ExplicitTargetUnit_Yards() {

        Length expected = new Length(0.6666667, Length.LengthUnit.YARDS);

        Length actual = new Length(1.0, Length.LengthUnit.FEET)
                .add(new Length(12.0, Length.LengthUnit.INCHES),
                        Length.LengthUnit.YARDS);

        assertEquals(expected, actual);
    }

    /* =========================
       Explicit Target – CENTIMETERS
       ========================= */

    @Test
    void testAddition_ExplicitTargetUnit_Centimeters() {

        Length expected = new Length(5.08, Length.LengthUnit.CENTIMETERS);

        Length actual = new Length(2.54, Length.LengthUnit.CENTIMETERS)
                .add(new Length(1.0, Length.LengthUnit.INCHES),
                        Length.LengthUnit.CENTIMETERS);

        assertEquals(expected, actual);
    }

    /* =========================
       Same as First Operand
       ========================= */

    @Test
    void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {

        Length expected = new Length(3.0, Length.LengthUnit.YARDS);

        Length actual = new Length(2.0, Length.LengthUnit.YARDS)
                .add(new Length(3.0, Length.LengthUnit.FEET),
                        Length.LengthUnit.YARDS);

        assertEquals(expected, actual);
    }

    /* =========================
       Same as Second Operand
       ========================= */

    @Test
    void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {

        Length expected = new Length(9.0, Length.LengthUnit.FEET);

        Length actual = new Length(2.0, Length.LengthUnit.YARDS)
                .add(new Length(3.0, Length.LengthUnit.FEET),
                        Length.LengthUnit.FEET);

        assertEquals(expected, actual);
    }

    /* =========================
       Commutativity
       ========================= */

    @Test
    void testAddition_ExplicitTargetUnit_Commutativity() {

        Length a = new Length(1.0, Length.LengthUnit.FEET);
        Length b = new Length(12.0, Length.LengthUnit.INCHES);

        Length result1 = a.add(b, Length.LengthUnit.YARDS);
        Length result2 = b.add(a, Length.LengthUnit.YARDS);

        assertEquals(result1, result2);
    }

    /* =========================
       Zero Handling
       ========================= */

    @Test
    void testAddition_ExplicitTargetUnit_WithZero() {

        Length expected = new Length(1.6666667, Length.LengthUnit.YARDS);

        Length actual = new Length(5.0, Length.LengthUnit.FEET)
                .add(new Length(0.0, Length.LengthUnit.INCHES),
                        Length.LengthUnit.YARDS);

        assertEquals(expected, actual);
    }

    /* =========================
       Negative Values
       ========================= */

    @Test
    void testAddition_ExplicitTargetUnit_NegativeValues() {

        Length expected = new Length(36.0, Length.LengthUnit.INCHES);

        Length actual = new Length(5.0, Length.LengthUnit.FEET)
                .add(new Length(-2.0, Length.LengthUnit.FEET),
                        Length.LengthUnit.INCHES);

        assertEquals(expected, actual);
    }

    /* =========================
       Null Target Unit
       ========================= */

    @Test
    void testAddition_ExplicitTargetUnit_NullTargetUnit() {

        assertThrows(IllegalArgumentException.class,
                () -> new Length(1.0, Length.LengthUnit.FEET)
                        .add(new Length(12.0, Length.LengthUnit.INCHES), null));
    }

    /* =========================
       Large Values
       ========================= */

    @Test
    void testAddition_ExplicitTargetUnit_LargeToSmallScale() {

        Length expected = new Length(18000.0, Length.LengthUnit.INCHES);

        Length actual = new Length(1000.0, Length.LengthUnit.FEET)
                .add(new Length(500.0, Length.LengthUnit.FEET),
                        Length.LengthUnit.INCHES);

        assertEquals(expected, actual);
    }

    /* =========================
       Small Values
       ========================= */

    @Test
    void testAddition_ExplicitTargetUnit_SmallToLargeScale() {

        Length expected = new Length(0.6666667, Length.LengthUnit.YARDS);

        Length actual = new Length(12.0, Length.LengthUnit.INCHES)
                .add(new Length(12.0, Length.LengthUnit.INCHES),
                        Length.LengthUnit.YARDS);

        assertEquals(expected, actual);
    }
}