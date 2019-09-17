package com.biocycle.entWebApp;

import static org.mockito.Mockito.doNothing;
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

import com.biocycle.entWebApp.dto.RedistributionBeanDto;
import com.biocycle.entWebApp.proxy.OfferCRUDMSProxy;
import com.biocycle.entWebApp.proxy.ProductBatchCRUDMSProxy;
import com.biocycle.entWebApp.proxy.RedistributionCRUDMSProxy;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EWAOfferCRUDIntegrationTests {
	
	@Autowired
	MockMvc mockMvc;
	@Autowired
	OfferCRUDMSProxy OfferCRUDMSProxy;
	
	@MockBean
	RedistributionCRUDMSProxy redistributionCRUDMSProxy;
	@MockBean
	ProductBatchCRUDMSProxy productBatchCRUDMSProxy;
	
	@Captor
	private ArgumentCaptor<Integer> redistributionIdCaptor;
	@Captor
	private ArgumentCaptor<RedistributionBeanDto> redistributionBeanDtoCaptor;
	@Captor
	private ArgumentCaptor<Integer> productBatchIdCaptor;
	@Captor
	private ArgumentCaptor<Boolean> statusCaptor;
	
	//---- Tests 
	@WithMockUser(username = "pme",roles = {"PME"})
	@Test
	public void iT01ProductManagmentManagerCancelOffer() throws Exception {
		
		//Mock 
		when(redistributionCRUDMSProxy.findRedistributionById(redistributionIdCaptor.capture())).thenReturn(ResponseEntity.ok(EWATestHelper.getRedistributionBeanDto()));
		doNothing().when(redistributionCRUDMSProxy).updateRedistribution(redistributionBeanDtoCaptor.capture());
		when(productBatchCRUDMSProxy.updateProductBatchIsAwaitingToBeCollectedStatus(productBatchIdCaptor.capture(), statusCaptor.capture())).thenReturn(ResponseEntity.ok().build());
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/pme/redistribution/offer/cancel/1/3")
				).andExpect(status().is3xxRedirection());
		
	}
	
}