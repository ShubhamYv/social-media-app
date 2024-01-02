package com.sky.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sky.models.Chat;
import com.sky.models.User;
import com.sky.repository.ChatRepository;
import com.sky.service.ChatService;

@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatRepository chatRepository;
	
	@Override
	public Chat createChat(User currentUser, User user2) throws Exception {
		Chat isExist = chatRepository.findChatByUserId(user2, currentUser);
		if (null != isExist) {
			return isExist;
		}
		Chat chat = new Chat();
		chat.getUsers().add(user2);
		chat.getUsers().add(currentUser);
		chat.setTimeStamp(LocalDateTime.now());
		return chatRepository.save(chat);
	}

	@Override
	public Chat findChatById(Integer chatId) throws Exception {
		Chat chat = chatRepository.findById(chatId)
				.orElseThrow(() -> new Exception("Chat is not found with id : " + chatId));
		return chat;
	}

	@Override
	public List<Chat> findAllChatByUserId(Integer userId) {
		List<Chat> chats = chatRepository.findByUsersId(userId);
		return chats;
	}

}
