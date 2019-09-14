package com.biocycle.storageContainerCRUD;

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
public class StorageContainerCrudApplicationIntegrationTests {
	
	@Autowired
	MockMvc mockMvc;
	
	//---- GET
	@Test
	public void iT01findStorageContainerById() throws Exception {
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/storagecontainers/1")	
								.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
	}
	
	@Test
	public void iT02findAllEmptyStorageContainer() throws Exception {
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/storagecontainers/empty")	
								.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
	}
	
	@Test
	public void iT03findStorageContainerFromIdList() throws Exception {
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/storagecontainers/listId")	
								.param("containerIdList", "1,2")
								.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
	}
	
	//---- POST 
	@Test
	public void iT04addStorageContainer() throws Exception {
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.post("/storagecontainers")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(StorageContainerTestHelper.getStorageContainerDtoPOSTJson())
				).andExpect(status().is2xxSuccessful());
	}
	
	//---- PUT 
	@Test
	public void iT05updateStorageContainer() throws Exception {
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.put("/storagecontainers")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(StorageContainerTestHelper.getStorageContainerDtoPUTJSon())
				).andExpect(status().is2xxSuccessful());
	}
	
	//---- PUT 
	@Test
	public void iT06updateStorageContainerList() throws Exception {
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.put("/storagecontainers/updatestatus")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(StorageContainerTestHelper.getStorageContainerDtoListPUTJson())
				).andExpect(status().is2xxSuccessful());
	}
	
	//---- DELETE 
	@Test
	public void iT07deleteStorageContainer() throws Exception {
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.delete("/storagecontainers/31")
				).andExpect(status().is2xxSuccessful());
	}
}
