package com.webdev.blog_app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webdev.blog_app.payloads.LoginDto;
import com.webdev.blog_app.payloads.LoginResponse;
import com.webdev.blog_app.payloads.UserDto;
import com.webdev.blog_app.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
 
@RequestMapping("/api/users")
public class UserController {
    // UserService userService;
	
	@Autowired
	private UserService userService;
	
	
	// get all users
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> users = userService.getAllUsers();
		return new ResponseEntity<List<UserDto>>(users, HttpStatus.OK);
	}
	
	// create user
	@PostMapping("/register")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user) {
		UserDto createdUser = userService.createUser(user);
		return new ResponseEntity<UserDto>(createdUser, HttpStatus.CREATED);
	}
	
	// login user
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginDto user) {
		LoginResponse token = userService.login(user);
		return new ResponseEntity<LoginResponse>(token, HttpStatus.OK);
	}
	
	//get user by id
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable int userId) {
		UserDto user = userService.getUserById(userId);
		return new ResponseEntity<UserDto>(user, HttpStatus.OK);
	}
	
	// update user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto user, @PathVariable int userId) {
		UserDto updatedUser = userService.updateUser(user, userId);
		return new ResponseEntity<UserDto>(updatedUser, HttpStatus.OK);
	}
	
	// delete user
	 @DeleteMapping("/{userId}")
	 public ResponseEntity<String> deleteUser(@PathVariable int userId) {
			String response = userService.deleteUser(userId);
			return new ResponseEntity<String>(response, HttpStatus.OK);
	  }
}
