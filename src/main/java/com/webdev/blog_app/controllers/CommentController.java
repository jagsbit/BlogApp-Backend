package com.webdev.blog_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webdev.blog_app.payloads.CommentDto;
import com.webdev.blog_app.service.impl.CommentServiceImpl;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
 
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    // ✅ 1. Create a comment
    @PostMapping("/post/{postId}/user/{userId}")
    public ResponseEntity<CommentDto> createComment(
            @RequestBody CommentDto commentDto,
            @PathVariable int postId,
            @PathVariable int userId) {

        CommentDto createdComment = commentService.createComment(commentDto, postId, userId);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    // ✅ 2. Delete a comment
    @DeleteMapping("/{commentId}/post/{postId}/user/{userId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable int commentId,
            @PathVariable int postId,
            @PathVariable int userId) {

        String responseMessage = commentService.deleteComment(commentId, postId, userId);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}