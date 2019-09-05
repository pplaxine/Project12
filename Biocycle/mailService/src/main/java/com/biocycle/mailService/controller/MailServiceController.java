package com.biocycle.mailService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biocycle.mailService.service.MailServiceManager;

@RestController
public class MailServiceController {
	
	@Autowired
	private MailServiceManager mailServiceManager;
	
	@PostMapping(value = "/email/partnership/valid/{organisationName}/{emailAddress}")
	public ResponseEntity<Void> sendEmail(@PathVariable String organisationName, @PathVariable String emailAddress){
		return mailServiceManager.createAndSendEmailForPartnershipValidation(organisationName, emailAddress);
	}
}
