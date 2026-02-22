📅 18 Feb 2026  
🔹 UC2 – Feet and Inches Equality Verification  
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
