package com.biocycle.storageContainerCRUD;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.biocycle.storageContainerCRUD.dao.StorageContainerDao;
import com.biocycle.storageContainerCRUD.dto.StorageContainerDto;
import com.biocycle.storageContainerCRUD.dto.mapper.StorageContainerDtoMapper;
import com.biocycle.storageContainerCRUD.model.StorageContainer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@RunWith(SpringRunner.class)
@WebMvcTest
public class StorageContainerCrudApplicationUnitTests {

	@Autowired
	MockMvc mockMvc;
	@MockBean
	StorageContainerDao storageContainerDao;
	@MockBean
	StorageContainerDtoMapper storageContainerDtoMapper;
	
	private ObjectWriter objectWriter;
	private StorageContainer storageContainerStub;
	private StorageContainerDto storageContainerDtoStub;
	private List<StorageContainer> storageContainerList;
	private List<StorageContainerDto> storageContainerDtoList;

	@Before
	public void executeBeforeEach() {
		
		//jSon object writer
		ObjectMapper jsonM = new ObjectMapper();
		jsonM.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		objectWriter = jsonM.writer().withDefaultPrettyPrinter();
		
		//--Stub
		//GiveAway
		storageContainerStub = new StorageContainer();
		storageContainerStub.setId(1);
		//GiveAwayDtoStub
		storageContainerDtoStub = new StorageContainerDto();
		storageContainerDtoStub.setId(1);
		//RedistributionList
		storageContainerList = new ArrayList<>();
		storageContainerList.add(storageContainerStub);
		storageContainerList.add(storageContainerStub);
		//RedistributionDtoList
		storageContainerDtoList = new ArrayList<>();
		storageContainerDtoList.add(storageContainerDtoStub);
		storageContainerDtoList.add(storageContainerDtoStub);
	}
	
