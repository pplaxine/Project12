package com.biocycle.giveAwayCRUD;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

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

import com.biocycle.giveAwayCRUD.dao.GiveAwayDao;
import com.biocycle.giveAwayCRUD.model.GiveAway;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GiveAwayCrudIntegrationTests {

	@Autowired
	MockMvc mockMvc;
	@Autowired
	GiveAwayDao giveAwayDao;
	
	//---- GET
	@Test
	public void iT01FindActiveGiveAwayByDate() throws Exception {
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/giveaways/date/2019-07-21")	
								.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
	}
	
	@Test
	public void iT02findActiveGiveAway() throws Exception {
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/giveaways/active")	
								.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
	}
	
	@Test
	public void iT03findAllGiveAwayByOrganisationId() throws Exception {
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/giveaways/all/1")	
								.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
	}
	
	@Test
	public void iT04findGiveAway() throws Exception {
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/giveaways/1")	
								.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
	}
	
	//---- POST 
	@Test
	public void iT05addGiveAway() throws Exception {
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.post("/giveaways")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(GiveAwayTestHelper.getGiveAwayDtoPOSTJson())
				).andExpect(status().is2xxSuccessful());
	}
	
	//---- PUT 
	@Test
	public void iT06updateGiveAway() throws Exception {
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.put("/giveaways")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(GiveAwayTestHelper.getGiveAwayDtoPUTJson())
				).andExpect(status().is2xxSuccessful());
	}
	
	//---- DELETE 
	@Test
	public void iT07deleteGiveAway() throws Exception {
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.delete("/giveaways/4")
				).andExpect(status().is2xxSuccessful());
	}
	
	
	
	

}