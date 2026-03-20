# Quantity Measurement Application

## Project Overview

**The Quantity Measurement Application validates equality, unit conversions, and arithmetic operations across multiple measurement categories:**

 - Length: Feet, Inches, Yards, Centimeters
 - Weight: Kilogram, Gram, Pound

**The project follows best practices:**

✅ Test-Driven Development (TDD)
✅ Feature Branch Workflow
✅ Clean Code Practices
✅ DRY (Don’t Repeat Yourself) Principle
✅ Robust Unit Conversion Strategy
✅ Multi-category Measurement Architecture

---

## Git Workflow

---
```
main
 └── dev
      ├── feature/UC1-FeetEquality
      ├── feature/UC2-InchEquality
      ├── feature/UC3-GenericLength
      ├── feature/UC4-YardEquality
      ├── feature/UC5-UnitConversoion
      ├── feature/UC6-UnitAddition
      ├── feature/UC7-TargetUnitAddition
      ├── feature/UC8-StandaloneUnit
      ├── feature/UC9-WeightMeasurement
      ├── feature/UC10-GenericQuantity
      ├── feature/UC11-VolumeMeasurement
      ├── feature/UC12-SubtractionAndDivision
      ├── feature/UC13-CentralizedArithmeticLogic
      ├── feature/UC14-temperature-measurement
      ├── feature/UC15-NTierArchitectureRefactor
      ├── feature/UC16-JDBCPersistence
      └── feature/UC17-SpringBackend
```

---

## Feature History

📅 17 Feb 2026  

**🔹 UC1 – Feet Equality Verification**  
Branch: feature/UC1-FeetEquality

🎯 Objective

- Check if two measurements in Feet are equal
- Correctly override equals() for comparison
- Apply Test-Driven Development principles

✅ Implementation

- Defined Feet class for encapsulating measurement
- Added precise equality logic using Double.compare()
- Ensured null safety and type validation
- Created comprehensive JUnit 5 tests

