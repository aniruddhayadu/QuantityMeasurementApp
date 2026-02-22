📅 20 Feb 2026  
🔹 UC5 – Unit-to-Unit Conversion (Same Measurement Type)  
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
