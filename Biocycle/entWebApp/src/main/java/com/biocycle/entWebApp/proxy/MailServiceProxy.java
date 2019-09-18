package com.biocycle.entWebApp.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * The Interface MailServiceProxy.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@FeignClient(name = "zuul-server", contextId = "mailservice")
@RibbonClient(name = "mailService")
public interface MailServiceProxy {
	
	/**
	 * Send partnership accepted email.
	 *
	 * @param organisationName the organisation name
	 * @param emailAddress the email address
	 * @return the response entity
	 */
	@PostMapping(value = "/mailservice/email/partnership/valid/{organisationName}/{emailAddress}")
	ResponseEntity<Void> sendPartnershipAcceptedEmail(@PathVariable("organisationName") String organisationName, @PathVariable("emailAddress") String emailAddress);
}
