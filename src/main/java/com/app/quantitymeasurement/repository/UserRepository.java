package com.app.quantitymeasurement.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.quantitymeasurement.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Finds a user by their unique email address. Essential for JWT Authentication
	 * and Google OAuth login.
	 */
	Optional<User> findByEmail(String email);
}