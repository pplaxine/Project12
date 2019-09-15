package com.biocycle.productDispatchService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

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
import com.biocycle.productDispatchService.dto.ProductRequestBeanDto;
import com.biocycle.productDispatchService.proxy.OfferCRUDMSProxy;
import com.biocycle.productDispatchService.proxy.ProductRequestCRUDMSProxy;
import com.biocycle.productDispatchService.proxy.RedistributionCRUDMSProxy;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PDSRedistributionCRUDIntegrationTests {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	private RedistributionCRUDMSProxy redistributionCRUDMSProxy;
	@MockBean
	private OfferCRUDMSProxy offerCRUDMSProxy;
	@MockBean
	private ProductRequestCRUDMSProxy productRequestCRUDMSProxy;
	
	@Captor
	private ArgumentCaptor<OfferBeanDto> offerBeanDto;
	@Captor
	private ArgumentCaptor<List<ProductRequestBeanDto>> productRequestBeanDtoList;
	
	
	//---- POST 
	@Test
	public void iT01addRedistributionForOffer() throws Exception {
		
		URI location = URI.create("localhost://blop/blop/3");
		//Mock 
		when(offerCRUDMSProxy.addOffer(offerBeanDto.capture())).thenReturn(ResponseEntity.created(location).build());
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.post("/redistributions/offers/2")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(PDSTestHelper.getOfferBeanDtoPostJson())
				).andExpect(status().is2xxSuccessful());
		
		redistributionCRUDMSProxy.deleteRedistribution(5);
	}
	
	//---- POST 
	@Test
	public void iT02addRedistributionForRequest() throws Exception {

		//Stub 
		List<ProductRequestBeanDto> productRequestBeanDtoListResp = new ArrayList<>();
		ProductRequestBeanDto productRequestBeanDto;
		for (int i = 1; i < 4; i++) {
			productRequestBeanDto = new ProductRequestBeanDto();
			productRequestBeanDto.setId(i);
			productRequestBeanDtoListResp.add(productRequestBeanDto);
		}
		
		//Mock 
		when(productRequestCRUDMSProxy.addProductRequestList(productRequestBeanDtoList.capture())).thenReturn(ResponseEntity.ok(productRequestBeanDtoListResp));
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.post("/redistributions/requests/3")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(PDSTestHelper.getProductRequestBeanDtoListPOSTJson())
				).andExpect(status().is2xxSuccessful());
		redistributionCRUDMSProxy.deleteRedistribution(6);
		
	}
}
