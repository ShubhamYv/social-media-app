package com.sky.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sky.models.Post;
import com.sky.models.User;
import com.sky.repository.PostRepository;
import com.sky.repository.UserRepository;
import com.sky.service.PostService;
import com.sky.service.UserService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Post createNewPost(Post post, Integer userId) throws Exception {
		User user = userService.findUserById(userId);
		post.setUser(user);
		post.setCreatedAt(LocalDateTime.now());
		return postRepository.save(post);
	}

	@Override
	public String deletePost(Integer postId, Integer userId) throws Exception {
		Post post = findPostById(postId);
		User user = userService.findUserById(userId);

		if (!post.getUser().getId().equals(user.getId())) {
			throw new Exception("You can't delete another user's post!!!");
		}

		postRepository.delete(post);
		return "Post deleted successfully";
	}

	@Override
	public List<Post> findPostByUserId(Integer userId) throws Exception {
		return postRepository.findPostByUserId(userId);
	}

	@Override
	public Post findPostById(Integer postId) throws Exception {
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new Exception("Post not found with the id : " + postId));
		return post;
	}

	@Override
	public List<Post> findAllPosts() {
		return this.postRepository.findAll();

	}

	@Override
	public Post savedPost(Integer postId, Integer userId) throws Exception {
		Post post = findPostById(postId);
		User user = userService.findUserById(userId);

		if (user.getSavedPost().contains(post)) {
			user.getSavedPost().remove(post);
		}
		user.getSavedPost().add(post);
		userRepository.save(user);
		return post;
	}

	@Override
	public Post likePost(Integer postId, Integer userId) throws Exception {
		Post post = findPostById(postId);
		User user = userService.findUserById(userId);

		if (post.getLiked().contains(user)) {
			post.getLiked().remove(user);
		} else {
			post.getLiked().add(user);
		}
		return postRepository.save(post);

	}

}
