package com.apps.quantitymeasurementapp;

public class QuantityMeasurementApp {

    public static Length demonstrateLengthAddition(Length l1, Length l2) {
        Length result = l1.add(l2);
        System.out.println("add(" + l1 + ", " + l2 + ") = " + result);
        return result;
    }

    public static void main(String[] args) {

        demonstrateLengthAddition(
                new Length(1.0, Length.LengthUnit.FEET),
                new Length(2.0, Length.LengthUnit.FEET));

        demonstrateLengthAddition(
                new Length(1.0, Length.LengthUnit.FEET),
                new Length(12.0, Length.LengthUnit.INCHES));

        demonstrateLengthAddition(
                new Length(12.0, Length.LengthUnit.INCHES),
                new Length(1.0, Length.LengthUnit.FEET));

        demonstrateLengthAddition(
                new Length(1.0, Length.LengthUnit.YARDS),
                new Length(3.0, Length.LengthUnit.FEET));

        demonstrateLengthAddition(
                new Length(2.54, Length.LengthUnit.CENTIMETERS),
                new Length(1.0, Length.LengthUnit.INCHES));

        demonstrateLengthAddition(
                new Length(5.0, Length.LengthUnit.FEET),
                new Length(0.0, Length.LengthUnit.INCHES));

        demonstrateLengthAddition(
                new Length(5.0, Length.LengthUnit.FEET),
                new Length(-2.0, Length.LengthUnit.FEET));
    }
}