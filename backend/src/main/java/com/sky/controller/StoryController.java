package com.sky.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sky.models.Story;
import com.sky.models.User;
import com.sky.response.ApiResponse;
import com.sky.service.StoryService;
import com.sky.service.UserService;

@RestController
@RequestMapping("/api")
public class StoryController {

	@Autowired
	private StoryService storyService;

	@Autowired
	private UserService userService;

	@PostMapping("/story")
	public ResponseEntity<Story> createStory(@RequestHeader("Authorization") String jwt, @RequestBody Story story)
			throws Exception {
		User user = userService.findUserByJwt(jwt);
		Story storyCreated = this.storyService.createStory(story, user.getId());
		return new ResponseEntity<Story>(storyCreated, HttpStatus.CREATED);
	}

	@GetMapping("/story/user/{userId}")
	public ResponseEntity<List<Story>> findUsersStory(@PathVariable Integer userId,
			@RequestHeader("Authorization") String jwt) throws Exception {
		userService.findUserByJwt(jwt);
		List<Story> story = storyService.findStoryByUserId(userId);
		return new ResponseEntity<List<Story>>(story, HttpStatus.OK);
	}

	@DeleteMapping("/story/{storyId}")
	public ResponseEntity<ApiResponse> deleteStory(@RequestHeader("Authorization") String jwt,
			@PathVariable Integer storyId) throws Exception {
		User user = userService.findUserByJwt(jwt);
		String deleteStory = storyService.deleteStory(storyId, user.getId());
		ApiResponse apiResponse = new ApiResponse(deleteStory, true);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}
}
