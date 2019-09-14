package com.biocycle.offerCRUD;

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

import com.biocycle.offerCRUD.dao.OfferDao;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OfferCrudIntegretionTests {

	@Autowired
	MockMvc mockMvc;
	@Autowired
	OfferDao offerDao;
	
	//---- GET
	@Test
	public void iT01findOfferById() throws Exception {
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/offers/1")	
								.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
	}
	
	//---- POST 
	@Test
	public void iT02addOffer() throws Exception {
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.post("/offers")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(OfferTestHelper.getOfferDtoPOSTJson())
				).andExpect(status().is2xxSuccessful());
	}
	
	//---- PUT 
	@Test
	public void iT03updateGiveAway() throws Exception {
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.put("/offers")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(OfferTestHelper.getOfferDtoPUTJson())
				).andExpect(status().is2xxSuccessful());
	}
	
	//---- DELETE 
	@Test
	public void iT04deleteOffer() throws Exception {
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.delete("/offers/4")
				).andExpect(status().is2xxSuccessful());
	}
}
