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

    /**
     * Supported units with conversion factor relative to INCHES.
     */
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

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    /* =========================
       UC5 Conversion Methods
       ========================= */

    public static double convert(double value, LengthUnit source, LengthUnit target) {
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");

        if (source == null || target == null)
            throw new IllegalArgumentException("Units cannot be null");

        double valueInBase = value * source.getFactor();
        return valueInBase / target.getFactor();
    }

    public Length convertTo(LengthUnit targetUnit) {
        double converted = convert(this.value, this.unit, targetUnit);
        return new Length(converted, targetUnit);
    }

    private double toBaseUnit() {
        return value * unit.getFactor();
    }

    /* =========================
       UC6 ADDITION
       ========================= */

    /**
     * Adds another length to this length.
     * Result is returned in the unit of the first operand (this.unit).
     */
    public Length add(Length other) {

        if (other == null)
            throw new IllegalArgumentException("Second operand cannot be null");

        // Convert both to base unit (INCHES)
        double sumInBase = this.toBaseUnit() + other.toBaseUnit();

        // Convert back to this unit
        double resultValue = sumInBase / this.unit.getFactor();

        return new Length(resultValue, this.unit);
    }

    /* =========================
       Equality (epsilon-based)
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