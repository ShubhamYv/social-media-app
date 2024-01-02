package com.sky.service;

import com.sky.models.Comment;

public interface CommentService {

	Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception;

	Comment likeComment(Integer commentId, Integer userId) throws Exception;

	Comment findCommentById(Integer commentId) throws Exception;

	String deleteComment(Integer commentId) throws Exception;

	Comment updateComment(Comment comment, Integer commentId) throws Exception;
}
