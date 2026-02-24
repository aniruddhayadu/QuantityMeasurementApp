package com.apps.quantitymeasurementapp;

public class Quantity<U extends IMeasurable> {
	private double value;
	private U unit;
	
	public Quantity(double value, U unit) {
		if(Double.isNaN(value)) {
			throw new IllegalArgumentException("Nan value!");
		}
		if(unit==null) {
			throw new IllegalArgumentException("Nan value!");			
		}
		this.value = value;
		this.unit = unit;
	}

	public double getValue() {
		return value;
	}

	public U getUnit() {
		return unit;
	}
	
	public double convertTo(U targetUnit) {
		//convert to base unit
		double baseUnit = this.unit.convertToBaseUnit(value);
		//convert to target unit
		double targetUnits = targetUnit.convertFromBaseUnit(baseUnit);
		return targetUnits;
		
	}
	
	//add
	public Quantity<U> add(Quantity<U> other){
		double part1 = this.convertTo(this.unit);
		double part2 = other.convertTo(this.unit);
		return new Quantity<>((part1+part2),this.unit);
	}
	//add with target unit
	public Quantity<U> add(Quantity<U> other, U targetUnit){
		double part1 = this.convertTo(targetUnit);
		double part2 = other.convertTo(targetUnit);
		return new Quantity<>((part1+part2),targetUnit);
	}
	
	//equals
	@Override
	public boolean equals(Object obj) {
		if(this==obj) {
			return true;
		}
		
		if(obj==null || obj.getClass()!=this.getClass()) {
			return false;
		}
		
		// Generic cast (Suppressed warning because we checked getClass() above)
	    @SuppressWarnings("unchecked")
	    Quantity<U> other = (Quantity<U>) obj;

	    // Conversion Logic: Convert both to their Base Unit for comparison
	    double baseValue1 = this.unit.convertToBaseUnit(this.value);
	    double baseValue2 = other.unit.convertToBaseUnit(other.value);

	    // Value comparison with a small delta for double precision errors
	    return Math.abs(baseValue1 - baseValue2) < 0.0001;
	}
	// Always override hashCode when overriding equals
	@Override
	public int hashCode() {
	    double baseValue = this.unit.convertToBaseUnit(this.value);
	    return Double.hashCode(baseValue);
	}
	
	@Override
	public String toString() {
		return "Quantity [value=" + value + ", unit=" + unit + "]";
	}

	public static void main(String args[]) {
		Quantity<LengthUnit> unit1 = new Quantity<>(3.0,LengthUnit.FEET);
		System.out.println(unit1.convertTo(LengthUnit.INCHES));
		
		Quantity<LengthUnit> unit2 = new Quantity<>(24.0,LengthUnit.INCHES);
		Quantity<LengthUnit> unit3 = new Quantity<>(24.0,LengthUnit.INCHES);
		System.out.println(unit2.add(unit3));
		
		System.out.println(unit2.add(unit3, LengthUnit.FEET));
	}
}