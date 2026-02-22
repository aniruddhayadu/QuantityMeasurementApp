package com.apps.quantitymeasurementapp;

/**
 * Standalone WeightUnit enum.
 * Base unit: KILOGRAM
 */
public enum WeightUnit {

    KILOGRAM(1.0),
    GRAM(0.001),          // 1 g = 0.001 kg
    POUND(0.453592);      // 1 lb ≈ 0.453592 kg

    private final double conversionFactor; // to kilogram

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    // Convert value in this unit → base unit (kg)
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    // Convert value from base unit (kg) → this unit
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }
}
