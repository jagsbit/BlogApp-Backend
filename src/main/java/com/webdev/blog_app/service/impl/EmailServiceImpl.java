package com.webdev.blog_app.service.impl;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl {
	
	@Autowired
    private JavaMailSender mailSender;

    public String sendSimpleEmail(String to, String subject, String body) throws UnsupportedEncodingException {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            
            helper.setFrom(new InternetAddress("techtalesblogsapp@gmail.com", "Tech Tales Blog"));
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true); // true enables HTML

            mailSender.send(message);
            return "Email sent successfully!";
        } catch (MessagingException e) {
            return "Failed to send email: " + e.getMessage();
        }
    }

}
