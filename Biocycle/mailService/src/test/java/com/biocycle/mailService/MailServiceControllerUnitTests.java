package com.biocycle.mailService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.biocycle.mailService.service.MailServiceManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@RunWith(SpringRunner.class)
@WebMvcTest
public class MailServiceControllerUnitTests {

	@Autowired
	MockMvc mockMvc;
	@MockBean
	private MailServiceManager mailServiceManager;
	
	private ObjectWriter objectWriter;
	
	@Before
	public void executeBeforeEach() {
		
		//jSon object writer
		ObjectMapper jsonM = new ObjectMapper();
		jsonM.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		objectWriter = jsonM.writer().withDefaultPrettyPrinter();
		
	}
	
	@Test
	public void sendEmailUT() throws Exception {
		
		String organisationName = "OrgaTest";
		String emailAddress = "orgaTest@orange.fr";
		
		//Manager
		when(mailServiceManager.createAndSendEmailForPartnershipValidation(organisationName, emailAddress)).thenReturn(ResponseEntity.ok().build());
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.post("/email/partnership/valid/"+ organisationName + "/" + emailAddress)	
								.contentType(MediaType.APPLICATION_JSON_UTF8)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 200;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}

}
