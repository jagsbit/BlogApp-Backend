package com.webdev.blog_app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.webdev.blog_app.models.UserPrincipal;
import com.webdev.blog_app.models.Users;
import com.webdev.blog_app.repository.UserRepo;

 

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Users user=userRepository.findByEmail(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("User Not found");
		}
		
		return new UserPrincipal(user);
		
		 
	}

}
