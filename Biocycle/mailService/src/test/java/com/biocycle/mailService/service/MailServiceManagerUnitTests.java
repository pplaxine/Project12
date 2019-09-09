package com.biocycle.mailService.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MailServiceManager.class})
public class MailServiceManagerUnitTests {

	@Autowired
	private MailServiceManager organisationBeanManager;
	@MockBean
	private JavaMailSender javaMailSender;
	@MockBean
	private Environment env;

	@Test
	public void composeEmailUT() {
		
		String organisationName = "Orga1";
		
		when(env.getProperty("mail.template.partnership.accepted.path")).thenReturn("src/main/resources/static/emailTemplate/emailPartnershipAcceptedTemplate.html");
		
		String email = organisationBeanManager.composeEmail(organisationName);
		
		assertNotNull("The email has not been composed", email);
		
	}
	
	@Test
	public void replaceAllUT() {
		StringBuilder sb = new StringBuilder();
		sb.append("I love blop");
		String strExpected = ("I love foo");
		
		organisationBeanManager.replaceAll(sb, "blop", "foo");
		
		assertEquals("Text has not been reset as expected",strExpected, sb.toString());
	}
	
}























