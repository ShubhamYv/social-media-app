package com.sky.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sky.models.Reels;
import com.sky.models.User;
import com.sky.repository.ReelsRepository;
import com.sky.service.ReelsService;
import com.sky.service.UserService;

@Service
public class ReelsServiceImpl implements ReelsService {

	@Autowired
	private ReelsRepository reelsRepository;

	@Autowired
	private UserService userService;

	@Override
	public Reels createReels(Reels reels, Integer userId) throws Exception {
		User user = userService.findUserById(userId);
		reels.setUser(user);
		Reels newReels = reelsRepository.save(reels);
		return newReels;
	}

	@Override
	public List<Reels> findAllReels() {
		List<Reels> allReels = reelsRepository.findAll();
		return allReels;
	}

	@Override
	public List<Reels> findUsersReels(Integer userId) throws Exception {
		User user = userService.findUserById(userId);
		List<Reels> reels = reelsRepository.findByUserId(user.getId());
		return reels;
	}

}
