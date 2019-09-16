package com.biocycle.customerWebApp;

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

import com.biocycle.customerWebApp.proxy.OfferCRUDMSProxy;
import com.biocycle.customerWebApp.proxy.OrganisationCRUDMSProxy;
import com.biocycle.customerWebApp.proxy.ProductBatchCRUDMSProxy;
import com.biocycle.customerWebApp.proxy.ProductDispatchServiceProxy;
import com.biocycle.customerWebApp.proxy.ProductRequestCRUDMSProxy;
import com.biocycle.customerWebApp.proxy.RedistributionCRUDMSProxy;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CWAOrganisationCRUDIntegrationTests {
	
	@Autowired
	MockMvc mockMvc;
	@Autowired
	OrganisationCRUDMSProxy organisationCRUDMSProxy;
	
	@MockBean
	private ProductDispatchServiceProxy productDispatchServiceProxy;
	@MockBean
	private ProductRequestCRUDMSProxy productRequestCRUDMSProxy;
	@MockBean
	private RedistributionCRUDMSProxy redistributionCRUDMSProxy;
	@MockBean
	private ProductBatchCRUDMSProxy productBatchCRUDMSProxy;
	
	@Captor
	private ArgumentCaptor<Integer> organisationId;
	
	//---- Tests 
	@WithMockUser(username = "orga1@orange.fr",authorities = {"DONOR"})
	@Test
	public void iT01OrganisationManageraddUserInfoToSession() throws Exception {

		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/user/giveaway")	
				).andExpect(status().is2xxSuccessful());
	}
	
	//---- Tests 
	@WithMockUser(username = "orga1@orange.fr",authorities = {"DONOR"})
	@Test
	public void iT02OrganisationManageraddUserInfoToSession() throws Exception {

		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/user/donations")	
				).andExpect(status().is2xxSuccessful());
	}
	
	//---- Tests 
	@WithMockUser(username = "orga2@orange.fr",authorities = {"RECEIVER"})
	@Test
	public void iT03OrganisationManageraddUserInfoToSession() throws Exception {

		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/user2/redistributions/productrequests/form")	
				).andExpect(status().is2xxSuccessful());
	}
	
	//---- Tests 
	@WithMockUser(username = "orga2@orange.fr",authorities = {"RECEIVER"})
	@Test
	public void iT04OrganisationManageraddUserInfoToSession() throws Exception {
		
		//Mock
		when(redistributionCRUDMSProxy.getAllRedistributionByOrganisationId(organisationId.capture())).thenReturn(ResponseEntity.ok(CWATestHelper.getRedistributionBeanDtoList()));
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/user2/offers")	
				).andExpect(status().is2xxSuccessful());
	}
	
}
