package com.sky.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sky.models.User;
import com.sky.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public List<User> getAllUsers() {
		List<User> allUsers = this.userService.getAllUsers();
		return allUsers;
	}

	@GetMapping("/users/{userId}")
	public User getUserById(@PathVariable("userId") Integer id) throws Exception {
		User user = userService.findUserById(id);
		return user;
	}

	@PutMapping("/users")
	public User updateUser(@RequestHeader("Authorization") String jwt,
			@RequestBody User user) throws Exception {
		User currentUser = userService.findUserByJwt(jwt);
		return this.userService.updateUser(user, currentUser.getId());
	}

	@DeleteMapping("/users/{userId}")
	public String deleteUser(@PathVariable Integer id) throws Exception {
		this.userService.deleteUser(id);
		return "User deleted successfully";
	}

	@PutMapping("/users/follow/{userId2}")
	public User followUserHandler(@RequestHeader("Authorization") String jwt,
			@PathVariable Integer userId2) throws Exception {
		User currentUser = userService.findUserByJwt(jwt);
		User user = this.userService.followUser(currentUser.getId(), userId2);
		return user;
	}

	@GetMapping("/users/search")
	public List<User> searchUser(@RequestParam("query") String query) {
		List<User> users = this.userService.searchUser(query);
		return users;
	}

	@GetMapping("/users/profile")
	public User getUserFromToken(@RequestHeader("Authorization") String jwt) {
		System.out.println("jwt------ " + jwt);
		User user = userService.findUserByJwt(jwt);
		return user;
	}
}
