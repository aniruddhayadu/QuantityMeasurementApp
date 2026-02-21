package com.apps.quantitymeasurementapp;
public class QuantityMeasurementApp {

    // Helper method to compare two lengths
    public static boolean demonstrateLengthComparison(double value1, Length.LengthUnit unit1,
                                                      double value2, Length.LengthUnit unit2) {
        Length length1 = new Length(value1, unit1);
        Length length2 = new Length(value2, unit2);
        boolean result = length1.equals(length2);
        System.out.println("Comparing " + value1 + " " + unit1 + " and " + value2 + " " + unit2 + ": " + result);
        return result;
    }

    public static void main(String[] args) {
        demonstrateLengthComparison(1.0, Length.LengthUnit.FEET, 12.0, Length.LengthUnit.INCHES);
        demonstrateLengthComparison(1.0, Length.LengthUnit.YARDS, 36.0, Length.LengthUnit.INCHES);
        demonstrateLengthComparison(100.0, Length.LengthUnit.CENTIMETERS, 39.37, Length.LengthUnit.INCHES);
        demonstrateLengthComparison(3.0, Length.LengthUnit.FEET, 1.0, Length.LengthUnit.YARDS);
        demonstrateLengthComparison(30.48, Length.LengthUnit.CENTIMETERS, 1.0, Length.LengthUnit.FEET);
        demonstrateLengthComparison(2.0, Length.LengthUnit.YARDS, 6.0, Length.LengthUnit.FEET);
        demonstrateLengthComparison(2.0, Length.LengthUnit.YARDS, 72.0, Length.LengthUnit.INCHES);
    }
}