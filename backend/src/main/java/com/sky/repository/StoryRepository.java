package com.sky.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sky.models.Story;

public interface StoryRepository extends JpaRepository<Story, Integer> {

	List<Story> findByUserId(Integer userId);
}
