package com.webdev.blog_app.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.webdev.blog_app.config.AppConstants;
import com.webdev.blog_app.payloads.PostDto;
import com.webdev.blog_app.payloads.PostResponse;
import com.webdev.blog_app.service.impl.FileServiceImpl;
import com.webdev.blog_app.service.impl.LikeServiceImpl;
import com.webdev.blog_app.service.impl.PostServiceImpl;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletResponse;

 

@RestController
 
@RequestMapping("/api")
public class PostController {
	
	@Autowired
	private PostServiceImpl postService;
	
	@Autowired
	private FileServiceImpl fileService;
	
	@Autowired
    private LikeServiceImpl likeService;
	
	@Value("${project.image}")
	private String path;
	
	@PostMapping("/user/{userId}/category/{catId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable int userId, @PathVariable int catId) {
		PostDto createdPost = this.postService.createPost(postDto, userId, catId);
		return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
	}
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<PostResponse> getPostsByUser(@PathVariable int userId,@RequestParam(value = "pageNumber", defaultValue =AppConstants.PAGE_NUMBER , required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir
			) {
		PostResponse posts = this.postService.getPostsByUser(userId,pageNumber, pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponse>(posts, HttpStatus.OK);
	}
	@GetMapping("/category/{catId}/posts")
	public ResponseEntity<PostResponse> getPostsByCategory(@PathVariable int catId,@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir)  {
		PostResponse posts = this.postService.getPostsByCategory(catId,pageNumber,pageSize,sortBy, sortDir);
		return new ResponseEntity<PostResponse>(posts, HttpStatus.OK);
	}
	
@GetMapping("/posts")
public ResponseEntity<PostResponse> getAllPosts(
		@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNumber,
		@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) int pageSize,
		@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
		@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {

	PostResponse posts = this.postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir);
	return new ResponseEntity<>(posts, HttpStatus.OK);
}

	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable int postId) {
		PostDto post = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(post, HttpStatus.OK);
	}
	
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<String> deletePost(@PathVariable int postId) {
		this.postService.deletePost(postId);
		return new ResponseEntity<String>("Post deleted successfully", HttpStatus.OK);
	}
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable int postId) {
		PostDto updatedPost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
	}
	
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPosts(@PathVariable String keyword) {
		List<PostDto> result = this.postService.searchPosts(keyword);
		return new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);
	}
	
	//upload image
	
	@PostMapping("/posts/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadImage(@RequestParam("image") MultipartFile image, @PathVariable int postId) throws IOException {
		 PostDto postDto = this.postService.getPostById(postId);
		 String fileName=this.fileService.uploadImage(path, image);
		 postDto.setPostImage(fileName);
		 PostDto updatedPost = this.postService.updatePost(postDto, postId);
		 return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
	}
	@GetMapping(value="/posts/image/{imageName}",produces =MediaType.IMAGE_JPEG_VALUE)
	public void  downloadImage(@PathVariable String imageName,HttpServletResponse response) throws IOException {
        InputStream resource=this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }
	
	@PostMapping("/posts/like/{postId}/{userId}")
	public void addLike( @PathVariable int postId,@PathVariable int userId){
		 likeService.addLike(postId, userId);
	}
	
	 
	

}
