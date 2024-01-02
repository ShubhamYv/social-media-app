package com.sky.service;

import com.sky.models.User;
import com.sky.request.LoginRequest;
import com.sky.response.AuthResponse;

public interface AuthService {

	// SignUp
	AuthResponse registerUser(User user) throws Exception;

	// LogIn
	AuthResponse loginUser(LoginRequest loginRequest);
}
