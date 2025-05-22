package com.webdev.blog_app.payloads;

public class LoginResponse {
    private String token;
    private UserDto user;

    public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public LoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

	public LoginResponse(String token, UserDto user) {
		super();
		this.token = token;
		this.user = user;
	}
}


