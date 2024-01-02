package com.sky.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sky.models.User;
import com.sky.request.LoginRequest;
import com.sky.response.AuthResponse;
import com.sky.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/signup")
	public AuthResponse signUp(@RequestBody User user) throws Exception {
		return authService.registerUser(user);
	}

	@PostMapping("/signin")
	public AuthResponse signIn(@RequestBody LoginRequest loginRequest) {
		return authService.loginUser(loginRequest);
	}
}
