package com.sky.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sky.models.Reels;

public interface ReelsRepository extends JpaRepository<Reels, Integer> {

	List<Reels> findByUserId(Integer userId);
}
