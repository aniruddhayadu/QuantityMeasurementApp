
📅 23 Feb 2026  
🔹 UC9 – Weight Measurement Equality, Conversion, and Addition (Kilogram, Gram, Pound)  
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
