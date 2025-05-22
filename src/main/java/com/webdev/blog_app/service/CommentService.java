package com.webdev.blog_app.service;

import com.webdev.blog_app.payloads.CommentDto;

public interface CommentService {
   CommentDto createComment(CommentDto commentDto, int postId, int userId);
   String deleteComment(int commentId, int postId, int userId);
}
