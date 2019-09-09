package com.biocycle.mailService.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailServiceManager {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	Environment env;
	
	public ResponseEntity<Void> createAndSendEmailForPartnershipValidation(String organisationName, String emailAddress){
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, false, "utf-8");
			String email = composeEmail(organisationName);
			
			mimeMessage.setContent(email, "text/html");
			mmh.setTo(emailAddress);
			mmh.setSubject("Biocycle Partnership");
			javaMailSender.send(mimeMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().build();
	}
	
	//UTILITY METHOD 
	protected String composeEmail(String organisationName) {
		StringBuilder sb = new StringBuilder();
		
		try(BufferedReader br = Files.newBufferedReader(Paths.get(env.getProperty("mail.template.partnership.accepted.path")))){
			br.lines().forEach(e-> sb.append(e));
			replaceAll(sb, "$$userName", organisationName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * Replace all the occurrences of a String by another String.
	 * 
	 * @param builder {@link StringBuilder}
	 * @param from the String to replace 
	 * @param to the String to replace with 
	 */
	protected void replaceAll(StringBuilder builder, String from, String to)
	{
	    int index = builder.indexOf(from);
	    while (index != -1)
	    {
	        builder.replace(index, index + from.length(), to);
	        index += to.length(); // Move to the end of the replacement
	        index = builder.indexOf(from, index);
	    }
	}
}
