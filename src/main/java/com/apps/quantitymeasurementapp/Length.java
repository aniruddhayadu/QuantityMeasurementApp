package com.apps.quantitymeasurement;

public class Length {
    private double value;
    private LengthUnit unit;

    // Enum for supported units (UC3: only FEET and INCHES)
    public enum LengthUnit {
        FEET(12.0),     // 1 foot = 12 inches
        INCHES(1.0);    // base unit = inches
    	

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }
        
        
        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    // Constructor
    public Length(double value, LengthUnit unit) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Value must be a valid number");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }

    // Convert to base unit (inches)
    private double convertToBaseUnit() {
        return value * unit.getConversionFactor();
    }

    // Compare lengths
    public boolean compare(Length other) {
        if (other == null) return false;
        return Double.compare(this.convertToBaseUnit(), other.convertToBaseUnit()) == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Length)) return false;
        Length that = (Length) o;
        return compare(that);
    }

    @Override
    public int hashCode() {
        return Double.valueOf(convertToBaseUnit()).hashCode();
    }
}