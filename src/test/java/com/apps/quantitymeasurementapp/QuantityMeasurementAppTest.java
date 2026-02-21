package com.apps.quantitymeasurementapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testAddition_SameUnit_FeetPlusFeet() {
        Length result = new Length(3, Length.LengthUnit.FEET);
        assertEquals(result,
                new Length(1, Length.LengthUnit.FEET)
                        .add(new Length(2, Length.LengthUnit.FEET)));
    }

    @Test
    void testAddition_CrossUnit_FeetPlusInches() {
        Length result = new Length(2, Length.LengthUnit.FEET);
        assertEquals(result,
                new Length(1, Length.LengthUnit.FEET)
                        .add(new Length(12, Length.LengthUnit.INCHES)));
    }

    @Test
    void testAddition_Commutativity() {
        Length a = new Length(1, Length.LengthUnit.FEET);
        Length b = new Length(12, Length.LengthUnit.INCHES);

        Length result1 = a.add(b);
        Length result2 = b.add(a).convertTo(Length.LengthUnit.FEET);

        assertEquals(result1, result2);
    }

    @Test
    void testAddition_WithZero() {
        Length result = new Length(5, Length.LengthUnit.FEET);
        assertEquals(result,
                new Length(5, Length.LengthUnit.FEET)
                        .add(new Length(0, Length.LengthUnit.INCHES)));
    }

    @Test
    void testAddition_NullSecondOperand() {
        assertThrows(IllegalArgumentException.class,
                () -> new Length(1, Length.LengthUnit.FEET).add(null));
    }

    @Test
    void testAddition_LargeValues() {
        Length result = new Length(2e6, Length.LengthUnit.FEET);
        assertEquals(result,
                new Length(1e6, Length.LengthUnit.FEET)
                        .add(new Length(1e6, Length.LengthUnit.FEET)));
    }
}