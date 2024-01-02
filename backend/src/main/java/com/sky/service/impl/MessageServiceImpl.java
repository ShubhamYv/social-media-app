package com.sky.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sky.models.Chat;
import com.sky.models.Message;
import com.sky.models.User;
import com.sky.repository.ChatRepository;
import com.sky.repository.MessageRepository;
import com.sky.service.ChatService;
import com.sky.service.MessageService;
import com.sky.service.UserService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private ChatRepository chatRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private ChatService chatService;

	@Override
	public Message createMessage(Integer userId, Integer chatId, Message req) throws Exception {
		User user = userService.findUserById(userId);
		Chat chat = chatService.findChatById(chatId);
		Message message = new Message();
		message.setChat(chat);
		message.setContent(req.getContent());
		message.setImage(req.getImage());
		message.setUser(user);
		message.setTimeStamp(LocalDateTime.now());

		Message savedMessage = messageRepository.save(message);
		chat.getMessages().add(savedMessage);
		chatRepository.save(chat);
		return savedMessage;
	}

	@Override
	public List<Message> findChatsMessage(Integer chatId) throws Exception {
		Chat chat = chatService.findChatById(chatId);
		List<Message> messages = messageRepository.findByChatId(chat.getId());
		return messages;
	}
}
