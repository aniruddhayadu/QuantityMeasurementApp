📅 25 Feb 2026  

**🔹 UC12 – Subtraction and Division Operations for Quantities  **
Branch: feature/UC12-SubtractionAndDivision

🎯 Objective

- Extend arithmetic support beyond addition  
- Implement subtraction between two quantities of the same category  
- Implement division operation for quantities  
- Maintain immutability and cross-unit precision  
- Ensure strict category validation during arithmetic operations  

✅ Implementation

- Extended `Quantity` class with subtraction and division support
- Subtraction:
  - `subtract(Quantity, Quantity)` → result in first operand’s unit
  - Converts both operands to base unit before performing subtraction
  - Returns new immutable `Quantity` object
- Division:
  - `divide(double divisor)` → divides quantity value by scalar
  - Validates divisor is non-zero and finite
  - Preserves unit of original quantity
- Validation:
  - Ensures both quantities belong to same measurement category
  - Prevents cross-category arithmetic operations
  - Handles zero, negative, large, and small values
- Backward-compatible architecture:
  - UC1–UC11 functionality remains unaffected
  - Arithmetic operations centralized within generic `Quantity` class  

[Source Code](https://github.com/aniruddhayadu/QuantityMeasurementApp/tree/feature/UC12-SubtractionAndDivision/src)

---
