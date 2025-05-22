package com.webdev.blog_app.controllers;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webdev.blog_app.payloads.EmailRequest;
import com.webdev.blog_app.service.impl.EmailServiceImpl;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/email")
public class EmailController {
	
	@Autowired
	private EmailServiceImpl emailService;
	
	@PostMapping("/send")
	public String sendEmail(@RequestBody EmailRequest request) throws UnsupportedEncodingException {
        return emailService.sendSimpleEmail(request.getTo(), request.getSubject(), request.getBody());
    }
	

}
