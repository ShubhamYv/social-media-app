package com.sky.service;

import java.util.List;

import com.sky.models.Story;

public interface StoryService {

	Story createStory(Story story, Integer userId) throws Exception;

	List<Story> findStoryByUserId(Integer userId) throws Exception;

	String deleteStory(Integer storyId, Integer userId) throws Exception;
}
