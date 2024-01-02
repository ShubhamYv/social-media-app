package com.sky.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sky.models.Comment;
import com.sky.models.Post;
import com.sky.models.User;
import com.sky.repository.CommentRepository;
import com.sky.service.CommentService;
import com.sky.service.PostService;
import com.sky.service.UserService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private PostService postService;

	@Override
	public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception {
		Post post = postService.findPostById(postId);
		User user = userService.findUserById(userId);
		comment.setUser(user);
		comment.setCreatedAt(LocalDateTime.now());
		comment.setPost(post);
		Comment savedComment = commentRepository.save(comment);
		return savedComment;
	}

	@Override
	public Comment likeComment(Integer commentId, Integer userId) throws Exception {
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new Exception("Comment is not found with the id : " + commentId));
		User user = userService.findUserById(userId);

		if (comment.getLiked().contains(user)) {
			comment.getLiked().remove(user);
		}
		comment.getLiked().add(user);
		Comment likedComment = commentRepository.save(comment);
		return likedComment;
	}

	@Override
	public Comment findCommentById(Integer commentId) throws Exception {
		Comment comment = this.commentRepository.findById(commentId)
				.orElseThrow(() -> new Exception("Comment not found with the id : " + commentId));
		return comment;
	}

	@Override
	public String deleteComment(Integer commentId) throws Exception {
		Comment comment = this.commentRepository.findById(commentId)
				.orElseThrow(() -> new Exception("Comment not found with the id : " + commentId));
		commentRepository.delete(comment);
		return "Comment deleted successfully...";
	}

	@Override
	public Comment updateComment(Comment comment, Integer commentId) throws Exception {
		Comment updateComment = this.commentRepository.findById(commentId)
				.orElseThrow(() -> new Exception("Comment not found with the id : " + commentId));
		updateComment.setContent(comment.getContent());
		updateComment.setCreatedAt(comment.getCreatedAt());
		updateComment.setLiked(comment.getLiked());
		updateComment.setUser(comment.getUser());
		Comment updatedComment = commentRepository.save(updateComment);
		return updatedComment;
	}

}
