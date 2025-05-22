package com.webdev.blog_app.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Users {
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	private String name;
	@Column(unique = true)
	private String email;
	private String password;
	private String about;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Posts> posts=new ArrayList<Posts>();
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Users(int id, String name, String email, String password, String about) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
	}
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Comments> comments=new ArrayList<Comments>();
	public List<Posts> getPosts() {
		return posts;
	}
	public void setPosts(List<Posts> posts) {
		this.posts = posts;
	}
	public List<Comments> getComments() {
		return comments;
	}
	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        Users user = (Users) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
	
	
	
}
