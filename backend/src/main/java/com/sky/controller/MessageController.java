package com.sky.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sky.models.Message;
import com.sky.models.User;
import com.sky.service.MessageService;
import com.sky.service.UserService;

@RestController
@RequestMapping("/api")
public class MessageController {

	@Autowired
	private MessageService messageService;

	@Autowired
	private UserService userService;

	@PostMapping("/messages/chat/{chatId}")
	public ResponseEntity<Message> createMessage(@RequestBody Message message,
			@RequestHeader("Authorization") String jwt, @PathVariable Integer chatId) throws Exception {
		User user = userService.findUserByJwt(jwt);
		Message createMessage = messageService.createMessage(user.getId(), chatId, message);
		return new ResponseEntity<Message>(createMessage, HttpStatus.CREATED);
	}

	@GetMapping("/messages/chat/{chatId}")
	public ResponseEntity<List<Message>> findChatsMessage(@RequestHeader("Authorization") String jwt,
			@PathVariable Integer chatId) throws Exception {
		User user = userService.findUserByJwt(jwt);
		List<Message> message = messageService.findChatsMessage(chatId);
		return new ResponseEntity<List<Message>>(message, HttpStatus.OK);
	}
}
