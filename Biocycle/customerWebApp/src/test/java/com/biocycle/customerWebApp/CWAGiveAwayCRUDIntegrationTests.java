package com.biocycle.customerWebApp;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.biocycle.customerWebApp.proxy.GiveAwayCRUDMSProxy;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CWAGiveAwayCRUDIntegrationTests {
	
	@Autowired
	MockMvc mockMvc;
	@Autowired
	GiveAwayCRUDMSProxy giveAwayCRUDMSProxy;
	
	
	//---- Tests 
	@WithMockUser(username = "orga1@orange.fr",authorities = {"DONOR"})
	@Test
	public void iT01GiveawayManagerCreateGiveAway() throws Exception {
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/user/donations")	
								.sessionAttr("organisation", CWATestHelper.getOrganisationBeanDto(true))
				).andExpect(status().is2xxSuccessful());
	}
	
	
	@WithMockUser(username = "orga1@orange.fr",authorities = {"DONOR"})
	@Test
	public void iT02GiveawayManagergGiveAway() throws Exception {
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/user/giveaway/create")	
								.flashAttr("giveAwayViewDto", CWATestHelper.getGiveAwayViewDto())
								.sessionAttr("containerViewDtoMap", CWATestHelper.getContainerViewDtoMap())
								.sessionAttr("organisation", CWATestHelper.getOrganisationBeanDto(true))
				).andExpect(status().is3xxRedirection());
		giveAwayCRUDMSProxy.deleteGiveAway(4);
	}
	

	
	
	

	
}
