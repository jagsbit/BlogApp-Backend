package com.webdev.blog_app.service;

import java.util.List;

import com.webdev.blog_app.payloads.PostDto;
import com.webdev.blog_app.payloads.PostResponse;

public interface PostService {

	// Create
	PostDto createPost(PostDto post, int userId, int catId);

	// Update
	PostDto updatePost(PostDto post, int postId);

	// Delete
	String deletePost(int postId);

	// Get all posts
	PostResponse getAllPosts(int pageNumber, int pageSize, String sortBy,String sortDir);


	// Get single post
	PostDto getPostById(int postId);

	// Get all posts by category
	PostResponse getPostsByCategory(int catId, int pageNumber, int pageSize,String sortBy,String sortDir);

	// Get all posts by user
	PostResponse getPostsByUser(int userId,int pageNumber, int pageSize,String sortBy,String sortDir);

	// Search posts
	List<PostDto> searchPosts(String keyword);
}
