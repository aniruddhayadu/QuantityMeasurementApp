package com.app.quantitymeasurement.controller;

import com.app.quantitymeasurement.dto.dtoRequest.LoginRequest;
import com.app.quantitymeasurement.dto.dtoRequest.RegisterRequest;
import com.app.quantitymeasurement.dto.dtoResponse.AuthResponse;
import com.app.quantitymeasurement.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/user")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "User Authentication", description = "Endpoints for user onboarding and secure session management")
public class UserController {

	private final UserService userService;

	// --- REUSABLE SWAGGER EXAMPLES ---
	private static final String EXAMPLE_REG_1 = "{\"username\":\"aniruddha\", \"email\":\"ani@test.com\", \"password\":\"pass123\", \"role\":\"USER\"}";
	private static final String EXAMPLE_REG_2 = "{\"username\":\"kapil\", \"email\":\"kapil@test.com\", \"password\":\"kapil123\", \"role\":\"USER\"}";
	private static final String EXAMPLE_LOGIN_1 = "{\"email\":\"ani@test.com\", \"password\":\"pass123\"}";

	@GetMapping("/status")
	public String checkServiceStatus() {
		log.info("Auth service health check called.");
		return "Authentication Service is active!";
	}

	@PostMapping("/register")
	@Operation(summary = "Create a new user account", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = {
			@ExampleObject(name = "User Alpha", value = EXAMPLE_REG_1),
			@ExampleObject(name = "User Beta", value = EXAMPLE_REG_2) })))
	public ResponseEntity<AuthResponse> onboardUser(@RequestBody RegisterRequest registerRequest) {
		log.info("Processing registration for email: {}", registerRequest.getEmail());
		return ResponseEntity.ok(userService.registerUser(registerRequest));
	}

	@PostMapping("/login")
	@Operation(summary = "Authenticate user and generate token", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = {
			@ExampleObject(name = "Default Login", value = EXAMPLE_LOGIN_1) })))
	public ResponseEntity<AuthResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
		log.info("Processing login request for: {}", loginRequest.getEmail());
		return ResponseEntity.ok(userService.loginUser(loginRequest));
	}
}