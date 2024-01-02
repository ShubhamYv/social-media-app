package com.sky.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sky.models.Reels;
import com.sky.models.User;
import com.sky.service.ReelsService;
import com.sky.service.UserService;

@RestController
@RequestMapping("/api")
public class ReelsController {

	@Autowired
	private ReelsService reelsService;

	@Autowired
	private UserService userService;

	@PostMapping("/reels")
	public ResponseEntity<Reels> createReels(@RequestHeader("Authorization") String jwt, @RequestBody Reels reels)
			throws Exception {
		User user = userService.findUserByJwt(jwt);
		Reels newReels = reelsService.createReels(reels, user.getId());
		return new ResponseEntity<Reels>(newReels, HttpStatus.CREATED);
	}

	@GetMapping("/reels")
	public ResponseEntity<List<Reels>> findAllReels() throws Exception {
		List<Reels> reels = reelsService.findAllReels();
		return new ResponseEntity<List<Reels>>(reels, HttpStatus.OK);
	}

	@GetMapping("/reels/user/{userId}")
	public ResponseEntity<List<Reels>> findUsersReels(@PathVariable Integer userId) throws Exception {
		List<Reels> reels = reelsService.findUsersReels(userId);
		return new ResponseEntity<List<Reels>>(reels, HttpStatus.OK);
	}
}
