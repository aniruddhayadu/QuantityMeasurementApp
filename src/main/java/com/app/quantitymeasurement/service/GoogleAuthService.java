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
		String googleEmail = oAuth2User.getAttribute("email");
		String name = oAuth2User.getAttribute("name");

		Optional<User> existingUser = userRepository.findByEmail(googleEmail);
		User user;

		if (existingUser.isPresent()) {
			user = existingUser.get();
		} else {
			user = new User();
			user.setUsername(name != null ? name : "GoogleUser_" + System.currentTimeMillis());
			user.setEmail(googleEmail);
			user.setPassword("OAUTH_EXTERNAL");
			user.setRole("ROLE_USER");
			user = userRepository.save(user);
		}

		String token = jwtService.generateToken(user.getEmail());
		return new AuthResponse(token, "Login Successful for " + googleEmail);
	}
}