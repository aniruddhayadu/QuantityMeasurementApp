package com.app.quantitymeasurement.controller;

import com.app.quantitymeasurement.dto.dtoResponse.AuthResponse;
import com.app.quantitymeasurement.service.GoogleAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/google")
public class GoogleAuthController {

	@Autowired
	private GoogleAuthService googleAuthService;

	@GetMapping("/callback")
	public ResponseEntity<AuthResponse> handleGoogleCallback(@RequestParam String code) {
		// Line 21/35 Fix: GoogleAuthService mein handleGoogleAuth method hona chahiye
		return ResponseEntity.ok(googleAuthService.handleGoogleAuth(code));
	}
}