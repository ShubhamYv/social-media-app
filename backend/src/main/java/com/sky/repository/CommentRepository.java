package com.sky.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sky.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
