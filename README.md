# Quantity Measurement Application

## Project Overview

**The Quantity Measurement Application validates equality, unit conversions, and arithmetic operations across multiple measurement categories:**

 - Length: Feet, Inches, Yards, Centimeters
 - Weight: Kilogram, Gram, Pound

**The project follows best practices:**

✅ Test-Driven Development (TDD)
✅ Feature Branch Workflow
✅ Clean Code Practices
✅ DRY (Don’t Repeat Yourself) Principle
✅ Robust Unit Conversion Strategy
✅ Multi-category Measurement Architecture

---

## Git Workflow

main
 └── dev
      ├── feature/UC1-FeetEquality
      ├── feature/UC2-InchEquality
      ├── feature/UC3-GenericQuantityClassForDRYPrinciple
      ├── feature/UC4-Extended-Unit-Support
      ├── feature/UC5-Unit-to-Unit-Conversion
      ├── feature/UC6-Addition-Of-Two-Length-Units
      ├── feature/UC7-Addition-With-Target-Unit-Specification
      ├── feature/UC8-StandaloneUnit-Refactoring
      └── feature/UC9-WeightMeasurement
---

## Feature History

📅 17 Feb 2026  

**🔹 UC1 – Feet Equality Verification**  
Branch: feature/UC1-FeetEquality

🎯 Objective

- Check if two measurements in Feet are equal
- Correctly override equals() for comparison
- Apply Test-Driven Development principles

✅ Implementation

- Defined Feet class for encapsulating measurement
- Added precise equality logic using Double.compare()
- Ensured null safety and type validation
- Created comprehensive JUnit 5 tests

