package com.webdev.blog_app.service;

import java.util.List;

import com.webdev.blog_app.payloads.LoginDto;
import com.webdev.blog_app.payloads.LoginResponse;
import com.webdev.blog_app.payloads.UserDto;

public interface UserService {
	
	List<UserDto> getAllUsers();
	UserDto getUserById(int userId);
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, int userId);
	String deleteUser(int userId);
    LoginResponse login(LoginDto user);
	
}
