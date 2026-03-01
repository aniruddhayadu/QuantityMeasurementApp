📅 26 Feb 2026  

**🔹 UC13 – Arithmetic Validation and Robust Category Safety  **
Branch: feature/UC13-ArithmeticValidation

🎯 Objective

- Strengthen arithmetic validation across all measurement categories  
- Prevent invalid arithmetic between incompatible measurement types  
- Improve exception handling and input validation  
- Maintain immutability and precision across operations  
- Ensure all UC1–UC12 test cases continue to pass  

✅ Implementation

- Enhanced `Quantity` class with strict validation logic:
  - Ensures arithmetic operations only occur within same measurement category
  - Throws meaningful exceptions for invalid cross-category operations
- Improved internal validation methods:
  - Non-null checks for operands
  - Finite numeric validation
  - Category consistency verification
- Refactored arithmetic logic for clarity and maintainability
- Standardized exception handling for:
  - Cross-category addition
  - Cross-category subtraction
  - Invalid division inputs
- Preserved backward compatibility:
  - All previous UC1–UC12 features work without modification
  - No breaking API changes introduced  

[Source Code](https://github.com/aniruddhayadu/QuantityMeasurementApp/tree/feature/UC13-CentralizedArithmeticLogic/src)

---
