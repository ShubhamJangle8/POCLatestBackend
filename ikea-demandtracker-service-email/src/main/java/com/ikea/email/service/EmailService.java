package com.ikea.email.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.ikea.email.model.EmailRequest;

@Service
public class EmailService {

	public void sendEmail(EmailRequest emailRequest) throws MessagingException {

		Properties properties = System.getProperties();

		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "outlook.office365.com");
		properties.put("mail.smtp.port", "587");

		System.out.println("stting prop");

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				System.out.println("auth");
				return new PasswordAuthentication("ubakara-anthony.francis-xavier@capgemini.com", "May@0315");
			}

		});

		session.setDebug(true);

		System.out.println("mim");

		Message mime_message = new MimeMessage(session);
		System.out.println("try");

		try {

			mime_message.setFrom(new InternetAddress(emailRequest.getFrom()));

			mime_message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailRequest.getTo()));

			mime_message.setSubject(emailRequest.getSubject());

			mime_message.setContent(emailRequest.getMessage(), "text/html");

			Transport.send(mime_message);

			System.out.println("done");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
