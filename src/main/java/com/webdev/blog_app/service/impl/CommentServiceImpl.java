/**
 * 
 */
package com.webdev.blog_app.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webdev.blog_app.execptions.ResourceNotFoundException;
import com.webdev.blog_app.models.Comments;
import com.webdev.blog_app.models.Posts;
import com.webdev.blog_app.models.Users;
import com.webdev.blog_app.payloads.CommentDto;
import com.webdev.blog_app.payloads.UserDto;
import com.webdev.blog_app.repository.CommentRepo;
import com.webdev.blog_app.repository.PostRepo;
import com.webdev.blog_app.repository.UserRepo;
import com.webdev.blog_app.service.CommentService;

/**
 * 
 */
@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	 
	public CommentDto createComment(CommentDto commentDto, int postId, int userId) {
		Posts post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));

		Users user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

		Comments comment = this.modelMapper.map(commentDto, Comments.class);

		post.getComments().add(comment);
		postRepo.save(post);

		user.getComments().add(comment);
		userRepo.save(user);

		comment.setPost(post);
		comment.setUser(user);

		Comments savedComment = this.commentRepo.save(comment);

		CommentDto commentResponse = this.modelMapper.map(savedComment, CommentDto.class);

		// ✅ Manually map the user to UserDto and set it in CommentDto
		commentResponse.setUser(this.modelMapper.map(user, UserDto.class));

		return commentResponse;
	}


	@Override
	public String deleteComment(int commentId, int postId, int userId) {
		// TODO Auto-generated method stub
		Comments comment = commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));
		Posts post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		 Users user = userRepo.findById(userId)
		            .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		 if (comment.getPost().getPostId() != postId) {
			 throw new ResourceNotFoundException("Comment", "postId", postId);
		 }
		if (comment.getUser().getId() != userId) {
				throw new ResourceNotFoundException("Comment", "userId", userId);
		}
		post.getComments().remove(comment);
		user.getComments().remove(comment);
		postRepo.save(post);
		userRepo.save(user);
		
		
		commentRepo.delete(comment);
		return "Comment deleted successfully";
		
		 
	}

}
