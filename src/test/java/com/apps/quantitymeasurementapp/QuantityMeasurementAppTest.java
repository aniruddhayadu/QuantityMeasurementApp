package com.apps.quantitymeasurementapp;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {

    @Test
    public void testFeetEquality() {
        assertTrue(new Length(1.0, Length.LengthUnit.FEET).equals(new Length(1.0, Length.LengthUnit.FEET)));
    }

    @Test
    public void testInchesEquality() {
        assertTrue(new Length(12.0, Length.LengthUnit.INCHES).equals(new Length(12.0, Length.LengthUnit.INCHES)));
    }

    @Test
    public void testFeetInchesEquality() {
        assertTrue(new Length(1.0, Length.LengthUnit.FEET).equals(new Length(12.0, Length.LengthUnit.INCHES)));
    }

    @Test
    public void testFeetInequality() {
        assertFalse(new Length(1.0, Length.LengthUnit.FEET).equals(new Length(2.0, Length.LengthUnit.FEET)));
    }

    @Test
    public void testInchesInequality() {
        assertFalse(new Length(1.0, Length.LengthUnit.INCHES).equals(new Length(2.0, Length.LengthUnit.INCHES)));
    }

    @Test
    public void testYardEquality() {
        assertTrue(new Length(1.0, Length.LengthUnit.YARDS).equals(new Length(36.0, Length.LengthUnit.INCHES)));
    }

    @Test
    public void testCentimetersEquality() {
        assertTrue(new Length(100.0, Length.LengthUnit.CENTIMETERS)
                .equals(new Length(39.37, Length.LengthUnit.INCHES)));
    }

    @Test
    public void testThreeFeetEqualsOneYard() {
        assertTrue(new Length(3.0, Length.LengthUnit.FEET).equals(new Length(1.0, Length.LengthUnit.YARDS)));
    }

    @Test
    public void testThirtyPointFourEightCmEqualsOneFoot() {
        assertTrue(new Length(30.48, Length.LengthUnit.CENTIMETERS)
                .equals(new Length(1.0, Length.LengthUnit.FEET)));
    }

    @Test
    public void testReferenceEquality() {
        Length length = new Length(1.0, Length.LengthUnit.FEET);
        assertTrue(length.equals(length));
    }

    @Test
    public void testEqualsFalseForNull() {
        Length length = new Length(1.0, Length.LengthUnit.FEET);
        assertFalse(length.equals(null));
    }

    @Test
    public void testTransitiveProperty() {
        Length length1 = new Length(1.0, Length.LengthUnit.FEET);
        Length length2 = new Length(12.0, Length.LengthUnit.INCHES);
        Length length3 = new Length(1.0, Length.LengthUnit.YARDS);
        assertTrue(length1.equals(length2));
        assertTrue(length2.equals(length3));
        assertTrue(length1.equals(length3));
    }

    @Test
    public void testDifferentValuesSameUnitNotEqual() {
        assertFalse(new Length(1.0, Length.LengthUnit.FEET).equals(new Length(2.0, Length.LengthUnit.FEET)));
    }

    @Test
    public void testComplexMultiUnitScenario() {
        Length yard = new Length(2.0, Length.LengthUnit.YARDS);
        Length feet = new Length(6.0, Length.LengthUnit.FEET);
        Length inches = new Length(72.0, Length.LengthUnit.INCHES);
        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(yard.equals(inches));
    }
}