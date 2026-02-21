package com.apps.quantitymeasurementapp;

import java.util.Objects;

/**
 * Immutable Length value object.
 * Base unit: INCHES
 */
public class Length {

    private final double value;
    private final LengthUnit unit;

    private static final double EPSILON = 1e-6;

    public enum LengthUnit {
        INCHES(1.0),
        FEET(12.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double factor;

        LengthUnit(double factor) {
            this.factor = factor;
        }

        public double getFactor() {
            return factor;
        }
    }

    public Length(double value, LengthUnit unit) {
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() { return value; }

    public LengthUnit getUnit() { return unit; }

    /* =========================
       Conversion (UC5)
       ========================= */

    private double toBaseUnit() {
        return value * unit.getFactor();
    }

    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue = this.toBaseUnit();
        double converted = baseValue / targetUnit.getFactor();
        return new Length(converted, targetUnit);
    }

    /* =========================
       UC6 – Addition (implicit target)
       ========================= */

    public Length add(Length other) {
        return addInternal(other, this.unit);
    }

    /* =========================
       UC7 – Addition with Explicit Target
       ========================= */

    public Length add(Length other, LengthUnit targetUnit) {
        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        return addInternal(other, targetUnit);
    }

    /* =========================
       Private Utility Method (DRY)
       ========================= */

    private Length addInternal(Length other, LengthUnit targetUnit) {

        if (other == null)
            throw new IllegalArgumentException("Second operand cannot be null");

        // Normalize both to base (INCHES)
        double sumInBase = this.toBaseUnit() + other.toBaseUnit();

        // Convert to target unit
        double resultValue = sumInBase / targetUnit.getFactor();

        return new Length(resultValue, targetUnit);
    }

    /* =========================
       Equality
       ========================= */

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Length)) return false;

        Length other = (Length) obj;
        return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toBaseUnit());
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}