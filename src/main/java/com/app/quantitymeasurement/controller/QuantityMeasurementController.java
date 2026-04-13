package com.app.quantitymeasurement.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.app.quantitymeasurement.dto.QuantityInputDTO;
import com.app.quantitymeasurement.entity.QuantityMeasurementDTO;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/quantities")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Quantity Measurements", description = "Operations for comparison, conversion and arithmetic")
public class QuantityMeasurementController {

    private final IQuantityMeasurementService measurementService;

    @GetMapping("/status")
    public String checkStatus() {
        return "Quantity Measurement Service is Operational";
    }

    @PostMapping("/compare")
    @Operation(summary = "Compare two quantities")
    public ResponseEntity<QuantityMeasurementDTO> compare(@Valid @RequestBody QuantityInputDTO input) {
        log.info("Comparing: {} and {}", input.getThisQuantityDTO(), input.getThatQuantityDTO());
        QuantityMeasurementDTO response = measurementService.compare(
                input.getThisQuantityDTO(), 
                input.getThatQuantityDTO()
        );
        fillInputDetails(response, input);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/convert")
    @Operation(summary = "Convert a unit to another")
    public ResponseEntity<QuantityMeasurementDTO> convert(@Valid @RequestBody QuantityInputDTO input) {
        log.info("Converting: {}", input.getThisQuantityDTO());
        QuantityMeasurementDTO response = measurementService.convert(
                input.getThisQuantityDTO(), 
                input.getThatQuantityDTO()
        );
        fillInputDetails(response, input);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    @Operation(summary = "Add two quantities")
    public ResponseEntity<QuantityMeasurementDTO> add(@Valid @RequestBody QuantityInputDTO input) {
        QuantityMeasurementDTO response;
        if (input.getTargetQuantityDTO() != null) {
            response = measurementService.add(input.getThisQuantityDTO(), input.getThatQuantityDTO(), input.getTargetQuantityDTO());
        } else {
            response = measurementService.add(input.getThisQuantityDTO(), input.getThatQuantityDTO());
        }
        fillInputDetails(response, input);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/subtract")
    @Operation(summary = "Subtract one quantity from another")
    public ResponseEntity<QuantityMeasurementDTO> subtract(@Valid @RequestBody QuantityInputDTO input) {
        QuantityMeasurementDTO response;
        if (input.getTargetQuantityDTO() != null) {
            response = measurementService.subtract(input.getThisQuantityDTO(), input.getThatQuantityDTO(), input.getTargetQuantityDTO());
        } else {
            response = measurementService.subtract(input.getThisQuantityDTO(), input.getThatQuantityDTO());
        }
        fillInputDetails(response, input);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/divide")
    @Operation(summary = "Divide two quantities")
    public ResponseEntity<QuantityMeasurementDTO> divide(@Valid @RequestBody QuantityInputDTO input) {
        QuantityMeasurementDTO response = measurementService.divide(
                input.getThisQuantityDTO(), 
                input.getThatQuantityDTO()
        );
        fillInputDetails(response, input);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/history/{operation}")
    @Operation(summary = "Get history by operation type (ADD, COMPARE, etc.)")
    public ResponseEntity<List<QuantityMeasurementDTO>> getHistoryByOperation(@PathVariable String operation) {
        return ResponseEntity.ok(measurementService.getOperationHistory(operation));
    }

    @GetMapping("/history/type/{type}")
    @Operation(summary = "Get history by measurement type (LengthUnit, WeightUnit, etc.)")
    public ResponseEntity<List<QuantityMeasurementDTO>> getHistoryByType(@PathVariable String type) {
        return ResponseEntity.ok(measurementService.getMeasurementsByType(type));
    }

    @GetMapping("/history/errored")
    @Operation(summary = "Get all failed operations")
    public ResponseEntity<List<QuantityMeasurementDTO>> getErrors() {
        return ResponseEntity.ok(measurementService.getErrorHistory());
    }

    @GetMapping("/count/{operation}")
    public ResponseEntity<Long> getCount(@PathVariable String operation) {
        return ResponseEntity.ok(measurementService.getOperationCount(operation));
    }

    /**
     * Helper to ensure the response sent back to UI contains input values 
     * to prevent "0 null" display issues.
     */
    private void fillInputDetails(QuantityMeasurementDTO dto, QuantityInputDTO input) {
        if (dto != null && input.getThisQuantityDTO() != null) {
            dto.setThisValue(input.getThisQuantityDTO().getValue());
            dto.setThisUnit(input.getThisQuantityDTO().getUnit());
            dto.setThisMeasurementType(input.getThisQuantityDTO().getMeasurementType());

            if (input.getThatQuantityDTO() != null) {
                dto.setThatValue(input.getThatQuantityDTO().getValue());
                dto.setThatUnit(input.getThatQuantityDTO().getUnit());
                dto.setThatMeasurementType(input.getThatQuantityDTO().getMeasurementType());
            }
        }
    }
}