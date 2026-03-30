package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.dto.dtoRequest.LoginRequest;
import com.app.quantitymeasurement.dto.dtoRequest.RegisterRequest;
import com.app.quantitymeasurement.dto.dtoResponse.AuthResponse;
import com.app.quantitymeasurement.entity.User;
import com.app.quantitymeasurement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;

	// Fix for Line 45: Matching "registerUser"
	public AuthResponse registerUser(RegisterRequest request) {
		if (userRepository.findByEmail(request.getEmail()).isPresent()) {
			throw new RuntimeException("Email already exists!");
		}
		User user = new User(); // Or use your DTO Mapper here
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		userRepository.save(user);

		String token = jwtService.generateToken(user.getEmail());
		return new AuthResponse(token, "Registration Successful");
	}

	// Fix for Line 50: Matching "loginUser"
	public AuthResponse loginUser(LoginRequest request) {
		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new RuntimeException("User not found"));

		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid Password");
		}

		String token = jwtService.generateToken(user.getEmail());
		return new AuthResponse(token, "Login Successful");
	}
}