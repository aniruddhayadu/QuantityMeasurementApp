package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.dto.dtoResponse.AuthResponse;
import com.app.quantitymeasurement.entity.User;
import com.app.quantitymeasurement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class GoogleAuthService {

	private final JwtService jwtService;
	private final UserRepository userRepository;

	public AuthResponse handleGoogleAuth(String code) {
		log.info("Processing Google OAuth code: {}", code);

		// Simulation: Email extracted from Google Auth Code
		String googleEmail = "google_user@gmail.com";

		Optional<User> existingUser = userRepository.findByEmail(googleEmail);
		User user;

		if (existingUser.isPresent()) {
			user = existingUser.get();
		} else {
			// Line 43 Fix: Traditional instantiation instead of .builder()
			log.info("New Google user detected. Registering: {}", googleEmail);
			user = new User();
			user.setUsername("GoogleUser_" + System.currentTimeMillis());
			user.setEmail(googleEmail);
			user.setPassword("OAUTH_PROTECTED");
			user.setRole("ROLE_USER");
			user = userRepository.save(user);
		}

		String token = jwtService.generateToken(user.getEmail());

		return new AuthResponse(token, "Google Authentication Successful");
	}
}