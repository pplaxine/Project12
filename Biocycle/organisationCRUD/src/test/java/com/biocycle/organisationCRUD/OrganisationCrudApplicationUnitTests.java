package com.biocycle.organisationCRUD;

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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.biocycle.organisationCRUD.dao.OrganisationDao;
import com.biocycle.organisationCRUD.dto.OrganisationDto;
import com.biocycle.organisationCRUD.dto.mapper.OrganisationDtoMapper;
import com.biocycle.organisationCRUD.model.Organisation;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@RunWith(SpringRunner.class)
@WebMvcTest
public class OrganisationCrudApplicationUnitTests {

	@Autowired
	MockMvc mockMvc;
	@MockBean
	OrganisationDao organisationDao;
	@MockBean
	OrganisationDtoMapper organisationDtoMapper;
	
	private ObjectWriter objectWriter;
	private Organisation organisationStub;
	private OrganisationDto organisationDtoStub;
	private List<Organisation> organisationList;

	@Before
	public void executeBeforeEach() {
		
		//jSon object writer
		ObjectMapper jsonM = new ObjectMapper();
		jsonM.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		objectWriter = jsonM.writer().withDefaultPrettyPrinter();
		
		//--Stub
		//GiveAway
		organisationStub = new Organisation();
		organisationStub.setId(1);
		//GiveAwayDtoStub
		organisationDtoStub = new OrganisationDto();
		organisationDtoStub.setId(1);
		//GiveAwayList
		organisationList = new ArrayList<>();
		organisationList.add(organisationStub);
		organisationList.add(organisationStub);
	}
	
	//---- GET
	@Test
	public void controllerFindAllOrganisationUT() throws Exception {
		
		//Dao
		Mockito.when(organisationDao.findAll()).thenReturn(organisationList);

		//Mapper
		Mockito.when(organisationDtoMapper.organisationToOrganisationDto(organisationStub)).thenReturn(organisationDtoStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/organisations")	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 200;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void controllerFindAllOrganidationExceptionUT() throws Exception {

		organisationList.clear();
		
		//Dao
		Mockito.when(organisationDao.findAll()).thenReturn(organisationList);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/organisations")	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 404;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void controllerFindOrganisationByIdUT() throws Exception {

		int organisationId = 1;
		
		//Dao
		Mockito.when(organisationDao.findById(organisationId)).thenReturn(Optional.of(organisationStub));

		//Mapper
		Mockito.when(organisationDtoMapper.organisationToOrganisationDto(organisationStub)).thenReturn(organisationDtoStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/organisations/"+ organisationId)	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 200;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
		
	}
	
	@Test
	public void controllerFindOrganidationByIdExceptionUT() throws Exception {

		int organisationId = 1;
		
		//Dao
		Mockito.when(organisationDao.findById(organisationId)).thenReturn(Optional.empty());
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/organisations/"+ organisationId)	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 404;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void controllerFindOrganisationByEmailUT() throws Exception {

		String organisationEmail = "orange@orange.fr";
		
		//Dao
		Mockito.when(organisationDao.findOrganisationByEmail(organisationEmail)).thenReturn(Optional.of(organisationStub));

		//Mapper
		Mockito.when(organisationDtoMapper.organisationToOrganisationDto(organisationStub)).thenReturn(organisationDtoStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/organisations/email/"+ organisationEmail)	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 200;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
		
	}
	
	@Test
	public void controllerFindOrganidationByEmailExceptionUT() throws Exception {

		String organisationEmail = "orange@orange.fr";
		
		//Dao
		Mockito.when(organisationDao.findOrganisationByEmail(organisationEmail)).thenReturn(Optional.empty());
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/organisations/email/"+ organisationEmail)	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 404;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void controllerFindAllOrganisationByIsValidatedUT() throws Exception {

		Boolean isValidated = true;
		
		//Dao
		Mockito.when(organisationDao.findAllOrganisationByIsValidated(isValidated)).thenReturn(Optional.of(organisationList));

		//Mapper
		Mockito.when(organisationDtoMapper.organisationToOrganisationDto(organisationStub)).thenReturn(organisationDtoStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/organisations/validated/true")	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 200;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
		
	}
	
	@Test
	public void controllerFindAllOrganisationByIsValidatedExceptionUT() throws Exception {

		Boolean isValidated = true;
		
		//Dao
		Mockito.when(organisationDao.findAllOrganisationByIsValidated(isValidated)).thenReturn(Optional.empty());
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/organisations/validated/true")		
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 404;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	//---- POST
	@Test
	public void controllerAddOrganisationUT() throws Exception {

		organisationStub.setId(1);
		
		//Dto to Json
		String organisationJson = objectWriter.writeValueAsString(organisationDtoStub);
		
		//Mapper
		Mockito.when(organisationDtoMapper.organisationDtoToOrganisation(organisationDtoStub)).thenReturn(organisationStub);

		//Dao
		Mockito.when(organisationDao.save(null)).thenReturn(organisationStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.post("/organisations")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(organisationJson)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 201;
		String[] location = mvcResult.getResponse().getHeader("Location").split("/");
		int pathId = Integer.valueOf(location[location.length-1]);
		
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
		assertEquals("Unexpected location : ", organisationStub.getId(), pathId);
	}
		
	@Test
	public void controllerAddOrganisationIAmATeaPotExceptionUT() throws Exception {

		organisationStub.setId(1);
		
		//Dto to Json
		String organisationJson = objectWriter.writeValueAsString(organisationDtoStub);
		
		//Mapper
		Mockito.when(organisationDtoMapper.organisationDtoToOrganisation(organisationDtoStub)).thenReturn(organisationStub);

		//Dao
		Mockito.when(organisationDao.save(null)).thenThrow(DataIntegrityViolationException.class);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.post("/organisations")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(organisationJson)
				).andReturn();
				
		//Assert
		int expectedStatusCode = 418;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	@Test(expected = Exception.class)
	public void controllerAddOrganisationExceptionUT() throws Exception {

		organisationStub.setId(1);
		
		//Dto to Json
		String organisationJson = objectWriter.writeValueAsString(organisationDtoStub);
		
		//Mapper
		Mockito.when(organisationDtoMapper.organisationDtoToOrganisation(organisationDtoStub)).thenReturn(organisationStub);

		//Dao
		Mockito.when(organisationDao.save(null)).thenThrow(Exception.class);
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.post("/organisations")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(organisationJson)
				).andReturn();
	}
	
	@Test
	public void controllerUpdateOrganisationUT() throws Exception {

		organisationStub.setId(1);
		
		//Dto to Json
		String organisationJson = objectWriter.writeValueAsString(organisationDtoStub);
		
		//Mapper
		Mockito.when(organisationDtoMapper.organisationDtoToOrganisation(organisationDtoStub)).thenReturn(organisationStub);

		//Dao
		Mockito.when(organisationDao.save(null)).thenReturn(organisationStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.put("/organisations")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(organisationJson)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 200;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	
	

}
