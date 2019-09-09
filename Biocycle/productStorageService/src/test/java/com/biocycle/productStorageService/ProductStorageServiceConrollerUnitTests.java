package com.biocycle.productStorageService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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

import com.biocycle.productStorageService.service.ProductStorageManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ProductStorageServiceConrollerUnitTests {

	@Autowired
	MockMvc mockMvc;
	@MockBean
	ProductStorageManager productStorageManager;
	
	@Captor
	private ArgumentCaptor<Integer> captorNumberOfContainer;
	private ObjectWriter objectWriter;
	private List<Integer> storageContainerIdList;
	
	@Before
	public void executeBeforeEach() {
		
		//jSon object writer
		ObjectMapper jsonM = new ObjectMapper();
		jsonM.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		objectWriter = jsonM.writer().withDefaultPrettyPrinter();
		
		storageContainerIdList = new ArrayList<>();
		storageContainerIdList.add(1);
		storageContainerIdList.add(2);
		
	}
	
	@Test
	public void getContainersUT() throws Exception {
		
		String storageContainerIdListJsonFormat = objectWriter.writeValueAsString(storageContainerIdList);
		int numberOfContainer = 2;
		
		//Manager
		when(productStorageManager.getOptimizedStorageContainerSpace(captorNumberOfContainer.capture())).thenReturn(ResponseEntity.ok(storageContainerIdList));
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/productstorage/" + numberOfContainer)	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 200;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
		assertEquals("Unexpected response content",storageContainerIdListJsonFormat.replaceAll("\\s+",""), mvcResult.getResponse().getContentAsString());
		
	}
	

}
