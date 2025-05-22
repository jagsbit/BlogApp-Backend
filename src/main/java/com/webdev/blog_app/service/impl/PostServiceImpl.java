package com.webdev.blog_app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.webdev.blog_app.execptions.ResourceNotFoundException;
import com.webdev.blog_app.models.Categories;
import com.webdev.blog_app.models.Posts;
import com.webdev.blog_app.models.Users;
import com.webdev.blog_app.payloads.PostDto;
import com.webdev.blog_app.payloads.PostResponse;
import com.webdev.blog_app.repository.CategoryRepo;
import com.webdev.blog_app.repository.PostRepo;
import com.webdev.blog_app.repository.UserRepo;
import com.webdev.blog_app.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto post, int userId, int catId) {
		// TODO Auto-generated method stub

		// Convert DTO to Entity
		Posts postEntity = this.modelMapper.map(post, Posts.class);

		// Fetch user and category from DB
		Users user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		Categories category = categoryRepo.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "catId", catId));
		// setting user and category to post entity
		postEntity.setUser(user);
		postEntity.setCategory(category);
		// Save the post entity
		Posts savedPost = this.postRepo.save(postEntity);
		// Convert Entity to DTO
		PostDto postDto = this.modelMapper.map(savedPost, PostDto.class);
		return postDto;

	}

	@Override
	public PostDto updatePost(PostDto post, int postId) {
		// TODO Auto-generated method stub
		Posts posts = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		posts.setPostTitle(post.getPostTitle());
		posts.setPostContent(post.getPostContent());
		posts.setPostImage(post.getPostImage());

		Posts updatedPost = this.postRepo.save(posts);
		PostDto postDto = this.modelMapper.map(updatedPost, PostDto.class);
		return postDto;
	}

	@Override
	public String deletePost(int postId) {
		// TODO Auto-generated method stub
		Posts post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		this.postRepo.delete(post);
		return "Post deleted successfully";

	}

@Override
public PostResponse getAllPosts(int pageNumber, int pageSize, String sortBy, String sortDir) {
    Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
    Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

    Page<Posts> pagePosts = postRepo.findAll(pageable);

    List<PostDto> postDtos = pagePosts.getContent().stream()
        .map(post -> modelMapper.map(post, PostDto.class))
        .collect(Collectors.toList());

    PostResponse resp = new PostResponse();
    resp.setContent(postDtos);
    resp.setPageNumber(pagePosts.getNumber());
    resp.setPageSize(pagePosts.getSize());
    resp.setTotalElements(pagePosts.getTotalElements());
    resp.setTotalPages(pagePosts.getTotalPages());
    resp.setLastPage(pagePosts.isLast());

    return resp;
}


	@Override
	public PostDto getPostById(int postId) {
		// TODO Auto-generated method stub
		Posts post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		PostDto postDto = modelMapper.map(post, PostDto.class);
		return postDto;

	}

	@Override
	public PostResponse getPostsByCategory(int catId, int pageNumber, int pageSize,String sortBy, String sortDir) {
	    Categories category = categoryRepo.findById(catId)
	            .orElseThrow(() -> new ResourceNotFoundException("Category", "catId", catId));
	    Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
	    Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
	     
	    Page<Posts> postPage = postRepo.findByCategory(category, pageable); // Note: Page<Posts>

	    List<Posts> posts = postPage.getContent();
	    List<PostDto> postDtos = new ArrayList<>();

	    for (Posts post : posts) {
	        PostDto postDto = modelMapper.map(post, PostDto.class);
	        postDtos.add(postDto);
	    }

	    PostResponse response = new PostResponse();
	    response.setContent(postDtos);
	    response.setPageNumber(postPage.getNumber());
	    response.setPageSize(postPage.getSize());
	    response.setTotalElements(postPage.getTotalElements());
	    response.setTotalPages(postPage.getTotalPages());
	    response.setLastPage(postPage.isLast());

	    return response;
	}


	@Override
	public PostResponse getPostsByUser(int userId, int pageNumber, int pageSize,String sortBy, String sortDir) {
	    Users user = userRepo.findById(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

	    Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
	    Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
	    Page<Posts> postPage = postRepo.findByUser(user, pageable); // Correct return type
         
	    List<Posts> posts = postPage.getContent();
	    List<PostDto> postDtos = new ArrayList<>();

	    for (Posts post : posts) {
	        PostDto postDto = modelMapper.map(post, PostDto.class);
	        postDtos.add(postDto);
	    }

	    PostResponse response = new PostResponse();
	    response.setContent(postDtos);
	    response.setPageNumber(postPage.getNumber());
	    response.setPageSize(postPage.getSize());
	    response.setTotalElements(postPage.getTotalElements());
	    response.setTotalPages(postPage.getTotalPages());
	    response.setLastPage(postPage.isLast());

	    return response;
	}


	@Override
	public List<PostDto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		List<Posts> posts = postRepo.findByPostTitleContaining(keyword);
		List<PostDto> postDtos = new ArrayList<>();
		for (Posts post : posts) {
			PostDto postDto = modelMapper.map(post, PostDto.class);
			postDtos.add(postDto);
		}
		return postDtos;
		 
	}

}
