package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.dto.dtoResponse.AuthResponse;
import com.app.quantitymeasurement.entity.User;
import com.app.quantitymeasurement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class GoogleAuthService {

	private final JwtService jwtService;
	private final UserRepository userRepository;

	public AuthResponse processGoogleUser(OAuth2User oAuth2User) {
		// Extract real email and name from Google Profile attributes
		String googleEmail = oAuth2User.getAttribute("email");
		String name = oAuth2User.getAttribute("name");

		log.info("Processing Google Login for email: {}", googleEmail);

		Optional<User> existingUser = userRepository.findByEmail(googleEmail);
		User user;

		if (existingUser.isPresent()) {
			user = existingUser.get();
			log.info("Existing Google user found: {}", googleEmail);
		} else {
			log.info("New Google user detected. Registering: {}", googleEmail);
			// Manual instantiation as per your requirement
			user = new User();
			user.setUsername(name != null ? name : "GoogleUser_" + System.currentTimeMillis());
			user.setEmail(googleEmail);
			user.setPassword("OAUTH_EXTERNAL_PROVIDER"); // Not used for OAuth users
			user.setRole("ROLE_USER");
			user = userRepository.save(user);
		}

		// Generate the JWT token using your internal service
		String token = jwtService.generateToken(user.getEmail());

		return new AuthResponse(token, "Google Authentication Successful for " + googleEmail);
	}
}