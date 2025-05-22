package com.webdev.blog_app.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDto {
  
	private int id;

	@Size(min = 4, message = "Username must be of minimum 4 characters")
	private String name;

	@Email(message = "Email address is not valid")
	private String email;

	@Pattern(regexp = "^[a-zA-Z0-9]{8,}", message = "Password must be of minimum 8 characters")
	private String password;

	@Size(min = 5, message = "About must be of minimum 5 characters")
	private String about;

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

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public UserDto() {
		super();
	}

	public UserDto(int id, String name, String email, String password, String about) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
	}
}
