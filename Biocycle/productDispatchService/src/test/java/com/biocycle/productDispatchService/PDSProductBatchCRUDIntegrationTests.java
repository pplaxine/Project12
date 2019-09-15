package com.biocycle.productDispatchService;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.biocycle.productDispatchService.dto.OfferBeanDto;
import com.biocycle.productDispatchService.dto.RedistributionBeanDto;
import com.biocycle.productDispatchService.proxy.OfferCRUDMSProxy;
import com.biocycle.productDispatchService.proxy.ProductBatchCRUDMSProxy;
import com.biocycle.productDispatchService.proxy.RedistributionCRUDMSProxy;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PDSProductBatchCRUDIntegrationTests {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ProductBatchCRUDMSProxy productBatchCRUDMSProxy;
	
	@MockBean
	RedistributionCRUDMSProxy redistributionCRUDMSProxy;
	@MockBean
	OfferCRUDMSProxy offerCRUDMSProxy;


	@Captor
	private ArgumentCaptor<Integer> redistributionId;
	@Captor
	private ArgumentCaptor<Integer> pbd;
	@Captor
	private ArgumentCaptor<Boolean> status;
	@Captor
	private ArgumentCaptor<RedistributionBeanDto> redistributionBeanDtoFromDB;
	@Captor
	private ArgumentCaptor<RedistributionBeanDto> redistributionBeanDtoCaptor;
	@Captor
	private ArgumentCaptor<OfferBeanDto> offerBeanDto;
	
	//---- PUT
	@Test
	public void iT01addOfferToRedistribution() throws Exception {
		
		//STUB 
		RedistributionBeanDto redistributionBeanDto = new RedistributionBeanDto();
		redistributionBeanDto.setId(1);
		
		URI location = URI.create("localhost://blop/blop/5");
		
		when(redistributionCRUDMSProxy.getRedistributionById(redistributionId.capture())).thenReturn(ResponseEntity.ok(redistributionBeanDto));
		when(offerCRUDMSProxy.addOffer(offerBeanDto.capture())).thenReturn(ResponseEntity.created(location).build());
		when(redistributionCRUDMSProxy.getRedistributionById(redistributionId.capture())).thenReturn(ResponseEntity.ok(redistributionBeanDto));
		doNothing().when(redistributionCRUDMSProxy).updateRedistribution(redistributionBeanDtoFromDB.capture());
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.put("/redistributions/offers/add/1")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(PDSTestHelper.getOfferBeanDtoPostJsonWithoutId())
				).andExpect(status().is2xxSuccessful());
		productBatchCRUDMSProxy.updateProductBatchIsAwaitingToBeCollectedStatus(5, false);
	}
	

	
	
}
