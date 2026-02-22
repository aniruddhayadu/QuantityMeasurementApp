package com.apps.quantitymeasurementapp;

import java.util.Objects;

/**
 * Immutable Weight value object.
 * Base unit: KILOGRAM
 */
public final class Weight {

    private final double value;
    private final WeightUnit unit;

    private static final double EPSILON = 1e-6;

    public Weight(double value, WeightUnit unit) {
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    /* =========================
       Conversion
       ========================= */

    private double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    public Weight convertTo(WeightUnit targetUnit) {
        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue = toBaseUnit();
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);

        return new Weight(convertedValue, targetUnit);
    }

    /* =========================
       Addition
       ========================= */

    // Implicit target (first operand's unit)
    public Weight add(Weight other) {
        return add(other, this.unit);
    }

    // Explicit target
    public Weight add(Weight other, WeightUnit targetUnit) {

        if (other == null)
            throw new IllegalArgumentException("Second operand cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double sumInBase = this.toBaseUnit() + other.toBaseUnit();

        double resultValue = targetUnit.convertFromBaseUnit(sumInBase);

        return new Weight(resultValue, targetUnit);
    }

    /* =========================
       Equality
       ========================= */

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null || this.getClass() != obj.getClass())
            return false;

        Weight other = (Weight) obj;

        return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Math.round(toBaseUnit() / EPSILON));
    }

    @Override
    public String toString() {
        return String.format("%.6f %s", value, unit);
    }
}