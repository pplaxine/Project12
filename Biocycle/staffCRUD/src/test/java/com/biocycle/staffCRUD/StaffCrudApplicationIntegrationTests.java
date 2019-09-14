package com.biocycle.staffCRUD;

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
public class StaffCrudApplicationIntegrationTests {
	
	@Autowired
	MockMvc mockMvc;
	
	//---- GET
	@Test
	public void iT01findAllStaff() throws Exception {
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/staff")	
								.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
	}
	
	@Test
	public void iT02findStaffById() throws Exception {
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/staff/1")	
								.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
	}
	
	@Test
	public void iT03findStaffByUserName() throws Exception {
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/staff/username/pme")	
								.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
	}
	
	//---- POST 
	@Test
	public void iT04addStaff() throws Exception {
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.post("/staff")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(StaffTestHelper.getStaffDtoPOSTJson())
				).andExpect(status().is2xxSuccessful());
	}
	
	//---- PUT 
	@Test
	public void iT05updateStaff() throws Exception {
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.put("/staff")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(StaffTestHelper.getStaffDtoPUTJson())
				).andExpect(status().is2xxSuccessful());
	}
	
	//---- DELETE 
	@Test
	public void iT06deleteStaff() throws Exception {
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.delete("/staff/9")
				).andExpect(status().is2xxSuccessful());
	}
}
