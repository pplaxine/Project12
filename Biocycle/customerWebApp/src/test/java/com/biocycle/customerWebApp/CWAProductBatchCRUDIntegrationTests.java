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

import com.biocycle.customerWebApp.dto.OfferBeanDto;
import com.biocycle.customerWebApp.proxy.OfferCRUDMSProxy;
import com.biocycle.customerWebApp.proxy.ProductBatchCRUDMSProxy;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CWAProductBatchCRUDIntegrationTests {
	
	@Autowired
	MockMvc mockMvc;
	@Autowired
	ProductBatchCRUDMSProxy productBatchCRUDMSProxy;
	@MockBean
	OfferCRUDMSProxy offerCRUDMSProxy;
	
	@Captor
	private ArgumentCaptor<OfferBeanDto> offerBeanDtoCaptor;
	
	
	//---- Tests 
	@WithMockUser(username = "orga1@orange.fr",authorities = {"DONOR"})
	@Test
	public void iT01RedistributionManagerProductSoonToBeExpired() throws Exception {
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/")	
				).andExpect(status().is2xxSuccessful());
	}
	
	@WithMockUser(username = "orga2@orange.fr",authorities = {"RECEIVER"})
	@Test
	public void iT02RedistributionManagerUpdateOfferStatus() throws Exception {
		
		when(offerCRUDMSProxy.updateOffer(offerBeanDtoCaptor.capture())).thenReturn(ResponseEntity.ok().build());
		when(offerCRUDMSProxy.findOfferById(1)).thenReturn(ResponseEntity.ok(CWATestHelper.getOfferBeanDto()));
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/user2/offers/accept/1")	
				).andExpect(status().is3xxRedirection());
	}
}