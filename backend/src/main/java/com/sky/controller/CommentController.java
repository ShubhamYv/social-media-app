package com.sky.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sky.models.Comment;
import com.sky.models.Post;
import com.sky.models.User;
import com.sky.service.CommentService;
import com.sky.service.PostService;
import com.sky.service.UserService;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@Autowired
	private UserService userService;

	@Autowired
	private PostService postService;

	@PostMapping("/comments/post/{postId}")
	public ResponseEntity<Comment> createComment(@RequestHeader("Authorization") String jwt,
			@PathVariable Integer postId, @RequestBody Comment comment) throws Exception {
		User user = userService.findUserByJwt(jwt);
		Comment createdComment = commentService.createComment(comment, postId, user.getId());
		return new ResponseEntity<Comment>(createdComment, HttpStatus.CREATED);
	}

	@PutMapping("/comments/like/{commentId}")
	public ResponseEntity<Comment> likeComment(@RequestHeader("Authorization") String jwt,
			@PathVariable Integer commentId) throws Exception {
		User user = userService.findUserByJwt(jwt);
		Comment likedComment = commentService.likeComment(commentId, user.getId());
		return new ResponseEntity<Comment>(likedComment, HttpStatus.OK);
	}

	@GetMapping("/comments/post/{postId}")
	public ResponseEntity<List<Comment>> getAllCommentsByPostId(@PathVariable Integer postId) throws Exception {
		Post post = this.postService.findPostById(postId);
		List<Comment> comments = new ArrayList<>();
		post.getComments().forEach((item) -> comments.add(item));
		return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
	}

}
