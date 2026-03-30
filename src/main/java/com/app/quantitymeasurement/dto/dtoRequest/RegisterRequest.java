package com.app.quantitymeasurement.dto.dtoRequest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // Required for JSON deserialization in Spring Boot
@AllArgsConstructor
public class RegisterRequest {

	@NotBlank(message = "Username cannot be empty")
	private String username;

	@NotBlank(message = "Email is required")
	@Email(message = "Please provide a valid email address")
	private String email;

	@NotBlank(message = "Password is required")
	@Size(min = 6, message = "Password must be at least 6 characters long")
	private String password;

	@NotBlank(message = "User role must be specified")
	private String role;
}