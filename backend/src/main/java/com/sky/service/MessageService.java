package com.sky.service;

import java.util.List;

import com.sky.models.Message;

public interface MessageService {

	Message createMessage(Integer userId, Integer chatId, Message req) throws Exception;

	List<Message> findChatsMessage(Integer chatId) throws Exception;

}
