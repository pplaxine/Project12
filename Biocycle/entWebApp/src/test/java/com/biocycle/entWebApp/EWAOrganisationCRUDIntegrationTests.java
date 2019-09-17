package com.biocycle.entWebApp;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.biocycle.entWebApp.proxy.GiveAwayCRUDMSProxy;
import com.biocycle.entWebApp.proxy.OrganisationCRUDMSProxy;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EWAOrganisationCRUDIntegrationTests {
	
	@Autowired
	MockMvc mockMvc;
	@Autowired
	OrganisationCRUDMSProxy organisationCRUDMSProxy;
	
	@MockBean
	GiveAwayCRUDMSProxy giveAwayCRUDMSProxy;
	
	//---- Tests 
	@WithMockUser(username = "cse",roles = {"CSE"})
	@Test
	public void iT01CollectionManagmentManagerDonationOfferList() throws Exception {
		
		//Mock 
		when(giveAwayCRUDMSProxy.findActiveGiveAway()).thenReturn(ResponseEntity.ok(EWATestHelper.getGiveAwayBeanDtoList()));
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/cse/donation/offers")
				).andExpect(status().is2xxSuccessful());
		
	}
	
	@WithMockUser(username = "cme",roles = {"CME"})
	@Test
	public void iT02CustomerManagmentManagerPartnershipRequests() throws Exception {
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/cme/partnerships/requests")
				).andExpect(status().is2xxSuccessful());
	}
	
	@WithMockUser(username = "cme",roles = {"CME"})
	@Test
	public void iT03CustomerManagmentManagerValidatePartnership() throws Exception {
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/cme/partnerships/requests/validate/1")
				).andExpect(status().is3xxRedirection());
	}
	
	
	
}