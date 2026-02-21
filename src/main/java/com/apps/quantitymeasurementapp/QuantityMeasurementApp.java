package com.apps.quantitymeasurementapp;

public class QuantityMeasurementApp {

    // Overloaded Method 1
    public static double demonstrateLengthConversion(double value,
                                                     Length.LengthUnit from,
                                                     Length.LengthUnit to) {

        double result = Length.convert(value, from, to);
        System.out.println("convert(" + value + ", " + from + ", " + to + ") = " + result);
        return result;
    }

    // Overloaded Method 2
    public static Length demonstrateLengthConversion(Length length,
                                                     Length.LengthUnit to) {

        Length result = length.convertTo(to);
        System.out.println(length + " -> " + result);
        return result;
    }

    public static boolean demonstrateLengthEquality(Length l1, Length l2) {
        boolean result = l1.equals(l2);
        System.out.println("Equality: " + result);
        return result;
    }

    public static void main(String[] args) {

        demonstrateLengthConversion(1.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
        demonstrateLengthConversion(3.0, Length.LengthUnit.YARDS, Length.LengthUnit.FEET);
        demonstrateLengthConversion(36.0, Length.LengthUnit.INCHES, Length.LengthUnit.YARDS);
        demonstrateLengthConversion(1.0, Length.LengthUnit.CENTIMETERS, Length.LengthUnit.INCHES);

        Length length = new Length(2.0, Length.LengthUnit.YARDS);
        demonstrateLengthConversion(length, Length.LengthUnit.FEET);

        demonstrateLengthEquality(
                new Length(1.0, Length.LengthUnit.FEET),
                new Length(12.0, Length.LengthUnit.INCHES)
        );
    }
}