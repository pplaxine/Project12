package com.biocycle.customerWebApp;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.biocycle.customerWebApp.dto.OrganisationBeanDto;
import com.biocycle.customerWebApp.proxy.CustomerManagmentServiceProxy;
import com.biocycle.customerWebApp.proxy.OrganisationCRUDMSProxy;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CWACustomerManagmentServiceIntegrationTests {
	
	@Autowired
	MockMvc mockMvc;
	@Autowired
	CustomerManagmentServiceProxy customerManagmentServiceProxy;
	@Autowired
	OrganisationCRUDMSProxy organisationCRUDMSProxy;
	
	//---- Tests 
	@Test
	public void iT01OrganisationManagerSaveRequest() throws Exception {
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/partnership/request/save")	
								.flashAttr("organisationBeanDto", CWATestHelper.getOrganisationBeanDto(false))
				).andExpect(status().is3xxRedirection());
	}
	
	@Test
	public void iT02OrganisationManagersavePassword() throws Exception {
		//validated status 
		String email = CWATestHelper.getOrganisationBeanDto(false).getEmailAddress();
		OrganisationBeanDto validatedOrganisation = organisationCRUDMSProxy.findOrganisationByEmail(email).getBody();
		validatedOrganisation.setIsValidated(true);
		organisationCRUDMSProxy.updateOrganisation(validatedOrganisation);
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/authentification/password/save")	
								.flashAttr("passwordCreationViewDto", CWATestHelper.getPasswordCreationViewDto())
				).andExpect(status().is3xxRedirection());
	}
	
}
