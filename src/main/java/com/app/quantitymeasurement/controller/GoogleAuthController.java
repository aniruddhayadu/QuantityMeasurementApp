package com.app.quantitymeasurement.controller;

import com.app.quantitymeasurement.dto.dtoResponse.AuthResponse;
import com.app.quantitymeasurement.service.GoogleAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/google") // Base Path
public class GoogleAuthController {

    @Autowired
    private GoogleAuthService googleAuthService; // Fixed: Added Autowired to prevent NullPointerException

    /**
     * This endpoint is called automatically after successful Google Login.
     * @param principal contains the Google User details injected by Spring Security.
     * @return AuthResponse containing the JWT token.
     */
    @GetMapping("/success") // Full Path: /auth/google/success
    public ResponseEntity<AuthResponse> handleGoogleSuccess(@AuthenticationPrincipal OAuth2User principal) {
        
        // Safety check: If Google auth fails or session expires
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse(null, "Google Authentication Failed or Principal is Null"));
        }

        // Process user and generate JWT
        AuthResponse response = googleAuthService.processGoogleUser(principal);
        return ResponseEntity.ok(response);
    }
}