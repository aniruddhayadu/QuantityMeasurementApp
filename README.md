📅 27 Feb 2026  

**🔹 UC14 – Temperature Measurement with Arithmetic Restriction (Celsius, Fahrenheit, Kelvin)  **
Branch: feature/UC14-temperature-measurement

🎯 Objective

- Introduce **temperature measurement category** alongside length, weight, and volume  
- Support units: CELSIUS (°C, base), FAHRENHEIT (°F), KELVIN (K)  
- Implement equality comparison with proper temperature conversion formulas  
- Restrict arithmetic operations (addition, subtraction, division) for temperature  
- Maintain immutability, type safety, and backward compatibility  

✅ Implementation

- `TemperatureUnit` enum as standalone class:
  - Constants: CELSIUS, FAHRENHEIT, KELVIN
  - Custom conversion logic (not simple multiplication factor)
  - Methods: `convertToBaseUnit()`, `convertFromBaseUnit()`
- Extended generic `Quantity` architecture to support temperature
- Equality:
  - Converts both operands to base unit (Celsius) before comparison
  - Supports cross-unit equality (°C ↔ °F ↔ K)
- Arithmetic Restriction:
  - Introduced validation to block add, subtract, and divide operations for temperature
  - Throws `UnsupportedOperationException` for invalid arithmetic attempts
- Category safety maintained:
  - Temperature cannot be compared or operated with length, weight, or volume
- Backward-compatible architecture:
  - All UC1–UC13 functionality remains unaffected
  - All existing 108 test cases continue to pass successfully  

[Source Code](https://github.com/aniruddhayadu/QuantityMeasurementApp/tree/feature/UC14-temperature-measurement/src)

---
