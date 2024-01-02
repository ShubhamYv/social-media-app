package com.sky.service;

import java.util.List;

import com.sky.models.Reels;

public interface ReelsService {

	Reels createReels(Reels reels, Integer userId) throws Exception;

	List<Reels> findAllReels();

	List<Reels> findUsersReels(Integer userId) throws Exception;

}
