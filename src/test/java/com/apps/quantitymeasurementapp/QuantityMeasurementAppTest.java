package com.apps.quantitymeasurementapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class QuantityMeasurementAppTest<U extends IMeasurable> {
	
	Quantity<LengthUnit> len1;
	Quantity<LengthUnit> len2;
	
	Quantity<WeightUnit> w1;
	Quantity<WeightUnit> w2;
	
	
	@Test
    public void testEquality_SameUnitAndValue_ShouldReturnTrue() {
        Quantity<LengthUnit> feet1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> feet2 = new Quantity<>(10.0, LengthUnit.FEET);
        
        // Using the new Generic App Logic
        assertTrue(QuantityMeasurementApp.demonstrateEquality(feet1, feet2));
    }

    @Test
    public void testEquality_DifferentUnitsSameValue_ShouldReturnTrue() {
        Quantity<LengthUnit> oneFeet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> twelveInches = new Quantity<>(12.0, LengthUnit.INCHES);
        
        assertTrue(QuantityMeasurementApp.demonstrateEquality(oneFeet, twelveInches));
    }

    @Test
    public void testEquality_DifferentValues_ShouldReturnFalse()  {
        Quantity<LengthUnit> feet1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> feet2 = new Quantity<>(1.1, LengthUnit.FEET);
        
        assertFalse(QuantityMeasurementApp.demonstrateEquality(feet1, feet2));
    }

    @Test
    public void testEquality_NullComparison_ShouldThrowException() {
        Quantity<LengthUnit> feet1 = new Quantity<>(1.0, LengthUnit.FEET);
        
        assertThrows(IllegalArgumentException.class, () -> {
            QuantityMeasurementApp.demonstrateEquality(feet1, null);
        });
    }

    @Test
    public void testEquality_SameReference_ShouldReturnTrue()  {
        Quantity<LengthUnit> feet1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> feet2 = feet1;
        
        assertTrue(QuantityMeasurementApp.demonstrateEquality(feet1, feet2));
    }

    // --- Conversion Tests ---

    @Test
    public void testConversion_YardToInches() {
        Quantity<LengthUnit> yard = new Quantity<>(1.0, LengthUnit.YARD);
        Quantity<LengthUnit> result = QuantityMeasurementApp.demonstrateConversion(yard, LengthUnit.INCHES);
        
        assertEquals(36.0, result.getValue(), 0.01);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    public void testConversion_FeetToCm() {
        Quantity<LengthUnit> foot = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = QuantityMeasurementApp.demonstrateConversion(foot, LengthUnit.CENTIMETERS);
        
        assertEquals(30.48, result.getValue(), 0.01);
    }

    // --- Addition Tests ---

    @Test
    public void testAddition_TwoDifferentUnits() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET); // 12 inches
        Quantity<LengthUnit> inches = new Quantity<>(2.0, LengthUnit.INCHES);
        
        // Assuming demonstrateAddition returns sum in a default or base unit
        Quantity<LengthUnit> sum = QuantityMeasurementApp.demonstrateAddition(feet, inches);
        Quantity<LengthUnit> target = new Quantity<>(14.0, LengthUnit.INCHES);
        // If the sum converts both to inches: 12 + 2 = 14
        assertTrue(sum.equals(target));
    }

    @ParameterizedTest
    @ValueSource(doubles = {1.0, 2.0, 5.0})
    public void testMultipleFeetToInchesEquality(double value) {
        Quantity<LengthUnit> feet = new Quantity<>(value, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(value * 12, LengthUnit.INCHES);
        
        assertTrue(QuantityMeasurementApp.demonstrateEquality(feet, inches));
    }
	
	// Yard

	@Test
	public void yardEquals36Inches() {
	    assertTrue(new Quantity<LengthUnit>(1.0, LengthUnit.YARD)
	            .equals(new Quantity<LengthUnit>(36.0, LengthUnit.INCHES)));
	}

	@Test
	public void centimeterEquals39Point3701Inches() {
	    assertTrue(new Quantity<LengthUnit>(100.0, LengthUnit.CENTIMETERS)
	            .equals(new Quantity<LengthUnit>(39.37, LengthUnit.INCHES)));
	}

	@Test
	public void threeFeetEqualsOneYard() {
	    assertTrue(new Quantity<LengthUnit>(3.0, LengthUnit.FEET)
	            .equals(new Quantity<LengthUnit>(1.0, LengthUnit.YARD)));        
	}

	@Test
	public void thirtyPoint48CmEqualsOneFoot() {
	    assertTrue(new Quantity<LengthUnit>(30.48, LengthUnit.CENTIMETERS)
	            .equals(new Quantity<LengthUnit>(1.0, LengthUnit.FEET)));
	}

	@Test
	public void yardNotEqualToInches() {
	    assertFalse(new Quantity<LengthUnit>(100.0, LengthUnit.YARD)
	            .equals(new Quantity<LengthUnit>(39.37, LengthUnit.INCHES)));
	}

	@Test
	public void referenceEqualitySameObject() {
	    len1 = new Quantity<LengthUnit>(1.0, LengthUnit.YARD);
	    len2 = len1;
	    assertTrue(len1.equals(len1));
	}

	@Test
	public void equalsReturnsFalseForNull() {
	    len1 = new Quantity<LengthUnit>(1.0, LengthUnit.YARD);
	    assertFalse(len1.equals(null));
	}

	@Test
	public void reflexiveSymmetricAndTransitiveProperty() {
		Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.YARD);
		Quantity<LengthUnit> b = new Quantity<>(3.0, LengthUnit.FEET);
		Quantity<LengthUnit> c = new Quantity<>(36.0, LengthUnit.INCHES);

	    // Reflexive
	    assertTrue(a.equals(a));

	    // Symmetric
	    assertTrue(a.equals(b));
	    assertTrue(b.equals(a));

	    // Transitive
	    assertTrue(a.equals(b));
	    assertTrue(b.equals(c));
	    assertTrue(a.equals(c));
	}

	@Test
	public void differentValuesSameUnitNotEqual() {
	    assertFalse(new Quantity<LengthUnit>(10.5, LengthUnit.FEET)
	            .equals(new Quantity<LengthUnit>(123.2, LengthUnit.FEET)));
	}

	@Test
	public void crossUnitEqualityDemonstrateMethod() {
		Quantity<LengthUnit> yard = new Quantity<LengthUnit>(1.0, LengthUnit.YARD);
		Quantity<LengthUnit> inch = new Quantity<LengthUnit>(36.0, LengthUnit.INCHES);

	    assertTrue(yard.equals(inch));
	}

	@Test
	public void convertFeetToInches()  {
		Quantity<LengthUnit> lengthInches = QuantityMeasurementApp
	            .demonstrateConversion(new Quantity<>(3.0, LengthUnit.FEET), LengthUnit.INCHES);
		Quantity<LengthUnit> expectedInches = new Quantity<LengthUnit>(36.0, LengthUnit.INCHES);
	    assertTrue(QuantityMeasurementApp
	            .demonstrateEquality(lengthInches, expectedInches));
	}

	@Test
	public void convertYardToInchesUsingOverloadMethod()  {
		Quantity<LengthUnit> lengthInYard = new Quantity<LengthUnit>(2.0, LengthUnit.YARD);
		Quantity<LengthUnit> lengthInInches = QuantityMeasurementApp
	            .demonstrateConversion(lengthInYard, LengthUnit.INCHES);
		Quantity<LengthUnit> expected = new Quantity<LengthUnit>(72.0, LengthUnit.INCHES);
	    assertTrue(QuantityMeasurementApp
	            .demonstrateEquality(lengthInInches, expected));
	}

	@Test
	public void addFeetAndInches()  {
		Quantity<LengthUnit> Feet = new Quantity<>(1.0, LengthUnit.FEET);
	    Quantity<LengthUnit> Inches = new Quantity<>(24.0, LengthUnit.INCHES);
	    Quantity<LengthUnit> output = QuantityMeasurementApp.demonstrateAddition(Feet, Inches);
	    Quantity<LengthUnit> result = new Quantity<>(3.0, LengthUnit.FEET);
	    assertTrue(output.equals(result));
	}

	@Test
	void addFeetAndInchesWithTargetUnitInches()  {
		Quantity<LengthUnit> Feet = new Quantity<>(1.0, LengthUnit.FEET);
	    Quantity<LengthUnit> Inches = new Quantity<>(24.0, LengthUnit.INCHES);
	    Quantity<LengthUnit> output = QuantityMeasurementApp
	            .demonstrateAddition(Feet, Inches, LengthUnit.INCHES);
	    Quantity<LengthUnit> result = new Quantity<>(3.0, LengthUnit.FEET);
	    assertTrue(output.equals(result));
	}
	


	//Verifies that Quantity(1.0, KILOGRAM).equals(Quantity(1.0, KILOGRAM)) returns true.
	//Tests: equals() returns true for identical kilogram measurements.
	@Test
	public void testEquality_KilogramToKilogram_SameValue(){
		w1 = new Quantity<>(1.0,WeightUnit.KILOGRAM);
		w2 = new Quantity<>(1.0,WeightUnit.KILOGRAM);
		assertTrue(w1.equals(w2));
	}
	

	//Verifies that Quantity(1.0, KILOGRAM).equals(Quantity(2.0, KILOGRAM)) returns false.
	//Tests: equals() returns false for different kilogram measurements.
	@Test
	public void testEquality_KilogramToKilogram_DifferentValue() {
		w1 = new Quantity<>(12.0,WeightUnit.KILOGRAM);
		w2 = new Quantity<>(1.0,WeightUnit.KILOGRAM);
		assertFalse(w1.equals(w2));
	}


	//Verifies that Quantity(1.0, KILOGRAM).equals(Quantity(1000.0, GRAM)) returns true.
	//Tests: equals() returns true for kilogram-to-gram conversion.
	@Test
	public void testEquality_KilogramToGram_EquivalentValue(){
		w1 = new Quantity<>(12.0,WeightUnit.KILOGRAM);
		w2 = new Quantity<>(12000.0,WeightUnit.GRAM);
		assertTrue(w1.equals(w2));
	}

	//Verifies that Quantity(1000.0, GRAM).equals(Quantity(1.0, KILOGRAM)) returns true.
	//Tests: equals() returns true (tests symmetry of conversion).
	@Test
	public void testEquality_GramToKilogram_EquivalentValue() {
		w1 = new Quantity<>(12000.0,WeightUnit.GRAM);
		w2 = new Quantity<>(12.0,WeightUnit.KILOGRAM);
		assertTrue(w1.equals(w2));
	}

	//Verifies that Quantity(1.0, KILOGRAM).equals(Quantity(1.0, FOOT)) returns false.
	//Tests: equals() returns false when comparing incompatible measurement categories.
	@Test
	public void testEquality_WeightVsLength_Incompatible() {
		w1 = new Quantity<>(10.0,WeightUnit.KILOGRAM);
		len1 = new Quantity<>(1.0,LengthUnit.FEET);
		assertFalse(w1.equals(w2));
	}

	//Verifies that Quantity(1.0, KILOGRAM).equals(null) returns false.
	//Tests: equals() returns false when comparing with null.
	@Test
	public void testEquality_NullComparison() {
		w1 = new Quantity<>(10.0,WeightUnit.KILOGRAM);
		assertFalse(w1.equals(null));
	}

	//Verifies that a weight object equals itself (reflexive property).
	//Tests: equals() returns true when comparing an object with itself.
	@Test
	public void testEquality_SameReference() {
		w1 = new Quantity<>(12000.0,WeightUnit.GRAM);
		w2 = w1;
		assertTrue(w1.equals(w2));
	}

	//Verifies that Quantity(1.0, null) throws IllegalArgumentException.
	//Tests: Exception thrown for null unit in constructor.
	@Test
	public void testEquality_NullUnit() {
		assertThrows(IllegalArgumentException.class,()-> {
			w1 = new Quantity<>(12000.0,null);
		});
	}

	//Verifies transitive property: if A equals B and B equals C, then A equals C.
	//Example: Quantity(1.0, KILOGRAM) equals Quantity(1000.0, GRAM) and Quantity(1000.0, GRAM) equals Quantity(1.0, KILOGRAM), therefore Quantity(1.0, KILOGRAM) equals Quantity(1.0, KILOGRAM).
	@Test
	public void testEquality_TransitiveProperty() {
		w1 = new Quantity<>(12000.0,WeightUnit.GRAM);
		w2 = new Quantity<>(12.0,WeightUnit.KILOGRAM);
		assertTrue((w1.equals(w2)) && (w2.equals(w1)));
	}
	

	//Verifies that Quantity(0.0, KILOGRAM).equals(Quantity(0.0, GRAM)) returns true.
	//Tests: Zero values are considered equal across units.
	@Test
	public void testEquality_ZeroValue() {
		w1 = new Quantity<>(0.0,WeightUnit.KILOGRAM);
		w2 = new Quantity<>(0.0,WeightUnit.GRAM);
		assertTrue(w1.equals(w2));
	}

	//Verifies that Quantity(-1.0, KILOGRAM).equals(Quantity(-1000.0, GRAM)) returns true.
	//Tests: Negative weight values are handled correctly in conversions.
	@Test
	public void testEquality_NegativeWeight() {
		w1 = new Quantity<>(-1.0,WeightUnit.KILOGRAM);
		w2 = new Quantity<>(-1000.0,WeightUnit.GRAM);
		assertTrue(w1.equals(w2));
	}

	
	//Verifies that Quantity(1000000.0, GRAM).equals(Quantity(1000.0, KILOGRAM)) returns true.
	//Tests: Large magnitude values maintain precision across conversions.
	@Test
	public void testEquality_LargeWeightValue() {
		w1 = new Quantity<>(1000000.0,WeightUnit.GRAM);
		w2 = new Quantity<>(1000.0,WeightUnit.KILOGRAM);
		assertTrue(w1.equals(w2));
	}

	//Verifies that Quantity(0.001, KILOGRAM).equals(Quantity(1.0, GRAM)) returns true.
	//Tests: Small magnitude values maintain precision across conversions.
	@Test
	public void testEquality_SmallWeightValue() {
		w1 = new Quantity<>(0.001,WeightUnit.KILOGRAM);
		w2 = new Quantity<>(1.0,WeightUnit.GRAM);
		assertTrue(w1.equals(w2));
	}

	//Verifies that Quantity(2.20462, POUND).convertTo(KILOGRAM) returns Quantity(~1.0, KILOGRAM) (within epsilon).
	//Tests: Conversion from pound to kilogram.
	@Test
	public void testConversion_PoundToKilogram() {
		w1 = new Quantity<>(2.20462,WeightUnit.POUND);
		Quantity<WeightUnit> output = QuantityMeasurementApp.demonstrateConversion(w1, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> result = new Quantity<>(1.0,WeightUnit.KILOGRAM);
		assertTrue(output.equals(result));
	}

	//Verifies that Quantity(1.0, KILOGRAM).convertTo(POUND) returns Quantity(~2.20462, POUND) (within epsilon).
	//Tests: Conversion from kilogram to pound.
	@Test
	public void testConversion_KilogramToPound() {
		w1 = new Quantity<>(1.0,WeightUnit.KILOGRAM);
		Quantity<WeightUnit> output = QuantityMeasurementApp.demonstrateConversion(w1, WeightUnit.POUND);
		Quantity<WeightUnit> result = new Quantity<>(2.20462,WeightUnit.POUND);
		assertTrue(output.equals(result));
	}

	//Verifies that Quantity(5.0, KILOGRAM).convertTo(KILOGRAM) returns Quantity(5.0, KILOGRAM).
	//Tests: Converting to the same unit returns unchanged value.
	@Test
	public void testConversion_SameUnit()  {
		w1 = new Quantity<>(5.0,WeightUnit.KILOGRAM);
		Quantity<WeightUnit> output = QuantityMeasurementApp.demonstrateConversion(w1, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> result = new Quantity<>(5.0,WeightUnit.KILOGRAM);
		assertTrue(output.equals(result));
	}

	//Verifies that Quantity(0.0, KILOGRAM).convertTo(GRAM) returns Quantity(0.0, GRAM).
	//Tests: Zero value conversion across units.
	@Test
	public void testConversion_ZeroValue() {
		w1 = new Quantity<>(0.0,WeightUnit.KILOGRAM);
		Quantity<WeightUnit> output = QuantityMeasurementApp.demonstrateConversion(w1, WeightUnit.GRAM);
		Quantity<WeightUnit> result = new Quantity<>(0.0,WeightUnit.GRAM);
		assertTrue(output.equals(result));
	}

	//Verifies that Quantity(-1.0, KILOGRAM).convertTo(GRAM) returns Quantity(-1000.0, GRAM).
	//Tests: Negative weight conversion preserves sign.
	@Test
	public void testConversion_NegativeValue() {
		w1 = new Quantity<>(-1.0,WeightUnit.KILOGRAM);
		Quantity<WeightUnit> output = QuantityMeasurementApp.demonstrateConversion(w1, WeightUnit.GRAM);
		Quantity<WeightUnit> result = new Quantity<>(-1000.0,WeightUnit.GRAM);
		assertTrue(output.equals(result));
	}

	//Verifies that Quantity (1.5, KILOGRAM).convertTo(GRAM) .convertTo(KILOGRAM) returns Quantity(~1.5, KILOGRAM) (within epsilon).
	//Tests: Round-trip conversions preserve value within floating-point tolerance.
	@Test
	public void testConversion_RoundTrip() {
		w1 = new Quantity<>(1.5,WeightUnit.KILOGRAM);
		Quantity<WeightUnit> output = QuantityMeasurementApp.demonstrateConversion(w1, WeightUnit.GRAM);
		Quantity<WeightUnit> result = new Quantity<>(1.5,WeightUnit.KILOGRAM);
		assertTrue(output.equals(result));
	}

	//Verifies that Quantity(1.0, KILOGRAM).add(Quantity(2.0, KILOGRAM)) returns Quantity(3.0, KILOGRAM).
	//Tests: Same-unit addition without conversion.
	@Test
	public void testAddition_SameUnit_KilogramPlusKilogram() {
		w1 = new Quantity<>(1.0,WeightUnit.KILOGRAM);
		w2 = new Quantity<>(2.0,WeightUnit.KILOGRAM);
		Quantity<WeightUnit> output = QuantityMeasurementApp.demonstrateAddition(w1, w2);
		Quantity<WeightUnit> result = new Quantity<>(3.0,WeightUnit.KILOGRAM);
		assertTrue(output.equals(result));
	} 

	//Verifies that Quantity(1.0, KILOGRAM).equals(Quantity(1.0, KILOGRAM)) returns true.
	//Tests: equals() returns true for identical kilogram measurements.
	@Test
	public void testAddition_CrossUnit_KilogramPlusGram() {
		w1 = new Quantity<>(1.0,WeightUnit.KILOGRAM);
		w2 = new Quantity<>(1.0,WeightUnit.KILOGRAM);
		assertTrue(w1.equals(w2));
	}

	//Verifies that Quantity(1.0, KILOGRAM).add(Quantity(1000.0, GRAM)) returns Quantity(2.0, KILOGRAM).
	//Tests: Cross-unit addition will result in the first operand's unit.
	@Test
	public void testLengthUnitEnum_InchesConstant() {
		w1 = new Quantity<>(1.0,WeightUnit.KILOGRAM);
		w2 = new Quantity<>(1000.0,WeightUnit.GRAM);
		Quantity<WeightUnit> output = QuantityMeasurementApp.demonstrateAddition(w1, w2);
		Quantity<WeightUnit> result = new Quantity<>(2.0,WeightUnit.KILOGRAM);
		assertTrue(output.equals(result));
	}

	//Verifies that Quantity(2.20462, POUND).add(Quantity(1.0, KILOGRAM)) returns Quantity(~4.40924, POUND).
	//Tests: Cross-unit addition with mixed metric and imperial units.
	@Test
	public void testAddition_CrossUnit_PoundPlusKilogram() {
		w1 = new Quantity<>(2.20462,WeightUnit.POUND);
		w2 = new Quantity<>(1.0,WeightUnit.KILOGRAM);
		Quantity<WeightUnit> output = QuantityMeasurementApp.demonstrateAddition(w1, w2);
		Quantity<WeightUnit> result = new Quantity<>(4.40924,WeightUnit.POUND);
		assertTrue(output.equals(result));
	} 

	//Verifies that Quantity(1.0, KILOGRAM).add(Quantity(1000.0, GRAM), GRAM) returns Quantity(2000.0, GRAM).
	//Tests: Explicit target unit specification in gram.
	@Test
	public void testAddition_ExplicitTargetUnit_Kilogram() {
		w1 = new Quantity<>(1.0,WeightUnit.KILOGRAM);
		w2 = new Quantity<>(1000.0,WeightUnit.GRAM);
		Quantity<WeightUnit> output = QuantityMeasurementApp.demonstrateAddition(w1, w2, WeightUnit.GRAM);
		Quantity<WeightUnit> result = new Quantity<>(2000.0,WeightUnit.GRAM);
		assertTrue(output.equals(result));
	}

	//Verifies that Quantity(1.0, KILOGRAM).add(Quantity(1000.0, GRAM)) equals Quantity(1000.0, GRAM).add(Quantity(1.0, KILOGRAM)) in their respective units.
	//Tests: Addition is commutative with appropriate unit conversions.
	@Test
	public void testAddition_Commutativity() {
		w1 = new Quantity<>(1.0,WeightUnit.KILOGRAM);
		w2 = new Quantity<>(1000.0,WeightUnit.GRAM);
		Quantity<WeightUnit> output1 = QuantityMeasurementApp.demonstrateAddition(w1, w2);
		Quantity<WeightUnit> output2 = QuantityMeasurementApp.demonstrateAddition(w2, w1);
		assertTrue(output1.equals(output2));
	}

	//Verifies that Quantity(5.0, KILOGRAM).add(Quantity(0.0, GRAM)) returns Quantity(5.0, KILOGRAM).
	//Tests: Adding zero acts as an identity element.
	@Test
	public void testAddition_WithZero() {
		w1 = new Quantity<>(5.0,WeightUnit.KILOGRAM);
		w2 = new Quantity<>(0.0,WeightUnit.GRAM);
		Quantity<WeightUnit> output1 = QuantityMeasurementApp.demonstrateAddition(w1, w2);
		Quantity<WeightUnit> result = new Quantity<>(5.0,WeightUnit.KILOGRAM);
		assertTrue(output1.equals(result));
	}

	//Verifies that Quantity(5.0, KILOGRAM).add(Quantity(-2000.0, GRAM)) returns Quantity(3.0, KILOGRAM).
	//Tests: Addition with negative measurements.
	@Test
	public void testAddition_NegativeValues() {
		w1 = new Quantity<>(5.0,WeightUnit.KILOGRAM);
		w2 = new Quantity<>(-2000.0,WeightUnit.GRAM);
		Quantity<WeightUnit> output1 = QuantityMeasurementApp.demonstrateAddition(w1, w2);
		Quantity<WeightUnit> result = new Quantity<>(3.0,WeightUnit.KILOGRAM);
		assertTrue(output1.equals(result));
	}

	//Verifies that Quantity(1e6, KILOGRAM).add(Quantity(1e6, KILOGRAM)) returns Quantity(2e6, KILOGRAM).
	//Tests: Addition with large magnitude values.
	@Test
	public void testAddition_LargeValues() {
		w1 = new Quantity<>(1e6,WeightUnit.KILOGRAM);
		w2 = new Quantity<>(1e6,WeightUnit.KILOGRAM);
		Quantity<WeightUnit> output1 = QuantityMeasurementApp.demonstrateAddition(w1, w2);
		Quantity<WeightUnit> result = new Quantity<>(2e6,WeightUnit.KILOGRAM);
		assertTrue(output1.equals(result));
	}
}