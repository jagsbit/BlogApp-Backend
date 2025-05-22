package com.webdev.blog_app.payloads;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.webdev.blog_app.models.Users;

public class PostDto {

    private int postId;
    private String postTitle;
    private String postContent;
    private String postImage;

    private CatDto category;
    private UserDto user;

    

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private OffsetDateTime postDate;

    private List<CommentDto> comments = new ArrayList<>();
    
    private Set<UserDto> likes=new HashSet<>();
    // Constructors
    public PostDto() {}

    public PostDto(int postId, String postTitle, String postContent, String postImage,
                   CatDto category, UserDto user, OffsetDateTime postDate, List<CommentDto> comments) {
        this.postId = postId;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.postImage = postImage;
        this.category = category;
        this.user = user;
        this.postDate = postDate;
        this.comments = comments;
    }

    // Getters and Setters
    public int getPostId() { return postId; }
    public void setPostId(int postId) { this.postId = postId; }

    public String getPostTitle() { return postTitle; }
    public void setPostTitle(String postTitle) { this.postTitle = postTitle; }

    public String getPostContent() { return postContent; }
    public void setPostContent(String postContent) { this.postContent = postContent; }

    public String getPostImage() { return postImage; }
    public void setPostImage(String postImage) { this.postImage = postImage; }

    public CatDto getCategory() { return category; }
    public void setCategory(CatDto category) { this.category = category; }

    public UserDto getUser() { return user; }
    public void setUser(UserDto user) { this.user = user; }

    public OffsetDateTime getPostDate() { return postDate; }
    public void setPostDate(OffsetDateTime postDate) { this.postDate = postDate; }

    public List<CommentDto> getComments() { return comments; }
    public void setComments(List<CommentDto> comments) { this.comments = comments; }

    public Set<UserDto> getLikes() {
        return likes;
    }

    public void setLikes(Set<UserDto> likes) {
        this.likes = likes;
    }
}
