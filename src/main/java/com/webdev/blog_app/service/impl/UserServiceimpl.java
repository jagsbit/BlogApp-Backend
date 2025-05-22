package com.webdev.blog_app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

 
import com.webdev.blog_app.execptions.ResourceNotFoundException;
import com.webdev.blog_app.models.Users;
import com.webdev.blog_app.payloads.LoginDto;
import com.webdev.blog_app.payloads.LoginResponse;
import com.webdev.blog_app.payloads.UserDto;
import com.webdev.blog_app.repository.UserRepo;
import com.webdev.blog_app.service.UserService;

@Service
public class UserServiceimpl implements UserService {
	
	// UserRepo userRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JWTService jwtService;
	
	private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		List<Users> users=userRepo.findAll();
		List<UserDto> newDto=new ArrayList<>();
		for (Users user : users) {
			UserDto userDto = UserToUserDto(user);
			newDto.add(userDto);
		}
		return newDto;
	}

	@Override
	public UserDto getUserById(int userId) {
		// TODO Auto-generated method stub
		
		Users user=userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		 
		UserDto userDto=UserToUserDto(user);
		return userDto;
		 
	}

	@Override
	public UserDto createUser(UserDto user) {
		Users user1 = UserDtoToUser(user);
		user1.setPassword(encoder.encode(user1.getPassword()));
		Users newUser = userRepo.save(user1);
		UserDto userDto = UserToUserDto(newUser);
		return userDto;
	}

	@Override
	public UserDto updateUser(UserDto user, int userId) {
		Users existingUser = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		if (existingUser == null) {
			throw new ResourceNotFoundException("User", "userId", userId);
		}
		existingUser.setName(user.getName());
		existingUser.setEmail(user.getEmail());
		existingUser.setPassword(user.getPassword());
		existingUser.setAbout(user.getAbout());

		Users updatedUser = userRepo.save(existingUser);
		UserDto userDto = UserToUserDto(updatedUser);
		return userDto;
	}

	@Override
	public String deleteUser(int userId) {
		// TODO Auto-generated method stub
		Users user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		if (user == null) {
			throw new ResourceNotFoundException("User", "userId", userId);
		}
		userRepo.delete(user);
		return "User deleted successfully";
		
		
	}
	public LoginResponse login(LoginDto user) {
		// TODO Auto-generated method stub
		  Users users=modelMapper.map(user, Users.class);
		 Authentication auth=authManager.authenticate(new UsernamePasswordAuthenticationToken(users.getEmail(),users.getPassword()));
			if (auth.isAuthenticated()) {
				Users loggedInuser=userRepo.findByEmail(user.getEmail());
				return  new LoginResponse(jwtService.generateToken(users.getEmail()),modelMapper.map(loggedInuser, UserDto.class));
			}
			return  new LoginResponse("Invalid credentials");
		 
	}
	public UserDto UserToUserDto(Users user) {
        UserDto userDto =modelMapper.map(user, UserDto.class);
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        return userDto;
    }

	public Users UserDtoToUser(UserDto userDto) {
        Users user =modelMapper.map(userDto, Users.class);
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
        return user;
	}

}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	