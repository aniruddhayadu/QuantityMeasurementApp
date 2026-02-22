📅 19 Feb 2026  
🔹 UC3 – Generic Quantity Class for DRY Principle  
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
