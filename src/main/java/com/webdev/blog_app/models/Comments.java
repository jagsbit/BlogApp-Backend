package com.webdev.blog_app.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity 
public class Comments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String content;
	@ManyToOne
	private Posts post;
	@ManyToOne
	private Users user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Posts getPost() {
		return post;
	}

	public void setPost(Posts post) {
		this.post = post;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Comments(int id, String content, Posts post, Users user) {
		super();
		this.id = id;
		this.content = content;
		this.post = post;
		this.user = user;
	}

	public Comments() {
		super();
		// TODO Auto-generated constructor stub
	}
}
