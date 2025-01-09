package com.becoder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.becoder.dto.EmailRequest;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	private String mailFrom;

	public void sendEmail(EmailRequest emailReq) throws Exception {

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom(mailFrom, emailReq.getTitle());
		helper.setTo(emailReq.getTo());
		helper.setSubject(emailReq.getSubject());
		helper.setText(emailReq.getMessage(),true);

		mailSender.send(message);
	}

}
