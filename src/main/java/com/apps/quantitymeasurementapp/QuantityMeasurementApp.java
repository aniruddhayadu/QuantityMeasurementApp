package com.apps.quantitymeasurementapp;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        System.out.println("===== UC9 Weight Measurement Demo =====");

        // Equality
        System.out.println(new Weight(1.0, WeightUnit.KILOGRAM)
                .equals(new Weight(1000.0, WeightUnit.GRAM)));

        System.out.println(new Weight(1.0, WeightUnit.KILOGRAM)
                .equals(new Weight(2.20462, WeightUnit.POUND)));

        // Conversion
        System.out.println(new Weight(1.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM));

        System.out.println(new Weight(2.0, WeightUnit.POUND)
                .convertTo(WeightUnit.KILOGRAM));

        // Addition (implicit target)
        System.out.println(new Weight(1.0, WeightUnit.KILOGRAM)
                .add(new Weight(1000.0, WeightUnit.GRAM)));

        // Addition (explicit target)
        System.out.println(new Weight(1.0, WeightUnit.KILOGRAM)
                .add(new Weight(1000.0, WeightUnit.GRAM),
                        WeightUnit.GRAM));

        System.out.println("===== UC9 Completed =====");
    }
}