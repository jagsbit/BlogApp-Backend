package com.webdev.blog_app.models;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int postId;

    private String postTitle;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String postContent;

    private OffsetDateTime postDate;

    private String postImage;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Categories category;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comments> comments = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "post_likes",
        joinColumns = @JoinColumn(name = "post_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<Users> likes = new HashSet<>();

    // Constructors
    public Posts() {}

    public Posts(int postId, String postTitle, String postContent, OffsetDateTime postDate, String postImage,
                 Categories category, Users user) {
        this.postId = postId;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.postDate = postDate;
        this.postImage = postImage;
        this.category = category;
        this.user = user;
    }

    // Getters and Setters
    public int getPostId() {
        return postId;
    }
    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }
    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }
    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public OffsetDateTime getPostDate() {
        return postDate;
    }
    public void setPostDate(OffsetDateTime postDate) {
        this.postDate = postDate;
    }

    public String getPostImage() {
        return postImage;
    }
    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public Categories getCategory() {
        return category;
    }
    public void setCategory(Categories category) {
        this.category = category;
    }

    public Users getUser() {
        return user;
    }
    public void setUser(Users user) {
        this.user = user;
    }

    public List<Comments> getComments() {
        return comments;
    }
    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public Set<Users> getLikes() {
        return likes;
    }
    public void setLikes(Set<Users> likes) {
        this.likes = likes;
    }
}
