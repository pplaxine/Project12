package com.biocycle.productDispatchService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.anything;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.biocycle.productDispatchService.dto.OfferBeanDto;
import com.biocycle.productDispatchService.dto.ProductRequestBeanDto;
import com.biocycle.productDispatchService.service.ProductDispatchManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ProductDispatchServiceControllerUnitTests {

	@Autowired
	MockMvc mockMvc;
	@MockBean
	ProductDispatchManager productDispatchManager;
	
	//object retrieved from body  
	@Captor
	private ArgumentCaptor<List<ProductRequestBeanDto>> captorProductRequestBeanDtoList;			
	@Captor
	private ArgumentCaptor<Integer> captorOrganisationId;											
	@Captor
	private ArgumentCaptor<OfferBeanDto> captorOfferBeanDto; 
	@Captor
	private ArgumentCaptor<Integer> captorRedistributionId;

	private ObjectWriter objectWriter;
	private ProductRequestBeanDto productRequestBeanDto;
	private List<ProductRequestBeanDto> productRequestBeanDtoList;
	private OfferBeanDto offerBeanDto;
	
	@Before
	public void executeBeforeEach() {
		//jSon object writer
		ObjectMapper jsonM = new ObjectMapper();
		jsonM.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		objectWriter = jsonM.writer().withDefaultPrettyPrinter();
		
		//--Stub
		//ProductRequestBeanDto
		productRequestBeanDto = new ProductRequestBeanDto();
		productRequestBeanDto.setId(1);
		
		//OfferBeanDto
		offerBeanDto = new OfferBeanDto();
		offerBeanDto.setId(1);
		
		//ProductRequestBeanDtoList
		productRequestBeanDtoList = new ArrayList<>();
		productRequestBeanDtoList.add(productRequestBeanDto);
		productRequestBeanDto.setId(2);
		productRequestBeanDtoList.add(productRequestBeanDto);
	}
	
	@Test
	public void addRedistributionForRequestUT() throws Exception {
		
		String productRequestDtoListJsonFormat = objectWriter.writeValueAsString(productRequestBeanDtoList);
		int organisationId = 1;

		//Manager 
		when(productDispatchManager.createRedistributionForRequest( captorOrganisationId.capture(), captorProductRequestBeanDtoList.capture())).thenReturn(ResponseEntity.created(null).build());
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.post("/redistributions/requests/" + organisationId)	
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(productRequestDtoListJsonFormat)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 201;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void addRedistributionForOfferUT() throws Exception {
		
		String offerBeanDtoJsonFormat = objectWriter.writeValueAsString(offerBeanDto);
		int organisationId = 1;

		//Manager 
		when(productDispatchManager.createRedistributionForOffer(captorOrganisationId.capture(), captorOfferBeanDto.capture())).thenReturn(ResponseEntity.created(null).build());
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.post("/redistributions/offers/" + organisationId)	//
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(offerBeanDtoJsonFormat)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 201;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void updateRedistributionWithofferUT() throws Exception {
		
		String offerBeanDtoJsonFormat = objectWriter.writeValueAsString(offerBeanDto);
		int redistributionId = 1;

		//Manager 
		when(productDispatchManager.addOfferToRedistribution(captorRedistributionId.capture(), captorOfferBeanDto.capture())).thenReturn(ResponseEntity.ok().build());
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.put("/redistributions/offers/add/" + redistributionId)	//
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(offerBeanDtoJsonFormat)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 200;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	

}
