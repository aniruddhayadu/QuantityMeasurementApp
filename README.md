📅 21 Feb 2026  
🔹 UC6 – Addition of Two Length Units (Same Category)  
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