[Source Code](https://github.com/aniruddhayadu/QuantityMeasurementApp/tree/feature/UC1-FeetEquality/src)

---

📅 18 Feb 2026  

**🔹 UC2 – Feet and Inches Equality Verification  **
Branch: feature/UC2-InchEquality

🎯 Objective

- Extend UC1 to check equality of measurements in Inches along with Feet
- Ensure proper equals() logic for both units
- Maintain Test-Driven Development approach

✅ Implementation

- Defined Inches class alongside Feet class
- Added equality logic for Inches using Double.compare()
- Validated numeric input for both units
- Ensured null safety and type validation
- Reduced main method dependency by creating dedicated methods for Feet and Inches equality
- Created JUnit 5 test cases covering same and different values, nulls, and self-reference


[Source Code](https://github.com/aniruddhayadu/QuantityMeasurementApp/tree/feature/UC2-InchEquality/src)

---

📅 19 Feb 2026  

**🔹 UC3 – Generic Quantity Class for DRY Principle  **
Branch: feature/UC3-GenericLength

🎯 Objective

- Refactor Feet and Inches classes into a single QuantityLength class
- Apply DRY principle to remove code duplication
- Maintain all equality and conversion functionality from UC1 & UC2
- Enable easy extension for new units

✅ Implementation

- Introduced LengthUnit enum for all supported units and conversion factors
- Created QuantityLength class handling value + unit
- Centralized equality logic using base unit conversion
- Ensured type safety, null handling, and numeric validation
- Added comprehensive JUnit 5 tests for same-unit, cross-unit, and invalid inputs
- All previous UC1 & UC2 functionality preserved

[Source Code](https://github.com/aniruddhayadu/QuantityMeasurementApp/tree/feature/UC3-GenericLength/src)

---

📅 20 Feb 2026

**🔹 UC4 – Extended Unit Support (Yards & Centimeters)  **
Branch: feature/UC4-YardEquality

🎯 Objective

- Extend QuantityLength class to support Yards and Centimeters
- Maintain cross-unit equality comparisons
- Preserve all previous UC1–UC3 functionality
- Ensure DRY principle while adding new units

✅ Implementation

- Added YARDS and CENTIMETERS to LengthUnit enum with proper conversion factors
- Verified QuantityLength equals() method works seamlessly for new units
- Ensured type safety, numeric validation, and null handling
- Covered yard-to-yard, yard-to-feet, yard-to-inches, and cm conversions in JUnit 5 tests
- All previous tests from UC1–UC3 continue to pass

[Source Code](https://github.com/aniruddhayadu/QuantityMeasurementApp/tree/feature/UC4-YardEquality/src)

---

📅 20 Feb 2026  

**🔹 UC5 – Unit-to-Unit Conversion (Same Measurement Type)  **
Branch: feature/UC5-UnitConversion

🎯 Objective

- Provide explicit conversion between units (feet ↔ inches, yards ↔ feet, centimeters ↔ inches, etc.)
- Maintain DRY principle and preserve UC1–UC4 functionality
- Expose a standard convert() API for all supported units
- Ensure bidirectional and precise conversions with proper validation

✅ Implementation

- Added static convert(value, sourceUnit, targetUnit) method in QuantityLength
- Validates numeric value and non-null, supported source/target units
- Normalizes input to base unit (feet) before converting to target unit
- Overloaded demonstrateLengthConversion() for raw values and QuantityLength instances
- Ensured proper rounding, epsilon tolerance, and exception handling for invalid inputs
- Preserved equals() and toString() overrides for consistent object behavior
- Comprehensive JUnit 5 tests cover same-unit, cross-unit, negative, zero, large, and small conversions

[Source Code](https://github.com/aniruddhayadu/QuantityMeasurementApp/tree/feature/UC5-UnitConversion/src)

---

📅 21 Feb 2026  

**🔹 UC6 – Addition of Two Length Units (Same Category)  **
Branch: feature/UC6-UnitAddition

🎯 Objective

- Enable addition of two QuantityLength objects, potentially with different units
- Result is expressed in the unit of the first operand
- Maintain DRY, immutability, and cross-unit precision

✅ Implementation

- Added instance and static add() methods in QuantityLength
- Validates non-null operands, valid LengthUnits, and finite values
- Converts both operands to base unit (feet), performs addition
- Converts sum to the unit of the first operand
- Returns a new QuantityLength object (immutability preserved)
- Leverages UC5 conversion logic; no duplication of conversion code
- Overloaded methods to support raw values or QuantityLength objects
- Handles zero, negative, large, and small values
- Comprehensive JUnit 5 tests cover same-unit, cross-unit, commutativity, and precision

[Source Code](https://github.com/aniruddhayadu/QuantityMeasurementApp/tree/feature/UC6-UnitAddition/src)

---

📅 21 Feb 2026  

**🔹 UC7 – Addition with Target Unit Specification** 

Branch: feature/UC7-TargetUnitAddition

🎯 Objective

- Extend UC6 addition functionality by allowing an explicit target unit
- Result can be expressed in any supported LengthUnit, not just the first operand
- Maintain immutability, precision, and cross-unit consistency

✅ Implementation

- Overloaded `add()` methods in `QuantityLength` to accept an explicit target unit
- Validates non-null operands, valid LengthUnits, and finite values
- Converts both operands to a base unit (feet), performs addition
- Converts the sum to the specified target unit
- Returns a new `QuantityLength` object (immutability preserved)
- Reuses private utility addition method to avoid code duplication
- Supports both implicit (first operand unit) and explicit target unit addition
- Handles zero, negative, large, and small values
- Ensures commutativity: add(A, B, targetUnit) = add(B, A, targetUnit)
- Comprehensive JUnit 5 tests cover same-unit, cross-unit, and all target unit combinations

[Source Code](https://github.com/aniruddhayadu/QuantityMeasurementApp/tree/feature/UC7-TargetUnitAddition/src)

---

📅 22 Feb 2026  

**🔹 UC8 – Refactoring Unit Enum to Standalone with Conversion Responsibility  **
Branch: feature/UC8-StandaloneUnit

🎯 Objective

- Extract `LengthUnit` from `QuantityLength` into a top-level enum
- Delegate all conversion logic to the unit itself
- Simplify `QuantityLength` to focus on comparison and arithmetic
- Enable scalable addition of new measurement categories (Weight, Volume, Temperature)

✅ Implementation

- `LengthUnit` now a standalone enum with constants: FEET, INCHES, YARDS, CENTIMETERS
- Each unit contains methods:
  - `convertToBaseUnit(double value)` → converts unit to base (feet)
  - `convertFromBaseUnit(double baseValue)` → converts from base to this unit
- `QuantityLength` delegates conversion to `LengthUnit` methods
- Backward-compatible API; all UC1–UC7 operations work without modification
- Improves cohesion, reduces coupling, eliminates circular dependencies
- Supports immutability, type safety, and thread-safety
- Established a reusable architectural pattern for multiple measurement categories

[Source Code](https://github.com/aniruddhayadu/QuantityMeasurementApp/tree/feature/UC8-StandaloneUnit/src)

---


📅 22 Feb 2026  

**🔹 UC9 – Weight Measurement Equality, Conversion, and Addition (Kilogram, Gram, Pound)  **
Branch: feature/UC9-WeightMeasurement

🎯 Objective

- Introduce **weight measurement category** alongside length
- Support units: KILOGRAM (kg, base), GRAM (g), POUND (lb)
- Implement equality comparison, unit conversion, and addition for weight
- Maintain immutability and type safety
- Demonstrate scalability of generic QuantityMeasurement patterns for multiple measurement categories

✅ Implementation

- `WeightUnit` enum as standalone class:
  - Constants: KILOGRAM, GRAM, POUND
  - Conversion factors relative to base unit (kg)
  - Methods: `convertToBaseUnit()`, `convertFromBaseUnit()`
- `QuantityWeight` class:
  - Immutable, private final fields for value and unit
  - Validation: non-null unit, finite numeric value
  - Equality: converts both operands to base unit before comparison
  - Conversion: `convertTo(targetUnit)` delegates to `WeightUnit`
  - Addition:
    - `add(QuantityWeight, QuantityWeight)` → result in first operand’s unit
    - `add(QuantityWeight, QuantityWeight, WeightUnit)` → result in explicit target unit
- Category type safety enforced:
  - Weight and length are distinct, non-comparable types
- Backward-compatible architecture:
  - UC1–UC8 functionality remains unaffected
  - Patterns mirror LengthUnit/QuantityLength, enabling future categories (volume, temperature, etc.)

[Source Code](https://github.com/aniruddhayadu/QuantityMeasurementApp/tree/feature/UC9-WeightMeasurement/src?authuser=0)

---
