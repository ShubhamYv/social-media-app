package com.sky.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sky.models.Chat;
import com.sky.models.User;
import com.sky.request.ChatRequest;
import com.sky.service.ChatService;
import com.sky.service.UserService;

@RestController
@RequestMapping("/api")
public class ChatController {

	@Autowired
	private ChatService chatService;

	@Autowired
	private UserService userService;

	@PostMapping("/chats")
	public ResponseEntity<Chat> createChat(@RequestHeader("Authorization") String jwt, @RequestBody ChatRequest chat)
			throws Exception {
		User currentUser = userService.findUserByJwt(jwt);
		User user2 = userService.findUserById(chat.getUserId());
		Chat createdChat = chatService.createChat(currentUser, user2);
		return new ResponseEntity<Chat>(createdChat, HttpStatus.CREATED);
	}

	@GetMapping("/chats")
	public ResponseEntity<List<Chat>> findUsersChat(@RequestHeader("Authorization") String jwt) {
		User user = userService.findUserByJwt(jwt);
		List<Chat> chats = chatService.findAllChatByUserId(user.getId());
		return new ResponseEntity<List<Chat>>(chats, HttpStatus.OK);
	}

}
