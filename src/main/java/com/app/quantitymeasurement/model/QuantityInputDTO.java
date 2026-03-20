//package com.app.quantitymeasurement.model;
//
//import io.swagger.v3.oas.annotations.media.Schema;
//import jakarta.validation.Valid;
//import jakarta.validation.constraints.NotNull;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Schema(example = """
//		{
//			"thisQuantityDTO": { "value": 1.0, "unit": "FEET", "measurementType": "LengthUnit" },
//			"thatQuantityDTO": { "value": 12.0, "unit": "INCHES", "measurementType": "LengthUnit" },
//			"targetQuantityDTO": { "value": 0.0, "unit": "INCHES", "measurementType": "LengthUnit" }
//		}
//		""")
//public class QuantityInputDTO {
//
//	@Valid
//	@NotNull(message = "First quantity cannot be null")
//	private QuantityDTO thisQuantityDTO;
//
//	@Valid
//	@NotNull(message = "Second quantity cannot be null")
//	private QuantityDTO thatQuantityDTO;
//
//	@Valid
//	@Schema(nullable = true)
//	private QuantityDTO targetQuantityDTO;
//
//	public QuantityInputDTO(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO, QuantityDTO targetQuantityDTO) {
//		this.thisQuantityDTO = thisQuantityDTO;
//		this.thatQuantityDTO = thatQuantityDTO;
//		this.targetQuantityDTO = targetQuantityDTO;
//	}
//
//	public QuantityDTO getThisQuantityDTO() {
//		return thisQuantityDTO;
//	}
//
//	public void setThisQuantityDTO(QuantityDTO thisQuantityDTO) {
//		this.thisQuantityDTO = thisQuantityDTO;
//	}
//
//	public QuantityDTO getThatQuantityDTO() {
//		return thatQuantityDTO;
//	}
//
//	public void setThatQuantityDTO(QuantityDTO thatQuantityDTO) {
//		this.thatQuantityDTO = thatQuantityDTO;
//	}
//
//	public QuantityDTO getTargetQuantityDTO() {
//		return targetQuantityDTO;
//	}
//
//	public void setTargetQuantityDTO(QuantityDTO targetQuantityDTO) {
//		this.targetQuantityDTO = targetQuantityDTO;
//	}
//}

package com.app.quantitymeasurement.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuantityInputDTO {

	@Valid
	@NotNull(message = "First quantity cannot be null")
	private QuantityDTO thisQuantityDTO;

	@Valid
	@NotNull(message = "Second quantity cannot be null")
	private QuantityDTO thatQuantityDTO;

	@Valid
	private QuantityDTO targetQuantityDTO;
}