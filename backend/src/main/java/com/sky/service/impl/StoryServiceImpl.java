package com.sky.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sky.models.Story;
import com.sky.models.User;
import com.sky.repository.StoryRepository;
import com.sky.service.StoryService;
import com.sky.service.UserService;

@Service
public class StoryServiceImpl implements StoryService {

	@Autowired
	private StoryRepository storyRepository;

	@Autowired
	private UserService userService;

	@Override
	public Story createStory(Story story, Integer userId) throws Exception {
		User user = userService.findUserById(userId);
		story.setUser(user);
		story.setTimeStamp(LocalDateTime.now());
		Story newStory = storyRepository.save(story);
		return newStory;
	}

	@Override
	public List<Story> findStoryByUserId(Integer userId) throws Exception {
		User user = userService.findUserById(userId);
		List<Story> allStory = storyRepository.findByUserId(user.getId());
		return allStory;
	}

	@Override
	public String deleteStory(Integer storyId, Integer userId) throws Exception {
		User user = userService.findUserById(userId);
		Story story = storyRepository.findById(storyId)
				.orElseThrow(() -> new Exception("Story not found with id: " + storyId));
		if (!story.getUser().getId().equals(user.getId())) {
			throw new Exception("You can't delete another user's story!!!");
		}
		storyRepository.delete(story);
		return "Story deleted successfully...";
	}

}
