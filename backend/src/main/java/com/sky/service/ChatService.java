package com.sky.service;

import java.util.List;

import com.sky.models.Chat;
import com.sky.models.User;

public interface ChatService {

	Chat createChat(User currentUser, User user2) throws Exception;

	Chat findChatById(Integer chatId) throws Exception;

	List<Chat> findAllChatByUserId(Integer userId);
}
