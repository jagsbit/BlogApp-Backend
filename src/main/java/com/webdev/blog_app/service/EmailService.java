package com.webdev.blog_app.service;

public interface EmailService {
	public String sendSimpleEmail(String to, String subject, String body);
}
