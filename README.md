📅 23 Feb 2026  

**🔹 UC10 – Generic Quantity Abstraction for Multi-Category Support  **
Branch: feature/UC10-GenericQuantityAbstraction

🎯 Objective

- Eliminate duplication between QuantityLength and QuantityWeight  
- Introduce a common abstraction for all measurement categories  
- Apply DRY principle across the entire application  
- Improve scalability for future categories (Volume, Temperature)  
- Maintain immutability and type safety  
- Ensure full backward compatibility with UC1–UC9  

✅ Implementation

- Introduced IMeasurable interface:
  - getConversionFactor()
  - convertToBaseUnit(double value)
  - convertFromBaseUnit(double baseValue)
  - getUnitName()
- Refactored LengthUnit and WeightUnit to implement IMeasurable
- Created unified Quantity class:
  - Immutable, private final fields for value and unit
  - Validation: non-null unit and finite numeric value
  - Equality: converts both operands to base unit before comparison
  - Prevents cross-category comparison using getUnitName()
  - Generic convertTo(IMeasurable targetUnit) method
  - Generic addition logic reusable across categories
- Removed duplicated logic from separate length and weight implementations
- Category type safety preserved
- Backward-compatible architecture:
  - UC1–UC9 functionality remains unaffected
  - Established scalable foundation for Volume and Temperature categories  

[Source Code](https://github.com/aniruddhayadu/QuantityMeasurementApp/tree/feature/UC10-GenericQuantity/src)

---
