package com.biocycle.entWebApp;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
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
public class EWAGiveAwayCRUDIntegrationTests {
	
	@Autowired
	MockMvc mockMvc;
	@Autowired
	GiveAwayCRUDMSProxy giveAwayCRUDMSProxy;
	
	@MockBean
	OrganisationCRUDMSProxy organisationCRUDMSProxy;
	@Captor
	private ArgumentCaptor<Integer> organisationIdCaptor;
	
	
	//---- Tests 
	@WithMockUser(username = "cse",roles = {"CSE"})
	@Test
	public void iT01CollectionManagmentManagerDonationOfferList() throws Exception {
		
		//Mock 
		when( organisationCRUDMSProxy.findOrganisationById(organisationIdCaptor.capture())).thenReturn(ResponseEntity.ok(EWATestHelper.getOrganisationBeanDto(true)));
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/cse/donation/offers")
				).andExpect(status().is2xxSuccessful());
		
	}
	
	@WithMockUser(username = "cse",roles = {"CSE"})
	@Test
	public void iT02CollectionManagmentManagerUpdateDonation() throws Exception {
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/cse/donation/offers/1/1/true")
				).andExpect(status().is3xxRedirection());
		
	}

}