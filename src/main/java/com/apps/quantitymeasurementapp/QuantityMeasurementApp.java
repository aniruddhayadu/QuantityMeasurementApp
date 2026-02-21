package com.apps.quantitymeasurementapp;

public class QuantityMeasurementApp {

    /**
     * Demonstrates addition with explicit target unit (UC7)
     */
    public static Length demonstrateAddition(
            Length length1,
            Length length2,
            Length.LengthUnit targetUnit) {

        if (length1 == null || length2 == null) {
            throw new IllegalArgumentException("Operands cannot be null");
        }

        Length result = length1.add(length2, targetUnit);

        System.out.println("add(" + length1 + ", "
                + length2 + ", "
                + targetUnit + ") = " + result);

        return result;
    }

    public static void main(String[] args) {

        System.out.println("===== UC7: Addition with Target Unit Specification =====");

        // FEET target
        demonstrateAddition(
                new Length(1.0, Length.LengthUnit.FEET),
                new Length(12.0, Length.LengthUnit.INCHES),
                Length.LengthUnit.FEET);

        // INCHES target
        demonstrateAddition(
                new Length(1.0, Length.LengthUnit.FEET),
                new Length(12.0, Length.LengthUnit.INCHES),
                Length.LengthUnit.INCHES);

        // YARDS target
        demonstrateAddition(
                new Length(1.0, Length.LengthUnit.FEET),
                new Length(12.0, Length.LengthUnit.INCHES),
                Length.LengthUnit.YARDS);

        //  Yard + Feet → YARDS
        demonstrateAddition(
                new Length(1.0, Length.LengthUnit.YARDS),
                new Length(3.0, Length.LengthUnit.FEET),
                Length.LengthUnit.YARDS);

        // Inches + Yard → FEET
        demonstrateAddition(
                new Length(36.0, Length.LengthUnit.INCHES),
                new Length(1.0, Length.LengthUnit.YARDS),
                Length.LengthUnit.FEET);

        //  Centimeter + Inch → CENTIMETERS
        demonstrateAddition(
                new Length(2.54, Length.LengthUnit.CENTIMETERS),
                new Length(1.0, Length.LengthUnit.INCHES),
                Length.LengthUnit.CENTIMETERS);

        // Zero case
        demonstrateAddition(
                new Length(5.0, Length.LengthUnit.FEET),
                new Length(0.0, Length.LengthUnit.INCHES),
                Length.LengthUnit.YARDS);

        // 8️⃣ Negative case
        demonstrateAddition(
                new Length(5.0, Length.LengthUnit.FEET),
                new Length(-2.0, Length.LengthUnit.FEET),
                Length.LengthUnit.INCHES);

        System.out.println("===== UC7 Demo Completed =====");
    }
}