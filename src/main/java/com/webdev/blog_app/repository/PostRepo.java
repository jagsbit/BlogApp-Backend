package com.webdev.blog_app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.webdev.blog_app.models.Categories;
import com.webdev.blog_app.models.Posts;
import com.webdev.blog_app.models.Users;

public interface PostRepo extends JpaRepository<Posts, Integer> {

	Page<Posts> findByCategory(Categories category,Pageable pageable);
	Page<Posts> findByUser(Users user,Pageable pageable);
	
	List<Posts> findByPostTitleContaining(String keyword);
	
	@Query("SELECT p FROM Posts p WHERE p.postTitle LIKE %:keyword% OR p.postContent LIKE %:keyword%")
	List<Posts> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
   