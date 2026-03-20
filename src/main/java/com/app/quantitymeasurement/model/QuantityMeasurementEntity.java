//package com.app.quantitymeasurement.model;
//
//import java.time.LocalDateTime;
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "quantity_measurement_entity")
//public class QuantityMeasurementEntity {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//
//	private double thisValue;
//	private String thisUnit;
//	private String thisMeasurementType;
//	private double thatValue;
//	private String thatUnit;
//	private String thatMeasurementType;
//	private String operation;
//	private double resultValue;
//	private String resultUnit;
//	private String resultMeasurementType;
//	private String resultString;
//	private boolean isError;
//	private String errorMessage;
//
//	private LocalDateTime createdAt;
//	private LocalDateTime updatedAt;
//
//	// Empty Constructor for JPA
//	public QuantityMeasurementEntity() {
//	}
//
//	// Full Constructor (DTO.toEntity uses this)
//	public QuantityMeasurementEntity(double thisValue, String thisUnit, String thisMeasurementType, double thatValue,
//			String thatUnit, String thatMeasurementType, String operation, double resultValue, String resultUnit,
//			String resultMeasurementType, String resultString, boolean isError, String errorMessage) {
//		this.thisValue = thisValue;
//		this.thisUnit = thisUnit;
//		this.thisMeasurementType = thisMeasurementType;
//		this.thatValue = thatValue;
//		this.thatUnit = thatUnit;
//		this.thatMeasurementType = thatMeasurementType;
//		this.operation = operation;
//		this.resultValue = resultValue;
//		this.resultUnit = resultUnit;
//		this.resultMeasurementType = resultMeasurementType;
//		this.resultString = resultString;
//		this.isError = isError;
//		this.errorMessage = errorMessage;
//	}
//
//	@PrePersist
//	protected void onCreate() {
//		createdAt = LocalDateTime.now();
//		updatedAt = LocalDateTime.now();
//	}
//
//	// --- MANUAL GETTERS (Required by DTO.from) ---
//	public Long getId() {
//		return id;
//	}
//
//	public double getThisValue() {
//		return thisValue;
//	}
//
//	public String getThisUnit() {
//		return thisUnit;
//	}
//
//	public String getThisMeasurementType() {
//		return thisMeasurementType;
//	}
//
//	public double getThatValue() {
//		return thatValue;
//	}
//
//	public String getThatUnit() {
//		return thatUnit;
//	}
//
//	public String getThatMeasurementType() {
//		return thatMeasurementType;
//	}
//
//	public String getOperation() {
//		return operation;
//	}
//
//	public double getResultValue() {
//		return resultValue;
//	}
//
//	public String getResultUnit() {
//		return resultUnit;
//	}
//
//	public String getResultMeasurementType() {
//		return resultMeasurementType;
//	}
//
//	public String getResultString() {
//		return resultString;
//	}
//
//	public boolean isError() {
//		return isError;
//	}
//
//	public String getErrorMessage() {
//		return errorMessage;
//	}
//
//	// --- EXTRA SETTERS FOR QuantityMeasurementService.java (The new service) ---
//	// Mapping old set calls to our existing fields so DTO doesn't break
//	public void setValue(double value) {
//		this.thisValue = value;
//	}
//
//	public void setUnit(String unit) {
//		this.thisUnit = unit;
//	}
//
//	public void setUnitType(String type) {
//		this.thisMeasurementType = type;
//	}
//
//	public void setConvertedValue(double value) {
//		this.resultValue = value;
//	}
//}

package com.app.quantitymeasurement.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "quantity_measurement_entity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuantityMeasurementEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private double thisValue;
	private String thisUnit;
	private String thisMeasurementType;
	private double thatValue;
	private String thatUnit;
	private String thatMeasurementType;
	private String operation;
	private double resultValue;
	private String resultUnit;
	private String resultMeasurementType;
	private String resultString;
	private boolean isError;
	private String errorMessage;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	// Custom constructor for Service logic (excluding ID and Timestamps)
	public QuantityMeasurementEntity(double thisValue, String thisUnit, String thisMeasurementType, double thatValue,
			String thatUnit, String thatMeasurementType, String operation, double resultValue, String resultUnit,
			String resultMeasurementType, String resultString, boolean isError, String errorMessage) {
		this.thisValue = thisValue;
		this.thisUnit = thisUnit;
		this.thisMeasurementType = thisMeasurementType;
		this.thatValue = thatValue;
		this.thatUnit = thatUnit;
		this.thatMeasurementType = thatMeasurementType;
		this.operation = operation;
		this.resultValue = resultValue;
		this.resultUnit = resultUnit;
		this.resultMeasurementType = resultMeasurementType;
		this.resultString = resultString;
		this.isError = isError;
		this.errorMessage = errorMessage;
	}
}