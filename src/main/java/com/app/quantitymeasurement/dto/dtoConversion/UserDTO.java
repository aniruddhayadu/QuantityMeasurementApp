package com.app.quantitymeasurement.dto.dtoConversion;

import com.app.quantitymeasurement.dto.dtoRequest.RegisterRequest;
import com.app.quantitymeasurement.entity.User;

public class UserDTO {
	public User toUser(RegisterRequest registerRequest) {
		return new User(registerRequest.getUsername(), registerRequest.getEmail(), registerRequest.getPassword(),
				registerRequest.getRole());
	}
}