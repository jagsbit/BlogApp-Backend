package com.webdev.blog_app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webdev.blog_app.execptions.ResourceNotFoundException;
import com.webdev.blog_app.models.Posts;
import com.webdev.blog_app.models.Users;
import com.webdev.blog_app.payloads.PostDto;
import com.webdev.blog_app.repository.PostRepo;
import com.webdev.blog_app.repository.UserRepo;
import com.webdev.blog_app.service.LikeService;

@Service
public class LikeServiceImpl implements LikeService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public void addLike(int postId, int userId) {
		// TODO Auto-generated method stub
		Posts post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		Users user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

		post.getLikes().add(user);
		postRepo.save(post);
		
		

	}
	
	 
}
