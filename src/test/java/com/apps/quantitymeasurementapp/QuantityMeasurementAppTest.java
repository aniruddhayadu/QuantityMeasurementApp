package com.apps.quantitymeasurementapp;
import org.junit.Test;
import static org.junit.Assert.*;

public class WeightTest {

    private static final double EPSILON = 1e-6;

    @Test
    public void testEquality_KgToGram() {
        assertEquals(
                new Weight(1.0, WeightUnit.KILOGRAM),
                new Weight(1000.0, WeightUnit.GRAM)
        );
    }

    @Test
    public void testEquality_KgToPound() {
        assertEquals(
                new Weight(1.0, WeightUnit.KILOGRAM),
                new Weight(2.20462, WeightUnit.POUND)
        );
    }

    @Test
    public void testConversion_PoundToKg() {
        Weight result = new Weight(2.20462, WeightUnit.POUND)
                .convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_KgPlusGram() {
        Weight result = new Weight(1.0, WeightUnit.KILOGRAM)
                .add(new Weight(1000.0, WeightUnit.GRAM));

        assertEquals(
                new Weight(2.0, WeightUnit.KILOGRAM),
                result
        );
    }

    @Test
    public void testAddition_ExplicitTarget() {
        Weight result = new Weight(1.0, WeightUnit.KILOGRAM)
                .add(new Weight(1000.0, WeightUnit.GRAM),
                        WeightUnit.GRAM);

        assertEquals(
                new Weight(2000.0, WeightUnit.GRAM),
                result
        );
    }

    @Test
    public void testWeightVsLength_Incompatible() {
        Weight weight = new Weight(1.0, WeightUnit.KILOGRAM);
        Length length = new Length(1.0, LengthUnit.FEET);

        assertNotEquals(weight, length);
    }
}}