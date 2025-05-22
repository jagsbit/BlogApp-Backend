package com.webdev.blog_app.service;

import com.webdev.blog_app.payloads.PostDto;

public interface LikeService {
   void addLike(int postId,int userId );
   
}
