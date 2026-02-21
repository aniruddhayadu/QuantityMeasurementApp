# QuantityMeasurementApp


A Java application for **quantity measurement**, supporting **unit conversions, comparisons, and arithmetic operations** on lengths.

---

**UC1: Basic Length Representation**
- Introduced `QuantityLength` objects with value and unit.
- Supports FEET, INCHES, YARDS, CENTIMETERS.
- Ensures type safety and basic validation for numeric values.
- Provides foundational class structure for later UCs.

---

**UC2: Length Equality Check**
- Implemented equality check between two `QuantityLength` objects.
- Compares values after converting to a common base unit.
- Handles null values safely and ensures reliable comparison.

---

**UC3: Cross-Unit Conversion**
- Added conversion between different length units.
- Uses a base unit (FEET) for conversion consistency.
- Maintains mathematical accuracy across conversions.

---

**UC4: Addition of Lengths**
- Supports addition of two `QuantityLength` objects.
- Converts operands to base unit before addition.
- Returns result in the unit of the first operand.

---

**UC5: Addition with Zero & Negative Values**
- Handles zero and negative values correctly in addition.
- Maintains commutative property and immutability.

---

**UC6: Addition & Method Overloading**
- Overloaded `add()` method to simplify usage.
- Maintains backward compatibility with previous addition method.
- Ensures all arithmetic respects floating-point precision.

---

**UC7: Addition with Target Unit Specification**
- Allows specifying the **target unit** for addition result.
- Maintains commutative property regardless of target unit.
- Uses private utility methods to avoid code duplication.
- Supports all supported length units for result conversion.

---

**UC8: Refactoring LengthUnit Enum**
- Extracted `LengthUnit` from `QuantityLength` to a standalone top-level enum.
- `LengthUnit` now handles all conversion responsibilities.
- `QuantityLength` simplified to focus on comparison and arithmetic.
- Eliminates circular dependencies and supports multiple measurement categories.
- Maintains backward compatibility; all UC1–UC7 tests pass unchanged.
- Supports scalable addition of future units like WeightUnit, VolumeUnit.

---

**General Notes**
- Immutability and thread-safety ensured in `QuantityLength` and enums.
- Explicit target units improve API clarity.
- Floating-point precision handled consistently across all operations.


---
