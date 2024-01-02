package com.sky.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sky.config.JwtProvider;
import com.sky.models.User;
import com.sky.repository.UserRepository;
import com.sky.request.LoginRequest;
import com.sky.response.AuthResponse;
import com.sky.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CustomUserDetailsServiceImpl customUserDetails;

	@Override
	public AuthResponse registerUser(User user) throws Exception {
		if (null != userRepository.findByEmail(user.getEmail())) {
			throw new Exception("This email already used with another account...");
		}
		user.setEmail(user.getEmail());
		user.setFirstName(user.getFirstName());
		user.setLastName(user.getLastName());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User savedUser = userRepository.save(user);

		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),
				savedUser.getPassword());

		String token = JwtProvider.generateToken(authentication);
		AuthResponse authResponse = new AuthResponse(token, "Successfully Registered...");
		return authResponse;
	}

	@Override
	public AuthResponse loginUser(LoginRequest loginRequest) {
		Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());
		String token = JwtProvider.generateToken(authentication);
		AuthResponse authResponse = new AuthResponse(token, "Successfully Login...");
		return authResponse;
	}

	private Authentication authenticate(String email, String password) {
		UserDetails userDetails = customUserDetails.loadUserByUsername(email);
		if (null == userDetails) {
			throw new BadCredentialsException("Invalid Username...");
		}
		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid username or password...");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}
}
