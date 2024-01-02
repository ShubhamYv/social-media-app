package com.sky.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sky.models.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {

	List<Message> findByChatId(Integer chatId);
}
