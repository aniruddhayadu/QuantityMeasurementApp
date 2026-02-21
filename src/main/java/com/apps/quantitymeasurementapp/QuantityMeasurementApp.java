package com.apps.quantitymeasurementapp;

import java.util.Objects;
import java.util.Scanner;

/**
 * Enum defining supported length units and their conversion factor relative to FEET.
 */
enum LengthUnit {
    FEET(1.0),        // Base unit
    INCH(1.0 / 12);   // 1 inch = 1/12 feet

    private final double toFeetFactor;

    LengthUnit(double toFeetFactor) {
        this.toFeetFactor = toFeetFactor;
    }

    public double toBaseUnit(double value) {
        return value * toFeetFactor;
    }
}

/**
 * Generic class representing a length measurement with a unit.
 * Implements value-based equality, cross-unit comparison, and null safety.
 */
class QuantityLength {
    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Value must be a valid number");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Unit must not be null");
        }
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof QuantityLength)) return false;
        QuantityLength other = (QuantityLength) obj;
        // Compare by converting both measurements to base unit (FEET)
        double thisInFeet = this.unit.toBaseUnit(this.value);
        double otherInFeet = other.unit.toBaseUnit(other.value);
        return Double.compare(thisInFeet, otherInFeet) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit.toBaseUnit(value));
    }
}

/**
 * Main application class providing UC1, UC2, and UC3 functionality.
 */
public class QuantityMeasurementApp {

    // UC1 – Feet equality check
    public static boolean sameFeet(double first, double second) {
        return new QuantityLength(first, LengthUnit.FEET)
                .equals(new QuantityLength(second, LengthUnit.FEET));
    }

    // UC2 – Inches equality check
    public static boolean sameInches(double first, double second) {
        return new QuantityLength(first, LengthUnit.INCH)
                .equals(new QuantityLength(second, LengthUnit.INCH));
    }

    // UC3 – Generic comparison for any supported units
    public static boolean compareQuantity(double value1, LengthUnit unit1,
                                          double value2, LengthUnit unit2) {
        return new QuantityLength(value1, unit1)
                .equals(new QuantityLength(value2, unit2));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("=== Quantity Measurement App ===");

            // Input first measurement
            System.out.print("Enter first value: ");
            double val1 = scanner.nextDouble();
            System.out.print("Enter first unit (FEET/INCH): ");
            
            LengthUnit unit1 = LengthUnit.valueOf(scanner.next().toUpperCase());

            // Input second measurement
            System.out.print("Enter second value: ");
            double val2 = scanner.nextDouble();
            System.out.print("Enter second unit (FEET/INCH): ");
            LengthUnit unit2 = LengthUnit.valueOf(scanner.next().toUpperCase());

            // UC3 generic comparison
            
            boolean result = compareQuantity(val1, unit1, val2, unit2);
            System.out.println("Comparison result: " + result);

            // UC1 & UC2 backward-compatible checks
            System.out.println("Feet same? " + sameFeet(val1, val2));
            System.out.println("Inches same? " + sameInches(val1, val2));

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input! " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}