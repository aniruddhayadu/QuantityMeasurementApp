package com.app.quantitymeasurement.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Data 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder 
public class QuantityMeasurementDTO {
    public double thisValue;
    public String thisUnit;
    public String thisMeasurementType;
    public double thatValue;
    public String thatUnit;
    public String thatMeasurementType;
    public String operation;
    public double resultValue;
    public String resultUnit;
    public String resultString;
    public String resultMeasurementType;

    @JsonProperty("error")
    public boolean error;
    public String errorMessage;
}