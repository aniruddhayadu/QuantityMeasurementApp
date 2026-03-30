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
@RequestMapping("api/v1/quantities")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Quantity Measurements")
public class QuantityMeasurementController {

	// Final keyword ensures constructor injection via @RequiredArgsConstructor
	private final IQuantityMeasurementService measurementService;

	@GetMapping("/status")
	public String checkStatus() {
		return "Operational";
	}

	@PostMapping("/compare")
	@Operation(summary = "Compare two quantities")
	public ResponseEntity<QuantityMeasurementDTO> compare(@Valid @RequestBody QuantityInputDTO input) {
		return ResponseEntity.ok(measurementService.compare(input.getThisQuantityDTO(), input.getThatQuantityDTO()));
	}

	@PostMapping("/convert")
	@Operation(summary = "Convert unit")
	public ResponseEntity<QuantityMeasurementDTO> convert(@Valid @RequestBody QuantityInputDTO input) {
		return ResponseEntity.ok(measurementService.convert(input.getThisQuantityDTO(), input.getThatQuantityDTO()));
	}

	@PostMapping("/add")
	@Operation(summary = "Add quantities")
	public ResponseEntity<QuantityMeasurementDTO> add(@Valid @RequestBody QuantityInputDTO input) {
		return ResponseEntity.ok(measurementService.add(input.getThisQuantityDTO(), input.getThatQuantityDTO()));
	}

	@PostMapping("/add-with-target")
	public ResponseEntity<QuantityMeasurementDTO> addWithTarget(@Valid @RequestBody QuantityInputDTO input) {
		return ResponseEntity.ok(measurementService.add(input.getThisQuantityDTO(), input.getThatQuantityDTO(),
				input.getTargetQuantityDTO()));
	}

	@GetMapping("/history/type/{type}")
	public ResponseEntity<List<QuantityMeasurementDTO>> getHistoryByType(@PathVariable String type) {
		return ResponseEntity.ok(measurementService.getMeasurementsByType(type));
	}

	@GetMapping("/history/errored")
	public ResponseEntity<List<QuantityMeasurementDTO>> getErrors() {
		return ResponseEntity.ok(measurementService.getErrorHistory());
	}
}