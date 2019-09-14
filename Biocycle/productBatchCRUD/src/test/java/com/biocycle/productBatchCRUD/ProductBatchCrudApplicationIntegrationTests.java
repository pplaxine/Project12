package com.biocycle.productBatchCRUD;

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
public class ProductBatchCrudApplicationIntegrationTests {
	
	@Autowired
	MockMvc mockMvc;
	
	//---- GET
	@Test
	public void iT01findAllProductBatch() throws Exception {
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/productbatches")	
								.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
	}
	
	@Test
	public void iT02findProductBatchById() throws Exception {
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/productbatches/1")	
								.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
	}
	
	@Test
	public void iT03getProductSoonToExpire() throws Exception {
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/productbatches/soon/expired")	
								.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
	}
	
	//---- POST 
	@Test
	public void iT04addProductBatch() throws Exception {
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.post("/productbatches")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(ProductBatchTestHelper.getProductBatchDtoPOSTJson())
				).andExpect(status().is2xxSuccessful());
	}
	
	//---- PUT 
	@Test
	public void iT05updateProductBatch() throws Exception {
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.put("/productbatches")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(ProductBatchTestHelper.getProductBatchDtoPutJson())
				).andExpect(status().is2xxSuccessful());
	}
	
	@Test
	public void iT06updateProductBatch() throws Exception {
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.put("/productbatches/is-awaiting-for-collection/6/true")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(ProductBatchTestHelper.getProductBatchDtoPutJson())
				).andExpect(status().is2xxSuccessful());
	}
	
	//---- DELETE 
	@Test
	public void iT07deleteOffer() throws Exception {
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.delete("/productbatches/6")
				).andExpect(status().is2xxSuccessful());
	}
	
}
