package com.webdev.blog_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webdev.blog_app.models.Comments;

public interface CommentRepo extends JpaRepository<Comments, Integer> {

}
