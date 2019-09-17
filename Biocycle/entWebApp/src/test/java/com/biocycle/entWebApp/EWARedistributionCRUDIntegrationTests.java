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

import com.biocycle.entWebApp.dto.OfferBeanDto;
import com.biocycle.entWebApp.dto.OrganisationBeanDto;
import com.biocycle.entWebApp.dto.ProductRequestBeanDto;
import com.biocycle.entWebApp.proxy.OfferCRUDMSProxy;
import com.biocycle.entWebApp.proxy.OrganisationCRUDMSProxy;
import com.biocycle.entWebApp.proxy.ProductBatchCRUDMSProxy;
import com.biocycle.entWebApp.proxy.ProductRequestCRUDMSProxy;
import com.biocycle.entWebApp.proxy.RedistributionCRUDMSProxy;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EWARedistributionCRUDIntegrationTests {
	
	@Autowired
	MockMvc mockMvc;
	@Autowired
	RedistributionCRUDMSProxy redistributionCRUDMSProxy;
	
	@MockBean
	OrganisationCRUDMSProxy organisationCRUDMSProxy;
	@MockBean
	OfferCRUDMSProxy offerCRUDMSProxy;
	@MockBean
	ProductBatchCRUDMSProxy productBatchCRUDMSProxy;
	@MockBean
	ProductRequestCRUDMSProxy productRequestCRUDMSProxy;
	
	@Captor
	private ArgumentCaptor<Integer> organisationIdCaptor;
	@Captor
	private ArgumentCaptor<Integer> redistributionBeanDtoIdCaptor;
	@Captor
	private ArgumentCaptor<Integer> productBatchIdCaptor;
	@Captor
	private ArgumentCaptor<Integer> productRequestIdCaptor;
	@Captor
	private ArgumentCaptor<Integer> offerIdCaptor;
	@Captor
	private ArgumentCaptor<Boolean> statusCaptor;
	@Captor
	private ArgumentCaptor<OfferBeanDto> offerBeanDtoCaptor;
	
	
	//---- Tests 
	@WithMockUser(username = "pme",roles = {"PME"})
	@Test
	public void iT01ProductManagmentManagerRedistribution() throws Exception {
		
		OrganisationBeanDto orga2 = EWATestHelper.getOrganisationBeanDto(true);
		orga2.setId(2);
		
		//Mock 
		when(organisationCRUDMSProxy.findOrganisationById(organisationIdCaptor.capture())).thenReturn(ResponseEntity.ok(orga2));
		when(offerCRUDMSProxy.findOfferById(redistributionBeanDtoIdCaptor.capture())).thenReturn(ResponseEntity.ok(EWATestHelper.getOfferBeanDto()));
		when(productBatchCRUDMSProxy.findProductBatchById(productBatchIdCaptor.capture())).thenReturn(ResponseEntity.ok(EWATestHelper.getProductBatchBeanDto()));
		when(productRequestCRUDMSProxy.findProductRequestById(productRequestIdCaptor.capture())).thenReturn(EWATestHelper.getProductRequestBeanDto());
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/pme/redistribution")
				).andExpect(status().is2xxSuccessful());
	}
	
	@WithMockUser(username = "pme",roles = {"PME"})
	@Test
	public void iT02ProductManagmentManagerRedistributionSolo() throws Exception {
		
		OrganisationBeanDto orga2 = EWATestHelper.getOrganisationBeanDto(true);
		orga2.setId(2);
		
		//Mock 
		when(organisationCRUDMSProxy.findOrganisationById(organisationIdCaptor.capture())).thenReturn(ResponseEntity.ok(orga2));
		when(offerCRUDMSProxy.findOfferById(redistributionBeanDtoIdCaptor.capture())).thenReturn(ResponseEntity.ok(EWATestHelper.getOfferBeanDto()));
		when(productBatchCRUDMSProxy.findProductBatchById(productBatchIdCaptor.capture())).thenReturn(ResponseEntity.ok(EWATestHelper.getProductBatchBeanDto()));
		when(productRequestCRUDMSProxy.findProductRequestById(productRequestIdCaptor.capture())).thenReturn(EWATestHelper.getProductRequestBeanDto());
		when(offerCRUDMSProxy.updateOffer(offerBeanDtoCaptor.capture())).thenReturn(ResponseEntity.ok().build());
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/pme/redistribution/1")
								.sessionAttr("productBatchBeanDtoForOfferList", EWATestHelper.getProductBatchBeanDtoList())
				).andExpect(status().is2xxSuccessful());
	}
	
	@WithMockUser(username = "pme",roles = {"PME"})
	@Test
	public void iT03ProductManagmentManagerCancelOffer() throws Exception {
		
		//Mock 
		when(productBatchCRUDMSProxy.updateProductBatchIsAwaitingToBeCollectedStatus(productBatchIdCaptor.capture(), statusCaptor.capture())).thenReturn(ResponseEntity.ok().build());
		when(offerCRUDMSProxy.findOfferById(offerIdCaptor.capture())).thenReturn(ResponseEntity.ok(EWATestHelper.getOfferBeanDto()));
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/pme/redistribution/offer/cancel/1/3")
				).andExpect(status().is3xxRedirection());
		
	}

}