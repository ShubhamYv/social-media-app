package com.sky.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sky.models.Chat;
import com.sky.models.User;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

	List<Chat> findByUsersId(Integer userId);

	@Query("SELECT c FROM Chat c WHERE :user Member Of c.users And :currentUser Member Of c.users")
	Chat findChatByUserId(@Param("user") User user, @Param("currentUser") User currentUser);
}
