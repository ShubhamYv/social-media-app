package com.sky.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sky.config.JwtProvider;
import com.sky.models.User;
import com.sky.repository.UserRepository;
import com.sky.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User findUserById(Integer id) throws Exception {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new Exception("User with id: " + id + "is not found..."));
		return user;
	}

	@Override
	public User findUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}

	@Override
	public User followUser(Integer currentUserId, Integer userId2) throws Exception {
		User currentUser = userRepository.findById(currentUserId)
				.orElseThrow(() -> new Exception("Current User not found..."));
		User user2 = userRepository.findById(userId2).orElseThrow(() -> new Exception("User2 not found..."));

		user2.getFollowers().add(currentUser.getId());
		currentUser.getFollowings().add(user2.getId());

		userRepository.save(currentUser);
		userRepository.save(user2);
		return currentUser;
	}

	@Override
	public User updateUser(User user, Integer userId) throws Exception {
		User user1 = findUserById(userId);

		if (user.getEmail() != null) {
			user1.setEmail(user.getEmail());
		}
		if (user.getFirstName() != null) {
			user1.setFirstName(user.getFirstName());
		}
		if (user.getLastName() != null) {
			user1.setLastName(user.getLastName());
		}
		if (user.getPassword() != null) {
			user1.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		User save = userRepository.save(user1);
		return save;
	}

	@Override
	public List<User> searchUser(String query) {
		List<User> users = this.userRepository.searchUser(query);
		return users;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> allUsers = this.userRepository.findAll();
		return allUsers;
	}

	@Override
	public void deleteUser(Integer userId) throws Exception {
		User user = this.findUserById(userId);
		this.userRepository.delete(user);
	}

	@Override
	public User findUserByJwt(String jwt) {
		String email = JwtProvider.getEmailFromJwtToke(jwt);
		User user = userRepository.findByEmail(email);
		return user;
	}

}
