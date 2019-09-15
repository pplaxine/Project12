package com.biocycle.productDispatchService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.biocycle.productDispatchService.dto.RedistributionBeanDto;
import com.biocycle.productDispatchService.proxy.ProductRequestCRUDMSProxy;
import com.biocycle.productDispatchService.proxy.RedistributionCRUDMSProxy;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PDSProductRequestCRUDIntegrationTests {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	private ProductRequestCRUDMSProxy productRequestCRUDMSProxy;
	
	@MockBean
	private RedistributionCRUDMSProxy redistributionCRUDMSProxy;
	
	@Captor
	private ArgumentCaptor<RedistributionBeanDto> redistributionBeanDto;
	@Captor
	private ArgumentCaptor<List<Integer>> productRequestIdList;
	
	//---- POST 
	@Test
	public void iT01addRedistributionForRequest() throws Exception {

		List<Integer> requestToRemoveIdList  = new ArrayList<>();
		requestToRemoveIdList.add(5);
		requestToRemoveIdList.add(6);
		
		//Mock 
		when(redistributionCRUDMSProxy.addRedistribution(redistributionBeanDto.capture())).thenReturn(ResponseEntity.created(null).build());
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.post("/redistributions/requests/3")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(PDSTestHelper.getProductRequestBeanDtoListPOSTJson())
				).andExpect(status().is2xxSuccessful());
		
		Integer[]requestToRemoveIdArray = requestToRemoveIdList.toArray(new Integer[requestToRemoveIdList.size()]);
		
		productRequestCRUDMSProxy.deleteProductRequestList(requestToRemoveIdArray);
		
	}
}