[Source Code](https://github.com/aniruddhayadu/QuantityMeasurementApp/tree/feature/UC1-FeetEquality/src)

---

📅 18 Feb 2026  

**🔹 UC2 – Feet and Inches Equality Verification  **
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

---

📅 19 Feb 2026  

**🔹 UC3 – Generic Quantity Class for DRY Principle  **
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

---

📅 20 Feb 2026

**🔹 UC4 – Extended Unit Support (Yards & Centimeters)  **
Branch: feature/UC4-YardEquality

🎯 Objective

- Extend QuantityLength class to support Yards and Centimeters
- Maintain cross-unit equality comparisons
- Preserve all previous UC1–UC3 functionality
- Ensure DRY principle while adding new units

✅ Implementation

- Added YARDS and CENTIMETERS to LengthUnit enum with proper conversion factors
- Verified QuantityLength equals() method works seamlessly for new units
- Ensured type safety, numeric validation, and null handling
- Covered yard-to-yard, yard-to-feet, yard-to-inches, and cm conversions in JUnit 5 tests
- All previous tests from UC1–UC3 continue to pass

[Source Code](https://github.com/aniruddhayadu/QuantityMeasurementApp/tree/feature/UC4-YardEquality/src)

---

📅 20 Feb 2026  

**🔹 UC5 – Unit-to-Unit Conversion (Same Measurement Type)  **
Branch: feature/UC5-UnitConversion

🎯 Objective

- Provide explicit conversion between units (feet ↔ inches, yards ↔ feet, centimeters ↔ inches, etc.)
- Maintain DRY principle and preserve UC1–UC4 functionality
- Expose a standard convert() API for all supported units
- Ensure bidirectional and precise conversions with proper validation

✅ Implementation

- Added static convert(value, sourceUnit, targetUnit) method in QuantityLength
- Validates numeric value and non-null, supported source/target units
- Normalizes input to base unit (feet) before converting to target unit
- Overloaded demonstrateLengthConversion() for raw values and QuantityLength instances
- Ensured proper rounding, epsilon tolerance, and exception handling for invalid inputs
- Preserved equals() and toString() overrides for consistent object behavior
- Comprehensive JUnit 5 tests cover same-unit, cross-unit, negative, zero, large, and small conversions

[Source Code](https://github.com/aniruddhayadu/QuantityMeasurementApp/tree/feature/UC5-UnitConversion/src)

---

📅 21 Feb 2026  

**🔹 UC6 – Addition of Two Length Units (Same Category)  **
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

---

📅 21 Feb 2026  

**🔹 UC7 – Addition with Target Unit Specification** 

Branch: feature/UC7-TargetUnitAddition

🎯 Objective

- Extend UC6 addition functionality by allowing an explicit target unit
- Result can be expressed in any supported LengthUnit, not just the first operand
- Maintain immutability, precision, and cross-unit consistency

✅ Implementation

- Overloaded `add()` methods in `QuantityLength` to accept an explicit target unit
- Validates non-null operands, valid LengthUnits, and finite values
- Converts both operands to a base unit (feet), performs addition
- Converts the sum to the specified target unit
- Returns a new `QuantityLength` object (immutability preserved)
- Reuses private utility addition method to avoid code duplication
- Supports both implicit (first operand unit) and explicit target unit addition
- Handles zero, negative, large, and small values
- Ensures commutativity: add(A, B, targetUnit) = add(B, A, targetUnit)
- Comprehensive JUnit 5 tests cover same-unit, cross-unit, and all target unit combinations

[Source Code](https://github.com/aniruddhayadu/QuantityMeasurementApp/tree/feature/UC7-TargetUnitAddition/src)

---

📅 22 Feb 2026  

**🔹 UC8 – Refactoring Unit Enum to Standalone with Conversion Responsibility  **
Branch: feature/UC8-StandaloneUnit

🎯 Objective

- Extract `LengthUnit` from `QuantityLength` into a top-level enum
- Delegate all conversion logic to the unit itself
- Simplify `QuantityLength` to focus on comparison and arithmetic
- Enable scalable addition of new measurement categories (Weight, Volume, Temperature)

✅ Implementation

- `LengthUnit` now a standalone enum with constants: FEET, INCHES, YARDS, CENTIMETERS
- Each unit contains methods:
  - `convertToBaseUnit(double value)` → converts unit to base (feet)
  - `convertFromBaseUnit(double baseValue)` → converts from base to this unit
- `QuantityLength` delegates conversion to `LengthUnit` methods
- Backward-compatible API; all UC1–UC7 operations work without modification
- Improves cohesion, reduces coupling, eliminates circular dependencies
- Supports immutability, type safety, and thread-safety
- Established a reusable architectural pattern for multiple measurement categories

[Source Code](https://github.com/aniruddhayadu/QuantityMeasurementApp/tree/feature/UC8-StandaloneUnit/src)

---


📅 22 Feb 2026  

**🔹 UC9 – Weight Measurement Equality, Conversion, and Addition (Kilogram, Gram, Pound)  **
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

## 📅 12 March 2026

## 🔹 UC15 – N-Tier Architecture Refactoring  
**Branch:** `feature/UC15-NTierArchitectureRefactor`

### 🎯 Objective
Refactor the monolithic application into a **professional N-Tier architecture** to improve maintainability, scalability, and separation of concerns.

Key goals:
- Ensure a **clean and strict separation of concerns** across architectural layers
- **Decouple data storage logic from business logic**
- Apply **SOLID principles**, especially:
  - Dependency Injection
  - Open/Closed Principle
- Maintain **backward compatibility** with all existing test cases

---

### 🏗️ Implementation

#### 1. Service Layer
Created `QuantityMeasurementServiceImpl`:
- Centralized **mathematical operations, unit conversions, and comparisons**
- Ensured the **Service layer is independent of storage implementations**

#### 2. Controller Layer
Introduced `QuantityMeasurementController`:
- Handles incoming requests
- Routes them securely to the **Service layer**

#### 3. Repository Layer
Defined the interface `IQuantityMeasurementRepository`:
- Standardizes **data access operations**
- Allows **high-level modules to remain independent of low-level implementations**

#### 4. Cache Repository Implementation
Implemented `QuantityMeasurementCacheRepository`:
- Provides **local file-based persistence**
- Uses **Java Serialization** for storing and retrieving measurement data

#### 5. Testing
Updated the **JUnit test suite**:
- Validates the **new architectural flow**
- Ensures **proper dependency injection across layers**
- Confirms **existing functionality remains fully intact**

---

### 🔗 Source Code

[QuantityMeasurementApp – UC15 N-Tier Architecture Refactor](https://github.com/aniruddhayadu/QuantityMeasurementApp/tree/feature/UC15-NTierArchitectureRefactor)

---


## 📅 14 March 2026

## 🔹 UC16 – JDBC Database Integration  
**Branch:** `feature/UC16-JDBCPersistence`

### 🎯 Objective
Enable persistent storage of measurement data using a **relational SQL database** while maintaining clean architecture and high performance.

Key goals:
- Enable **persistent storage of measurement data**
- Implement **industry-standard connection pooling** for optimized performance
- Secure database operations against vulnerabilities such as **SQL Injection**
- Seamlessly replace **local cache storage with database storage** using **Dependency Injection**
- Ensure **complete mocked test coverage** for the persistence layer

---

### 🏗️ Implementation

#### 1. JDBC Persistence Layer
- Integrated a **JDBC-based persistence layer**
- Configured an **embedded H2 SQL Database** for seamless data storage and retrieval

#### 2. Connection Pooling
Implemented `ConnectionPool` utility:
- Utilized **HikariCP** for **high-performance database connection management**
- Ensured efficient and reliable connection reuse

#### 3. Database Repository
Created `QuantityMeasurementDatabaseRepository`:
- Used **Parameterized Prepared Statements** to prevent **SQL Injection**
- Implemented SQL logic for **saving and retrieving historical measurement data**

#### 4. Dependency Injection
Refactored `QuantityMeasurementApp`:
- Enabled **dynamic injection of the database repository**
- Ensured **business logic remained unchanged**

#### 5. Testing
Achieved **100% test coverage**:
- Used **JUnit** and **Mockito**
- Mocked repository interactions
- Validated **Controller and Service layer behavior independently**

---

### 🔗 Source Code

[QuantityMeasurementApp – UC16 JDBC Persistence](https://github.com/aniruddhayadu/QuantityMeasurementApp/tree/feature/UC16-JDBCPersistence/src)

---


# 📊 UC17 – Audit History & Persistence with Lombok

**📅 Date:** 20 March 2026  
**🌿 Branch:** `feature/UC17-AuditHistoryPersistence`

---

## 🎯 Objective

This use case focuses on implementing a **persistent Audit History system** using **Spring Data JPA** and enhancing the codebase with **Project Lombok** for improved maintainability.

### Key Goals

- Transition from temporary cache to **H2 Database** for persistent storage  
- Reduce boilerplate code using **Lombok annotations**  
- Maintain a complete **audit trail** of all operations  
- Provide APIs for querying **history and analytics data**

---

## 🏗️ Implementation Details

### 1. Repository Layer (JPA Integration)

- Created `QuantityMeasurementRepository`
  - Extends `JpaRepository` for seamless CRUD operations
  - Enables direct interaction with the **H2 Database**

- Custom query methods:
  - `findByOperation(...)`
  - `countByOperation(...)`

---

### 2. Entity & DTO Optimization (Lombok)

Refactored:
- `QuantityMeasurementEntity`
- `QuantityDTO`

#### Applied Lombok Annotations:
- `@Data`
- `@AllArgsConstructor`

#### Benefits:
- Eliminated manual getters, setters, and constructors  
- Improved code readability and maintainability  

---

### 3. Service Layer (Audit Logic Integration)

Updated `QuantityMeasurementServiceImpl`:

#### Integrated persistence into core operations:
- Add  
- Subtract  
- Convert  
- Compare  

#### Audit Behavior:
- Each successful operation:
  - Automatically generates an **audit record**
  - Stores complete transaction details in the database  

---

### 4. Controller Layer (History Endpoints)

Enhanced `QuantityMeasurementController` with new endpoints:

- Fetch complete operation history  
- Filter history by operation type  
- Query by measurement category (e.g., Length, Volume)  
- Retrieve operation counts  

---

### 5. Testing

Updated test suite using **JUnit 5** and **Mockito**:

- Verified repository-level data persistence  
- Validated service-layer audit logging  
- Added integration tests using **MockMvc**  
- Ensured accurate history retrieval via REST APIs  

---

## 🗄️ Tech Stack

- **Spring Boot**
- **Spring Data JPA**
- **H2 Database**
- **Project Lombok**
- **JUnit 5**
- **Mockito**
- **MockMvc**

---

## 🔗 Source Code

📁 [UC17 Backend Implementation](https://github.com/aniruddhayadu/QuantityMeasurementApp/tree/feature/UC17-SpringBackend/src)

---

## ✅ Summary

This implementation introduces a **robust, persistent audit system** while improving code quality through **Lombok**. It ensures:

- Reliable historical tracking  
- Clean and maintainable codebase  
- Scalable API design for analytics and querying  

---

