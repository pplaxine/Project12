package com.biocycle.customerManagmentService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.biocycle.customerManagmentService.dto.OrganisationBeanDto;
import com.biocycle.customerManagmentService.proxy.OrganisationCRUDMSProxy;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CMSOrganisationCRUDMSProxyIntegrationTests {
	
	@Autowired
	MockMvc mockMvc;
	@Autowired
	OrganisationCRUDMSProxy organisationCRUDMSProxy; 
	
	//---- GET
	@Test
	public void iT01findAllOrganisation() throws Exception {
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/organisations")	
								.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());

	}
	
	
	//---- POST 
	@Test
	public void iT02addOrganisation() throws Exception {
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.post("/organisations")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(CMSTestHelper.getOrganisationBeanDtoPOSTJson())
				).andExpect(status().is2xxSuccessful());
	}
	
	//---- POST 
	@Test
	public void iT03addPassword() throws Exception {
		
		//creates Organisation validation necessary before creating password 
		OrganisationBeanDto organisationBeanDto = organisationCRUDMSProxy.findOrganisationByEmail("orga3@orange.fr").getBody();
		organisationBeanDto.setIsValidated(true);
		organisationCRUDMSProxy.updateOrganisation(organisationBeanDto);
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.post("/organisations/password")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(CMSTestHelper.getOrganisationBeanDtoPasswordPOSTJson())
				).andExpect(status().is2xxSuccessful());
		
		//Last test delete testing entry 
		organisationCRUDMSProxy.deleteOrganisation(organisationBeanDto.getId());
	}
	

	
	
	
}
