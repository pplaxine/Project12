package com.biocycle.customerWebApp;

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

import com.biocycle.customerWebApp.dto.OrganisationBeanDto;
import com.biocycle.customerWebApp.dto.ProductRequestBeanDto;
import com.biocycle.customerWebApp.dto.RedistributionBeanDto;
import com.biocycle.customerWebApp.proxy.OfferCRUDMSProxy;
import com.biocycle.customerWebApp.proxy.ProductBatchCRUDMSProxy;
import com.biocycle.customerWebApp.proxy.ProductRequestCRUDMSProxy;
import com.biocycle.customerWebApp.proxy.RedistributionCRUDMSProxy;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CWARedistributionCRUDServiceIntegrationTests {
	
	@Autowired
	MockMvc mockMvc;
	@Autowired
	RedistributionCRUDMSProxy redistributionCRUDMSProxy;
	
	@MockBean
	ProductRequestCRUDMSProxy productRequestCRUDMSProxy;
	@MockBean
	OfferCRUDMSProxy offerCRUDMSProxy;
	@MockBean
	ProductBatchCRUDMSProxy productBatchCRUDMSProxy;
	
	@Captor
	private ArgumentCaptor<Integer> redistributionBeanDtoIdCaptor;
	@Captor
	private ArgumentCaptor<Integer> offerIdCaptor;
	@Captor
	private ArgumentCaptor<Integer> pbIdCaptor;
	@Captor
	private ArgumentCaptor<ProductRequestBeanDto> productRequestBeanDtoCaptor;
	
	//---- Tests 
	@WithMockUser(username = "orga2@orange.fr",authorities = {"RECEIVER"})
	@Test
	public void iT01RedistributionManageraddOffers() throws Exception {
		
		OrganisationBeanDto orga2 = CWATestHelper.getOrganisationBeanDto(true);
		orga2.setId(2);
		
		//Mock 
		when(offerCRUDMSProxy.findOfferById(offerIdCaptor.capture())).thenReturn(ResponseEntity.ok(CWATestHelper.getOfferBeanDto()));
		when(productBatchCRUDMSProxy.findProductBatchById(pbIdCaptor.capture())).thenReturn(CWATestHelper.getProductBatchBeanDto());
		when(productRequestCRUDMSProxy.findProductRequestById(redistributionBeanDtoIdCaptor.capture())).thenReturn(CWATestHelper.getProductRequestBeanDto());
		doNothing().when(productRequestCRUDMSProxy).updateProductRequest(productRequestBeanDtoCaptor.capture());
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/user2/offers")
								.sessionAttr("organisation", orga2)
			
				).andExpect(status().is2xxSuccessful());
	}

}