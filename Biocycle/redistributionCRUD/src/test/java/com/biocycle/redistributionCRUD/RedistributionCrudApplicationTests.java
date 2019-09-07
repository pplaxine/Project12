package com.biocycle.redistributionCRUD;

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

import com.biocycle.redistributionCRUD.dao.RedistributionDao;
import com.biocycle.redistributionCRUD.dto.RedistributionDto;
import com.biocycle.redistributionCRUD.dto.mapper.RedistributionDtoMapper;
import com.biocycle.redistributionCRUD.model.Redistribution;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@RunWith(SpringRunner.class)
@WebMvcTest
public class RedistributionCrudApplicationTests {

	@Autowired
	MockMvc mockMvc;
	@MockBean
	RedistributionDao redistributionDao;
	@MockBean
	RedistributionDtoMapper redistributionDtoMapper;
	
	private ObjectWriter objectWriter;
	private Redistribution redistributionStub;
	private RedistributionDto redistributionDtoStub;
	private List<Redistribution> redistributionList;

	@Before
	public void executeBeforeEach() {
		
		//jSon object writer
		ObjectMapper jsonM = new ObjectMapper();
		jsonM.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		objectWriter = jsonM.writer().withDefaultPrettyPrinter();
		
		//--Stub
		//GiveAway
		redistributionStub = new Redistribution();
		redistributionStub.setId(1);
		//GiveAwayDtoStub
		redistributionDtoStub = new RedistributionDto();
		redistributionDtoStub.setId(1);
		//RedistributionList
		redistributionList = new ArrayList<>();
		redistributionList.add(redistributionStub);
		redistributionList.add(redistributionStub);
	}
	
	@Test
	public void controllerFindRedistributionByIdUT() throws Exception {

		int redistributionId = 1;
		
		//Dao
		Mockito.when(redistributionDao.findById(redistributionId)).thenReturn(Optional.of(redistributionStub));

		//Mapper
		Mockito.when(redistributionDtoMapper.RedistribtionToRedistributionDto(redistributionStub)).thenReturn(redistributionDtoStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/redistributions/"+ redistributionId)	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 200;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
		
	}
	
	@Test
	public void controllerFindRedistributionByIdExceptionUT() throws Exception {

		int redistributionId = 1;
		
		//Dao
		Mockito.when(redistributionDao.findById(redistributionId)).thenReturn(Optional.empty());
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/redistributions/"+ redistributionId)	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 404;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void controllerFindRedistributionByOrganisationIdUT() throws Exception {

		int organisationId = 1;
		
		//Dao
		Mockito.when(redistributionDao.findAllRedistributionByOrganisationId(organisationId)).thenReturn(Optional.of(redistributionList));

		//Mapper
		Mockito.when(redistributionDtoMapper.RedistribtionToRedistributionDto(redistributionStub)).thenReturn(redistributionDtoStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/redistributions/organisations/"+ organisationId)	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 200;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
		
	}

	@Test
	public void controllerFindRedistributionByOrganisationIdExceptionFromNullUT() throws Exception {
		
		int organisationId = 1;
		
		//Dao
		Mockito.when(redistributionDao.findAllRedistributionByOrganisationId(organisationId)).thenReturn(Optional.empty());
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/redistributions/organisations/"+ organisationId)	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 404;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void controllerFindRedistributionByOrganisationIdExceptionFromEmptyListUT() throws Exception {
	
		int organisationId = 1;
		redistributionList.clear();
		
		//Dao
		Mockito.when(redistributionDao.findAllRedistributionByOrganisationId(organisationId)).thenReturn(Optional.of(redistributionList));
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/redistributions/organisations/"+ organisationId)	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 404;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void controllerFindAllActiveRedistributionUT() throws Exception {
		
		//Dao
		Mockito.when(redistributionDao.findAllActiveRedistribution()).thenReturn(Optional.of(redistributionList));

		//Mapper
		Mockito.when(redistributionDtoMapper.RedistribtionToRedistributionDto(redistributionStub)).thenReturn(redistributionDtoStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/redistributions/active")	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 200;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void controllerFindAllActiveRedistributionExceptionNullUT() throws Exception {
		
		//Dao
		Mockito.when(redistributionDao.findAllActiveRedistribution()).thenReturn(Optional.empty());

		//Mapper
		Mockito.when(redistributionDtoMapper.RedistribtionToRedistributionDto(redistributionStub)).thenReturn(redistributionDtoStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/redistributions/active")	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 404;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void controllerFindAllActiveRedistributionExceptionEmptyListUT() throws Exception {
		
		redistributionList.clear();
		
		//Dao
		Mockito.when(redistributionDao.findAllActiveRedistribution()).thenReturn(Optional.of(redistributionList));

		//Mapper
		Mockito.when(redistributionDtoMapper.RedistribtionToRedistributionDto(redistributionStub)).thenReturn(redistributionDtoStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/redistributions/active")	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 404;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	//---- POST
	@Test
	public void controllerAddRedistributionUT() throws Exception {

		redistributionStub.setId(1);
		
		//Dto to Json
		String redistributionJson = objectWriter.writeValueAsString(redistributionDtoStub);
		
		//Mapper
		Mockito.when(redistributionDtoMapper.redistributionDtoToRedistribution(redistributionDtoStub)).thenReturn(redistributionStub);

		//Dao
		Mockito.when(redistributionDao.save(null)).thenReturn(redistributionStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.post("/redistributions")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(redistributionJson)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 201;
		String[] location = mvcResult.getResponse().getHeader("Location").split("/");
		int pathId = Integer.valueOf(location[location.length-1]);
		
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
		assertEquals("Unexpected location : ", redistributionStub.getId(), pathId);
	}
	
	@Test
	public void controllerAddRedistributionErrorUT() throws Exception {

		redistributionStub.setId(1);
		
		//Dto to Json
		String redistributionJson = objectWriter.writeValueAsString(redistributionDtoStub);
		
		//Mapper
		Mockito.when(redistributionDtoMapper.redistributionDtoToRedistribution(redistributionDtoStub)).thenReturn(redistributionStub);

		//Dao
		Mockito.when(redistributionDao.save(null)).thenReturn(null);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.post("/redistributions")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(redistributionJson)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 204;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}

}
