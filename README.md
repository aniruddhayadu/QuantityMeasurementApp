📅 24 Feb 2026  

**🔹 UC11 – Volume Measurement Equality, Conversion, and Addition (Litre, Millilitre, Gallon)  **
Branch: feature/UC11-VolumeMeasurement

🎯 Objective

- Introduce **volume measurement category** alongside length and weight  
- Support units: LITRE (L, base), MILLILITRE (mL), GALLON (gal)  
- Implement equality comparison, unit conversion, and addition for volume  
- Maintain immutability and type safety  
- Demonstrate scalability of generic Quantity architecture for new measurement categories  

✅ Implementation

- `VolumeUnit` enum as standalone class:
  - Constants: LITRE, MILLILITRE, GALLON
  - Conversion factors relative to base unit (litre)
  - Methods: `convertToBaseUnit()`, `convertFromBaseUnit()`
- `Quantity` class reused for volume:
  - Immutable, private final fields for value and unit
  - Validation: non-null unit and finite numeric value
  - Equality: converts both operands to base unit before comparison
  - Conversion: `convertTo(targetUnit)` delegates to `VolumeUnit`
  - Addition:
    - `add(Quantity, Quantity)` → result in first operand’s unit
    - `add(Quantity, Quantity, VolumeUnit)` → result in explicit target unit
- Category type safety enforced:
  - Volume, length, and weight remain distinct and non-comparable categories
- Backward-compatible architecture:
  - UC1–UC10 functionality remains unaffected
  - Volume integrated seamlessly using existing generic design pattern  

[Source Code](https://github.com/aniruddhayadu/QuantityMeasurementApp/tree/feature/UC11-VolumeMeasurement/src)

---