	@Test
	public void controllerFindStorageContainerByIdUT() throws Exception {

		int storageContainerId = 1;
		
		//Dao
		Mockito.when(storageContainerDao.findById(storageContainerId)).thenReturn(Optional.of(storageContainerStub));

		//Mapper
		Mockito.when(storageContainerDtoMapper.storageContainerToStorageContainerDto(storageContainerStub)).thenReturn(storageContainerDtoStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/storagecontainers/"+ storageContainerId)	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 200;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void controllerFindStorageContainerByIdExceptionUT() throws Exception {

		int storageContainerId = 1;
		
		//Dao
		Mockito.when(storageContainerDao.findById(storageContainerId)).thenReturn(Optional.empty());
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/storagecontainers/"+ storageContainerId)	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();

		//Assert
		int expectedStatusCode = 404;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void controllerFindAllEmptyStorageContainerUT() throws Exception {
		
		//Dao
		Mockito.when(storageContainerDao.findAllEmptyStorageContainer()).thenReturn(Optional.of(storageContainerList));

		//Mapper
		Mockito.when(storageContainerDtoMapper.storageContainerToStorageContainerDto(storageContainerStub)).thenReturn(storageContainerDtoStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/storagecontainers/empty")	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 200;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	
	}
	
	@Test
	public void controllerFindAllEmptyStorageContainerExceptionUT() throws Exception {
		
		//Dao
		Mockito.when(storageContainerDao.findAllEmptyStorageContainer()).thenReturn(Optional.empty());

		//Mapper
		Mockito.when(storageContainerDtoMapper.storageContainerToStorageContainerDto(storageContainerStub)).thenReturn(storageContainerDtoStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/storagecontainers/empty")	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 404;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void controllerFindStorageContainerFromIdListUT() throws Exception {
		
		//storageContainerList
		List<Integer> storageContainerIdList = new ArrayList<>();
		storageContainerIdList.add(1);
		storageContainerIdList.add(2);
		storageContainerIdList.add(3);
		
		//Dao
		Mockito.when(storageContainerDao.findAllById(storageContainerIdList)).thenReturn(storageContainerList);

		//Mapper
		Mockito.when(storageContainerDtoMapper.storageContainerToStorageContainerDto(storageContainerStub)).thenReturn(storageContainerDtoStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/storagecontainers/listId")	
									.param("containerIdList", "1,2,3")
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 200;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void controllerFindStorageContainerFromIdListExceptionUT() throws Exception {
		
		//storageContainerList
		List<Integer> storageContainerIdList = new ArrayList<>();
		storageContainerIdList.add(1);
		storageContainerIdList.add(2);
		storageContainerIdList.add(3);
		
		//Dao
		Mockito.when(storageContainerDao.findAllById(storageContainerIdList)).thenReturn(null);

		//Mapper
		Mockito.when(storageContainerDtoMapper.storageContainerToStorageContainerDto(storageContainerStub)).thenReturn(storageContainerDtoStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/storagecontainers/listId")	
									.param("containerIdList", "1,2,3")
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 404;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	//---- POST
	@Test
	public void controllerAddStorageContainerUT() throws Exception {

		storageContainerStub.setId(1);
		
		//Dto to Json
		String storageContainerJson = objectWriter.writeValueAsString(storageContainerDtoStub);
		
		//Mapper
		Mockito.when(storageContainerDtoMapper.storageContainerDtoToStorageContainer(storageContainerDtoStub)).thenReturn(storageContainerStub);

		//Dao
		Mockito.when(storageContainerDao.save(null)).thenReturn(storageContainerStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.post("/storagecontainers")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(storageContainerJson)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 201;
		String[] location = mvcResult.getResponse().getHeader("Location").split("/");
		int pathId = Integer.valueOf(location[location.length-1]);
		
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
		assertEquals("Unexpected location : ", storageContainerStub.getId(), pathId);
	}
	
	@Test
	public void controllerAddStorageContainerErrorUT() throws Exception {

		storageContainerStub.setId(1);
		
		//Dto to Json
		String storageContainerJson = objectWriter.writeValueAsString(storageContainerDtoStub);
		
		//Mapper
		Mockito.when(storageContainerDtoMapper.storageContainerDtoToStorageContainer(storageContainerDtoStub)).thenReturn(storageContainerStub);

		//Dao
		Mockito.when(storageContainerDao.save(null)).thenReturn(null);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.post("/storagecontainers")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(storageContainerJson)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 204;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	//---- PUT
	@Test
	public void controllerUpdateStorageContainerUT() throws Exception {

		storageContainerStub.setId(1);
		
		//Dto to Json
		String storageContainerJson = objectWriter.writeValueAsString(storageContainerDtoStub);
		
		//Mapper
		Mockito.when(storageContainerDtoMapper.storageContainerDtoToStorageContainer(storageContainerDtoStub)).thenReturn(storageContainerStub);

		//Dao
		Mockito.when(storageContainerDao.save(null)).thenReturn(storageContainerStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.put("/storagecontainers")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(storageContainerJson)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 204;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void controllerUpdateStorageContainerErrorUT() throws Exception {

		storageContainerStub.setId(1);
		
		//Dto to Json
		String storageContainerJson = objectWriter.writeValueAsString(storageContainerDtoStub);
		
		//Mapper
		Mockito.when(storageContainerDtoMapper.storageContainerDtoToStorageContainer(storageContainerDtoStub)).thenReturn(storageContainerStub);

		//Dao
		Mockito.when(storageContainerDao.save(null)).thenReturn(null);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.put("/storagecontainers")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(storageContainerJson)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 404;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void controllerUpdateStatusStorageContainerUT() throws Exception {

		storageContainerStub.setId(1);
		
		//Dto to Json
		String storageContainerJson = objectWriter.writeValueAsString(storageContainerDtoList);
		
		//Mapper
		Mockito.when(storageContainerDtoMapper.storageContainerDtoToStorageContainer(storageContainerDtoStub)).thenReturn(storageContainerStub);

		//Dao
		Mockito.when(storageContainerDao.save(null)).thenReturn(storageContainerStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.put("/storagecontainers/updatestatus")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(storageContainerJson)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 204;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void controllerUpdateStatusStorageContainerErrorUT() throws Exception {

		storageContainerStub.setId(1);
		
		//Dto to Json
		String storageContainerJson = objectWriter.writeValueAsString(storageContainerDtoList);
		
		//Mapper
		Mockito.when(storageContainerDtoMapper.storageContainerDtoToStorageContainer(storageContainerDtoStub)).thenReturn(storageContainerStub);

		//Dao
		Mockito.when(storageContainerDao.save(null)).thenReturn(null);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.put("/storagecontainers/updatestatus")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(storageContainerJson)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 404;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}


}
