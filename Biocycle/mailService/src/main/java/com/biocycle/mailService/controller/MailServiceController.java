package com.biocycle.mailService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biocycle.mailService.service.MailServiceManager;

/**
 * The Class MailServiceController.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@RestController
public class MailServiceController {
	
	/** The mail service manager. */
	@Autowired
	private MailServiceManager mailServiceManager;
	
	/**
	 * Send email.
	 *
	 * @param organisationName the organisation name
	 * @param emailAddress the email address
	 * @return the response entity
	 */
	@PostMapping(value = "/email/partnership/valid/{organisationName}/{emailAddress}")
	public ResponseEntity<Void> sendEmail(@PathVariable String organisationName, @PathVariable String emailAddress){
		return mailServiceManager.createAndSendEmailForPartnershipValidation(organisationName, emailAddress);
	}
}
