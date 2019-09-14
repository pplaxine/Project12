package com.biocycle.productRequestCRUD;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductRequestCrudApplicationIntegretionTests {
	
	@Autowired
	MockMvc mockMvc;
	
	//---- GET
	@Test
	public void iT01findProductRequestById() throws Exception {
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/productrequests/1")	
								.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
	}
	
	//---- POST 
	@Test
	public void iT02addProductRequest() throws Exception {
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.post("/productrequests")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(ProductRequestTestHelper.getProductRequestDtoPOSTJson())
				).andExpect(status().is2xxSuccessful());
	}
	
	@Test
	public void iT03addProductRequestList() throws Exception {
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.post("/productrequests/list")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(ProductRequestTestHelper.getProductRequestDtoListPostJson())
				).andExpect(status().is2xxSuccessful());
	}
	
	//---- PUT 
	@Test
	public void iT04updateProductRequest() throws Exception {
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.put("/productrequests")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(ProductRequestTestHelper.getProductRequestDtoPUTJSon())
				).andExpect(status().is2xxSuccessful());
	}
	
	//---- DELETE 
	@Test
	public void iT05deleteProductRequest() throws Exception {
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.delete("/productrequests/4")
				).andExpect(status().is2xxSuccessful());
	}
	
	@Test
	public void iT06deleteProductRequestList() throws Exception {
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.delete("/productrequests/list")
								.param("productRequestIdList", "1,2")
				).andExpect(status().is2xxSuccessful());
	}
}
