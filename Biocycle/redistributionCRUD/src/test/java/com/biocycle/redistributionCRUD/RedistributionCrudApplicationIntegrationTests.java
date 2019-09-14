package com.biocycle.redistributionCRUD;

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
public class RedistributionCrudApplicationIntegrationTests {
	
	@Autowired
	MockMvc mockMvc;
	
	//---- GET
	@Test
	public void iT01findRedistributionById() throws Exception {
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/redistributions/1")	
								.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
	}
	
	@Test
	public void iT02findRedistributionByOrganisationId() throws Exception {
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/redistributions/organisations/2")	
								.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
	}
	
	@Test
	public void iT03findAllActiveRedistributions() throws Exception {
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/redistributions/active")	
								.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
	}
	
	//---- POST 
	@Test
	public void iT04addRedistribution() throws Exception {
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.post("/redistributions")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(RedistributionTestHelper.RedistributionDtoPOSTJson())
				).andExpect(status().is2xxSuccessful());
	}
	
	//---- PUT 
	@Test
	public void iT05updateredistribution() throws Exception {
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.put("/redistributions")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(RedistributionTestHelper.RedistributionDtoPUTJson())
				).andExpect(status().is2xxSuccessful());
	}
	
	//---- DELETE 
	@Test
	public void iT06deleteRedistribution() throws Exception {
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.delete("/redistributions/4")
				).andExpect(status().is2xxSuccessful());
	}
	
	
}
