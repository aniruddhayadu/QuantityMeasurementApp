## 🔹 UC15 – N-Tier Architecture Refactoring  
**Branch:** `feature/UC15-NTierArchitecture`

### 🎯 Objective
Refactor the monolithic application into a professional **N-Tier architecture** to improve scalability, maintainability, and separation of concerns.

Key goals:
- Ensure a **strict and clean separation of concerns** across architectural layers
- **Decouple data storage logic from business logic**
- Apply **SOLID principles**, especially:
  - Dependency Injection
  - Open/Closed Principle
- Maintain **backward compatibility** with all existing test cases

---

### 🏗️ Implementation

#### 1. Service Layer
Created `QuantityMeasurementServiceImpl` to handle all business logic:
- Centralized **mathematical operations, unit conversions, and comparisons**
- Ensured the **Service layer remains independent** of specific storage implementations

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
- Uses **Java Serialization** for storing and retrieving data

#### 5. Testing
Updated the **JUnit test suite**:
- Validates the **new architectural flow**
- Ensures **correct dependency injection across all layers**
- Confirms **existing functionality remains intact**

---

### 🔗 Source

[QuantityMeasurementApp – UC15 N-Tier Architecture Refactor](https://github.com/aniruddhayadu/QuantityMeasurementApp/edit/feature/UC15-NTierArchitectureRefactor/README.md)
