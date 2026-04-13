package com.app.quantitymeasurement.controller;

import com.app.quantitymeasurement.dto.dtoResponse.AuthResponse;
import com.app.quantitymeasurement.service.GoogleAuthService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
@RequestMapping("/auth/google")
public class GoogleAuthController {

    @Autowired
    private GoogleAuthService googleAuthService;

    @GetMapping("/success")
    public void handleGoogleSuccess(@AuthenticationPrincipal OAuth2User principal, HttpServletResponse response) throws IOException {
        
        if (principal == null) {
            // ✅ Frontend login page redirect with error
            response.sendRedirect("https://quantitymeasurementapp-frontend-production-ebb9.up.railway.app/login?error=auth_failed");
            return;
        }

        // 1. JWT Token generate karo
        AuthResponse authResponse = googleAuthService.processGoogleUser(principal);
        String token = authResponse.getToken();

        // 2. ✅ Fix: Frontend Production URL par bhejo token ke saath
        String frontendRedirectUrl = "https://quantitymeasurementapp-frontend-production-ebb9.up.railway.app/login-success?token=" + token;
        
        response.sendRedirect(frontendRedirectUrl);
    }
}