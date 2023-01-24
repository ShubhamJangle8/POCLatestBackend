package com.ikea.email.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ikea.email.model.EmailRequest;
import com.ikea.email.service.EmailService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/email")
public class EmailController {

	@Autowired
	private EmailService emailService;

	@PostMapping("/sendEmail")
	public void sendCreateMail(@RequestBody EmailRequest emailRequest) throws MessagingException {
		emailService.sendEmail(emailRequest);
	}
}
