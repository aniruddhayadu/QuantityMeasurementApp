package com.apps.quantitymeasurementapp;

import java.util.Scanner;

public class QuantityMeasurementApp {

    public static class Feet {
        private final double value;

        public Feet(double value) {
            if (Double.isNaN(value) || Double.isInfinite(value)) {
                throw new IllegalArgumentException("Feet value must be a valid number");
            }
            this.value = value;
        }

        public double getValue() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Feet)) return false;
            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }


    public static class Inches {
        private final double value;

        public Inches(double value) {
            if (Double.isNaN(value) || Double.isInfinite(value)) {
                throw new IllegalArgumentException("Inches value must be a valid number");
            }
            this.value = value;
        }

        public double getValue() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Inches)) return false;
            Inches other = (Inches) obj;
            return Double.compare(this.value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }


    public static boolean compareFeet(double first, double second) {
        return new Feet(first).equals(new Feet(second));
    }

    public static boolean compareInches(double first, double second) {
        return new Inches(first).equals(new Inches(second));
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Feet comparison
            System.out.print("Enter first value in feet: ");
            double feet1 = scanner.nextDouble();
            System.out.print("Enter second value in feet: ");
            double feet2 = scanner.nextDouble();
            System.out.println("Feet comparison result: " + compareFeet(feet1, feet2));

            // Inches comparison
            System.out.print("Enter first value in inches: ");
            double inch1 = scanner.nextDouble();
            System.out.print("Enter second value in inches: ");
            double inch2 = scanner.nextDouble();
            System.out.println("Inches comparison result: " + compareInches(inch1, inch2));

        } catch (Exception e) {
            System.out.println("Invalid input! Please enter numeric values only.");
        } finally {
            scanner.close();
        }
    }
}