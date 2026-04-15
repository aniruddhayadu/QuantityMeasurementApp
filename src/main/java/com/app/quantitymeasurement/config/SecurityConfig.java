package com.app.quantitymeasurement.config;

import com.app.quantitymeasurement.exception.JwtAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtFilter jwtFilter;
	private final JwtAuthenticationEntryPoint authenticationEntryPoint;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.cors(cors -> cors.configurationSource(apiCorsConfigurationSource()))

				.csrf(AbstractHttpConfigurer::disable)

				.exceptionHandling(handler -> handler.authenticationEntryPoint(authenticationEntryPoint))

				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
				.authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**", // Login/Register endpoints
						"/login/**", "/oauth2/**", // Google Auth endpoints
						"/h2-console/**", // Database console
						"/v3/api-docs", // Swagger JSON config (Very Important)
						"/v3/api-docs/**", // Swagger API docs
						"/swagger-ui/**", // Swagger UI files
						"/swagger-ui.html", // Swagger UI entry point
						"/swagger-resources/**", "/webjars/**", // Swagger CSS/JS files
						"/error").permitAll().anyRequest().authenticated() // Baaki sab ke liye Token chahiye
				).oauth2Login(oauth -> oauth.defaultSuccessUrl("http://localhost:8080/auth/google/success", true))

				.headers(headers -> headers.frameOptions(frame -> frame.disable()))

				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

	@Bean
	public CorsConfigurationSource apiCorsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(List.of("http://localhost:3000", "http://localhost:8080", "http://127.0.0.1:5500"));
		config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		config.setAllowedHeaders(List.of("*"));
		config.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}
}
