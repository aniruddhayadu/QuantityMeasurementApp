package com.apps.quantitymeasurementapp;

public class QuantityMeasurementApp {
	
	//generic method
	public static <U extends IMeasurable> boolean demonstrateEquality(Quantity<U> q1, Quantity<U> q2) throws IllegalArgumentException {
		if(q1==null || q2 ==null) {
			throw new IllegalArgumentException("Null Object!");
		}
		return q1.equals(q2);
	}
	
	public static <U extends IMeasurable> Quantity<U> demonstrateConversion(Quantity<U> q, U targetUnit) throws IllegalArgumentException{
		if(q==null || targetUnit ==null) {
			throw new IllegalArgumentException("Null Object!");
		}
		return new Quantity<>(q.convertTo(targetUnit), targetUnit);
	}
	
	public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> q1, Quantity<U> q2) throws IllegalArgumentException{
		if(q1==null || q2 ==null) {
			throw new IllegalArgumentException("Null Object!");
		}
		return q1.add(q2);
	}
	
	public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> q1, Quantity<U> q2, U targetUnit) throws IllegalArgumentException{
		if(q1==null || q2 ==null || targetUnit==null) {
			throw new IllegalArgumentException("Null Object!");
		}
		return q1.add(q2, targetUnit);
	}
	
	public static void main(String[] args) throws IllegalArgumentException {		
		//Domonstrate Feet to Inches
		System.out.println(demonstrateEquality(new Quantity<>(1,LengthUnit.FEET), new Quantity<>(12,LengthUnit.INCHES)));
		
		
		//Domonstrate Yard to Inch
		System.out.println(demonstrateEquality(new Quantity<>(1.0,LengthUnit.YARD), new Quantity<>(36.0,LengthUnit.INCHES)));
		
		//Domonstrate Centimeter to Inch
		System.out.println(demonstrateEquality(new Quantity<>(100.0,LengthUnit.CENTIMETERS), new Quantity<>(39.37,LengthUnit.INCHES)));
		
		//Domonstrate Feet to Yard
		System.out.println(demonstrateEquality(new Quantity<>(3.0,LengthUnit.FEET), new Quantity<>(1.0,LengthUnit.YARD)));
		
		//Domonstrate Centimeter to feet
		System.out.println(demonstrateEquality(new Quantity<>(30.48,LengthUnit.CENTIMETERS), new Quantity<>(1.0,LengthUnit.FEET)));
		
		//Input: convert(1.0, FEET, INCHES) → Output: 12.0
		System.out.println("Convert Feet to Inches: "+(demonstrateConversion(new Quantity<>(1.0, LengthUnit.FEET), LengthUnit.INCHES)));
		//Input: convert(3.0, YARDS, FEET) → Output: 9.0
		System.out.println("Convert Yard to Feet: "+(demonstrateConversion(new Quantity<>(3.0, LengthUnit.YARD), LengthUnit.FEET)));
		//Input: convert(36.0, INCHES, YARDS) → Output: 1.0
		System.out.println("Convert Inches to Yard: "+(demonstrateConversion(new Quantity<>(36.0, LengthUnit.INCHES), LengthUnit.YARD)));
		//Input: convert(1.0, CENTIMETERS, INCHES) → Output: ~0.393701
		System.out.println("Convert Centimeter to Inches: "+(demonstrateConversion(new Quantity<>(1.0, LengthUnit.CENTIMETERS), LengthUnit.INCHES)));
		//Input: convert(0.0, FEET, INCHES) → Output: 0.0
		System.out.println("Convert Feet to Inches: "+(demonstrateConversion(new Quantity<>(0.0, LengthUnit.FEET), LengthUnit.INCHES)));
		

		//Input: add(Quantity(1.0, FEET), Quantity(12.0, INCHES), FEET) → Output: Quantity(2.0, FEET)
		System.out.println("Addition to Feet: "+(demonstrateAddition(new Quantity<>(1.0,LengthUnit.FEET), new Quantity<>(36.0,LengthUnit.INCHES), LengthUnit.FEET)));
		
		//Input: add(Quantity(1.0, FEET), Quantity(12.0, INCHES), INCHES) → Output: Quantity(24.0, INCHES)
		System.out.println("Addition to Inches: "+(demonstrateAddition(new Quantity<>(1.0,LengthUnit.FEET), new Quantity<>(12.0,LengthUnit.INCHES), LengthUnit.INCHES)));

		//Input: add(Quantity(1.0, FEET), Quantity(12.0, INCHES), YARDS) → Output: Quantity(~0.667, YARDS)
		System.out.println("Addition to Yards: "+(demonstrateAddition(new Quantity<>(1.0,LengthUnit.FEET), new Quantity<>(12.0,LengthUnit.INCHES), LengthUnit.YARD)));

		//Input: add(Quantity(1.0, YARDS), Quantity(3.0, FEET), YARDS) → Output: Quantity(2.0, YARDS)
		System.out.println("Addition to Yards: "+(demonstrateAddition(new Quantity<>(1.0,LengthUnit.YARD), new Quantity<>(3.0,LengthUnit.FEET), LengthUnit.YARD)));

		//Input: add(Quantity(36.0, INCHES), Quantity(1.0, YARDS), FEET) → Output: Quantity(6.0, FEET)
		System.out.println("Addition to Feet: "+(demonstrateAddition(new Quantity<>(36.0,LengthUnit.INCHES), new Quantity<>(1.0,LengthUnit.YARD), LengthUnit.FEET)));

		//Input: add(Quantity(2.54, CENTIMETERS), Quantity(1.0, INCHES), CENTIMETERS) → Output: Quantity(~5.08, CENTIMETERS)
		System.out.println("Addition to Centimeters: "+(demonstrateAddition(new Quantity<>(2.54,LengthUnit.CENTIMETERS), new Quantity<>(1.0,LengthUnit.INCHES), LengthUnit.CENTIMETERS)));

		//Input: add(Quantity(5.0, FEET), Quantity(0.0, INCHES), YARDS) → Output: Quantity(~1.667, YARDS)
		System.out.println("Addition to Yards: "+(demonstrateAddition(new Quantity<>(5.0,LengthUnit.FEET), new Quantity<>(0.0,LengthUnit.INCHES), LengthUnit.YARD)));

		//Input: add(Quantity(5.0, FEET), Quantity(-2.0, FEET), INCHES) → Output: Quantity(36.0, INCHES)
		System.out.println("Addition to Inches: "+(demonstrateAddition(new Quantity<>(5.0,LengthUnit.FEET), new Quantity<>(-2.0,LengthUnit.INCHES), LengthUnit.INCHES)));
		
		
		//Input: Quantity(1.0, FEET).convertTo(INCHES) → Output: Quantity(12.0, INCHES)
		System.out.println("Convert Feet to Inches: "+(demonstrateConversion(new Quantity<>(1.0, LengthUnit.FEET), LengthUnit.INCHES)));
		
		//Input: Quantity(1.0, FEET).add(Quantity(12.0, INCHES), FEET) → Output: Quantity(2.0, FEET)
		System.out.println("Addition to Feet: "+(demonstrateAddition(new Quantity<>(1.0,LengthUnit.FEET), new Quantity<>(36.0,LengthUnit.INCHES), LengthUnit.FEET)));

		//Input: Quantity(36.0, INCHES).equals(Quantity(1.0, YARDS)) → Output: true
		System.out.println("36.0 Inches Equals to 1.0 Yards: "+demonstrateEquality(new Quantity<>(36.0,LengthUnit.INCHES), new Quantity<>(1.0,LengthUnit.YARD)));
		
		//Input: Quantity(1.0, YARDS).add(Quantity(3.0, FEET), YARDS) → Output: Quantity(2.0, YARDS)
		System.out.println("Addition to Yards: "+(demonstrateAddition(new Quantity<>(1.0,LengthUnit.YARD), new Quantity<>(3.0,LengthUnit.FEET), LengthUnit.YARD)));
		
		//Input: Quantity(2.54, CENTIMETERS).convertTo(INCHES) → Output: Quantity(~1.0, INCHES) (within epsilon)
		System.out.println("Convert Feet to Inches: "+(demonstrateConversion(new Quantity<>(2.54, LengthUnit.CENTIMETERS), LengthUnit.INCHES)));

		//Input: Quantity(5.0, FEET).add(Quantity(0.0, INCHES), FEET) → Output: Quantity(5.0, FEET)
		System.out.println("Addition to Feets: "+(demonstrateAddition(new Quantity<>(5.0,LengthUnit.FEET), new Quantity<>(0.0,LengthUnit.INCHES), LengthUnit.FEET)));

		//Input: LengthUnit.FEET.convertToBaseUnit(12.0) → Output: 12.0 (already in base unit)
		System.out.println("Convert Feet to Feets: "+(demonstrateConversion(new Quantity<>(1.0, LengthUnit.FEET), LengthUnit.FEET)));
		
		//Input: LengthUnit.INCHES.convertToBaseUnit(12.0) → Output: 1.0 (converted to feet)
		System.out.println("Convert Feet to Inches: "+(demonstrateConversion(new Quantity<>(36.0, LengthUnit.INCHES), LengthUnit.FEET)));
		
		//Input: Weight(1.0, KILOGRAM).equals(Weight(1.0, KILOGRAM)) → Output: true
		System.out.println("1 KG Equals 1 KG: " +
		        demonstrateEquality(
		                new Quantity<>(1.0, WeightUnit.KILOGRAM),
		                new Quantity<>(1.0, WeightUnit.KILOGRAM)));

		//Input: Weight(1.0, KILOGRAM).equals(Weight(1000.0, GRAM)) → Output: true
		System.out.println("1 KG Equals 1000 GRAM: " +
		        demonstrateEquality(
		                new Quantity<>(1.0, WeightUnit.KILOGRAM),
		                new Quantity<>(1000.0, WeightUnit.GRAM)));

		//Input: Weight(1.0, KILOGRAM).equals(Weight(2.20462, POUND)) → Output: true (epsilon)
		System.out.println("1 KG Equals 2.20462 POUND: " +
		        demonstrateEquality(
		                new Quantity<>(1.0, WeightUnit.KILOGRAM),
		                new Quantity<>(2.20462, WeightUnit.POUND)));

		//Input: Weight(500.0, GRAM).equals(Weight(0.5, KILOGRAM)) → Output: true
		System.out.println("500 GRAM Equals 0.5 KG: " +
		        demonstrateEquality(
		                new Quantity<>(500.0, WeightUnit.GRAM),
		                new Quantity<>(0.5, WeightUnit.KILOGRAM)));
		
		//Input: Weight(1.0, KILOGRAM).convertTo(GRAM)
		System.out.println("Convert KG to GRAM: " +
		        demonstrateConversion( new Quantity<>(1.0,
		                WeightUnit.KILOGRAM),
		                WeightUnit.GRAM));

		//Input: Weight(2.0, POUND).convertTo(KILOGRAM)
		System.out.println("Convert POUND to KG: " +
		        demonstrateConversion( new Quantity<>(2.0,
		                WeightUnit.POUND),
		                WeightUnit.KILOGRAM));

		//Input: Weight(500.0, GRAM).convertTo(POUND)
		System.out.println("Convert GRAM to POUND: " +
		        demonstrateConversion(new Quantity<>(500.0,
		                WeightUnit.GRAM),
		                WeightUnit.POUND));
		
		//Input: Weight(1.0, KILOGRAM).add(Weight(1000.0, GRAM))
		System.out.println("Add KG + GRAM: " +
		        demonstrateAddition(
		                new Quantity<>(1.0, WeightUnit.KILOGRAM),
		                new Quantity<>(1000.0, WeightUnit.GRAM)));
		
		//Input: Weight(1.0, KILOGRAM).add(Weight(1000.0, GRAM), GRAM)
		System.out.println("Add to GRAM: " +
		        demonstrateAddition(
		                new Quantity<>(1.0, WeightUnit.KILOGRAM),
		                new Quantity<>(1000.0, WeightUnit.GRAM),
		                WeightUnit.GRAM));

		//Input: Weight(2.0, KILOGRAM).add(Weight(4.0, POUND), KILOGRAM)
		System.out.println("Add KG + POUND to KG: " +
		        demonstrateAddition(
		                new Quantity<>(2.0, WeightUnit.KILOGRAM),
			                new Quantity<>(4.0, WeightUnit.POUND),
			                	WeightUnit.KILOGRAM));
	}
}