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
	
	//subtract method
	public Quantity<U> subtract(Quantity<U> other){
		double part1 = this.unit.convertToBaseUnit(this.value);
		double part2 = other.unit.convertToBaseUnit(other.value);
		
		double baseResult = part1-part2;
		double finalResult = this.unit.convertFromBaseUnit(baseResult);
		return new Quantity<>(finalResult,this.unit);
	}
	
	//subtract method for specific unit
	public Quantity<U> subtract(Quantity<U> other, U targetUnit){
		double part1 = this.unit.convertToBaseUnit(this.value);
		double part2 = other.unit.convertToBaseUnit(other.value);
		
		double baseResult = part1-part2;
		double finalResult = targetUnit.convertFromBaseUnit(baseResult);
		return new Quantity<>(finalResult,targetUnit);
	}
	
	//division
	public double divide(Quantity<U> other){
		double part1 = this.unit.convertToBaseUnit(this.value);
		double part2 = other.unit.convertToBaseUnit(other.value);
		if (part2 == 0) {
	        throw new ArithmeticException("Cannot divide by zero");
		}
		double baseResult = part1/part2;
		return baseResult;
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
	
	@Override
	public String toString() {
		return "Quantity [value=" + value + ", unit=" + unit + "]";
	}

	public static void main(String args[]) {
		Quantity<LengthUnit> unit1 = new Quantity<>(3.0,LengthUnit.FEET);
		System.out.println(unit1.convertTo(LengthUnit.INCHES));
		
		Quantity<LengthUnit> unit2 = new Quantity<>(48.0,LengthUnit.INCHES);
		Quantity<LengthUnit> unit3 = new Quantity<>(24.0,LengthUnit.INCHES);
		System.out.println(unit2.add(unit3));
		
		System.out.println(unit2.add(unit3, LengthUnit.FEET));
		
		//subtract
		System.out.println(unit1.subtract(unit2));
		System.out.println(unit1.subtract(unit2,LengthUnit.INCHES));
		
		//division
		System.out.println(unit1.divide(unit3));
	}
}