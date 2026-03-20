//package com.app.quantitymeasurement.model;
//
//import java.util.logging.Logger;
//import io.swagger.v3.oas.annotations.media.Schema;
//import jakarta.validation.constraints.AssertTrue;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Pattern;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
///**
// * Interface to define common behavior for all measurable units.
// */
//interface IMeasurableUnit {
//	String getUnitName();
//
//	String getMeasurementType();
//}
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Schema(description = "A quantity with a value and unit")
//public class QuantityDTO {
//
//	private static final Logger logger = Logger.getLogger(QuantityDTO.class.getName());
//
//	// Enums for different categories
//	public enum LengthUnit implements IMeasurableUnit {
//		FEET, INCHES, YARDS, CENTIMETRES;
//
//		@Override
//		public String getUnitName() {
//			return this.name();
//		}
//
//		@Override
//		public String getMeasurementType() {
//			return "LengthUnit";
//		}
//	}
//
//	public enum WeightUnit implements IMeasurableUnit {
//		KG, GRAM, POUND, TONNE;
//
//		@Override
//		public String getUnitName() {
//			return this.name();
//		}
//
//		@Override
//		public String getMeasurementType() {
//			return "WeightUnit";
//		}
//	}
//
//	public enum VolumeUnit implements IMeasurableUnit {
//		MILLILITRE, LITRE, GALLON;
//
//		@Override
//		public String getUnitName() {
//			return this.name();
//		}
//
//		@Override
//		public String getMeasurementType() {
//			return "VolumeUnit";
//		}
//	}
//
//	public enum TemperatureUnit implements IMeasurableUnit {
//		CELSIUS, FAHRENHEIT, KELVIN;
//
//		@Override
//		public String getUnitName() {
//			return this.name();
//		}
//
//		@Override
//		public String getMeasurementType() {
//			return "TemperatureUnit";
//		}
//	}
//
//	@NotNull(message = "Value cannot be empty")
//	@Schema(example = "1.0")
//	public double value;
//
//	@NotNull(message = "Unit cannot be null")
//	@Schema(example = "FEET")
//	public String unit;
//
//	@NotNull(message = "Measurement type cannot be null")
//	@Pattern(regexp = "(?i)LengthUnit|VolumeUnit|WeightUnit|TemperatureUnit", message = "Measurement type must be one of: LengthUnit, VolumeUnit, WeightUnit, TemperatureUnit")
//	@Schema(example = "LengthUnit")
//	public String measurementType;
//
//	/**
//	 * Custom validation to ensure the unit belongs to the correct measurement type.
//	 */
//	@AssertTrue(message = "Unit must be valid for the specified measurement type")
//	public boolean isValidUnit() {
//		logger.info("Validating unit: " + unit + " for measurement type: " + measurementType);
//		if (unit == null || measurementType == null)
//			return false;
//
//		try {
//			switch (measurementType) {
//			case "LengthUnit":
//				LengthUnit.valueOf(unit.toUpperCase());
//				break;
//			case "VolumeUnit":
//				VolumeUnit.valueOf(unit.toUpperCase());
//				break;
//			case "WeightUnit":
//				WeightUnit.valueOf(unit.toUpperCase());
//				break;
//			case "TemperatureUnit":
//				TemperatureUnit.valueOf(unit.toUpperCase());
//				break;
//			default:
//				return false;
//			}
//		} catch (IllegalArgumentException e) {
//			return false;
//		}
//		return true;
//	}
//}

package com.app.quantitymeasurement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuantityDTO {
	public double value;
	public String unit;
	public String measurementType;
}