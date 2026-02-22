📅 21 Feb 2026  
🔹 UC7 – Addition with Target Unit Specification  
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
