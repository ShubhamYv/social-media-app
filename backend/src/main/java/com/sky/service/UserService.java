package com.sky.service;

import java.util.List;

import com.sky.models.User;

public interface UserService {

	List<User> getAllUsers();

	User findUserById(Integer id) throws Exception;

	User findUserByEmail(String email);

	User followUser(Integer userId1, Integer userId2) throws Exception;

	User updateUser(User user, Integer userId) throws Exception;

	List<User> searchUser(String query);

	void deleteUser(Integer userId) throws Exception;

	User findUserByJwt(String jwt);
}
