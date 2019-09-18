package com.biocycle.organisationCRUD;

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
public class OrganisationCrudApplicationIntegrationTests {

	@Autowired
	MockMvc mockMvc;
	
	//---- GET
	@Test
	public void iT01findAllOrganisation() throws Exception {
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/organisations")	
								.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
	}
	
	@Test
	public void iT02findOrganisationById() throws Exception {
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/organisations/1")	
								.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
	}
	
	@Test
	public void iT03findOrganisationByEmail() throws Exception {
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/organisations/email/orga1@orange.fr")	
								.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
	}
	
	@Test
	public void iT04findAllOrganisationByIsValidated() throws Exception {
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/organisations/validated/true")	
								.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
	}
	
	//---- POST 
	@Test
	public void iT05addOrganisation() throws Exception {
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.post("/organisations")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(OrganisationTestHelper.getOrganisationDtoPOSTJson())
				).andExpect(status().is2xxSuccessful());
	}
	
	//---- PUT 
	@Test
	public void iT06updateOrganisation() throws Exception {
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.put("/organisations")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(OrganisationTestHelper.getOrganisationDtoPUTJson())
				).andExpect(status().is2xxSuccessful());
	}
	
	//---- DELETE 
	@Test
	public void iT07deleteOrganisation() throws Exception {
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.delete("/organisations/3")
				).andExpect(status().is2xxSuccessful());
	}
	
	
}
