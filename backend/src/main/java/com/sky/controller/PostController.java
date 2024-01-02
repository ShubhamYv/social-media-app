package com.sky.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sky.models.Post;
import com.sky.models.User;
import com.sky.response.ApiResponse;
import com.sky.service.PostService;
import com.sky.service.UserService;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private UserService userService;

	@PostMapping("/posts")
	public ResponseEntity<Post> createPost(@RequestHeader("Authorization") String jwt, @RequestBody Post post)
			throws Exception {
		User currentUser = userService.findUserByJwt(jwt);
		Post newPost = this.postService.createNewPost(post, currentUser.getId());
		return new ResponseEntity<Post>(newPost, HttpStatus.CREATED);
	}

	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@RequestHeader("Authorization") String jwt,
			@PathVariable Integer postId) throws Exception {
		User currentUser = userService.findUserByJwt(jwt);
		String deletePost = this.postService.deletePost(postId, currentUser.getId());
		ApiResponse response = new ApiResponse(deletePost, true);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}

	@GetMapping("/posts/{postId}")
	public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws Exception {
		Post post = this.postService.findPostById(postId);
		return new ResponseEntity<Post>(post, HttpStatus.OK);
	}

	@GetMapping("/posts/user/{userId}")
	public ResponseEntity<List<Post>> findUsersAllPost(@PathVariable Integer userId) throws Exception {
		List<Post> allPosts = this.postService.findPostByUserId(userId);
		return new ResponseEntity<List<Post>>(allPosts, HttpStatus.OK);
	}

	@GetMapping("/posts")
	public ResponseEntity<List<Post>> findAllPost() {
		List<Post> allPosts = this.postService.findAllPosts();
		return new ResponseEntity<List<Post>>(allPosts, HttpStatus.OK);
	}

	@PutMapping("/posts/{postId}")
	public ResponseEntity<Post> savedPostHandler(@RequestHeader("Authorization") String jwt,
			@PathVariable Integer postId) throws Exception {
		User currentUser = userService.findUserByJwt(jwt);
		Post savedPost = this.postService.savedPost(postId, currentUser.getId());
		return new ResponseEntity<Post>(savedPost, HttpStatus.OK);
	}

	@PutMapping("/posts/like/{postId}")
	public ResponseEntity<Post> likePostHandler(@RequestHeader("Authorization") String jwt,
			@PathVariable Integer postId) throws Exception {
		User currentUser = userService.findUserByJwt(jwt);
		Post likedPost = this.postService.likePost(postId, currentUser.getId());
		return new ResponseEntity<Post>(likedPost, HttpStatus.OK);
	}
}
