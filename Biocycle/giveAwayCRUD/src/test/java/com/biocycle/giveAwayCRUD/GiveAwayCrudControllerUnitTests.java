package com.biocycle.giveAwayCRUD;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.biocycle.giveAwayCRUD.dao.GiveAwayDao;
import com.biocycle.giveAwayCRUD.dto.GiveAwayDto;
import com.biocycle.giveAwayCRUD.dto.mapper.GiveAwayDtoMapper;
import com.biocycle.giveAwayCRUD.model.GiveAway;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@RunWith(SpringRunner.class)
@WebMvcTest
public class GiveAwayCrudControllerUnitTests {

	@Autowired
	MockMvc mockMvc;
	@MockBean
	GiveAwayDao giveAwayDao;
	@MockBean
	GiveAwayDtoMapper giveAwayDtoMapper;
	
	private ObjectWriter objectWriter;
	private GiveAway giveAwayStub;
	private GiveAwayDto giveAwayDtoStub;
	private List<GiveAway> giveAwayList;
	
	@Before
	public void executeBeforeEach() {
		
		//jSon object writer
		ObjectMapper jsonM = new ObjectMapper();
		jsonM.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		objectWriter = jsonM.writer().withDefaultPrettyPrinter();
		
		//--Stub
		//GiveAway
		giveAwayStub = new GiveAway();
		giveAwayStub.setId(1);
		//GiveAwayDtoStub
		giveAwayDtoStub = new GiveAwayDto();
		giveAwayDtoStub.setId(1);
		//GiveAwayList
		giveAwayList = new ArrayList<>();
		giveAwayList.add(giveAwayStub);
		giveAwayList.add(giveAwayStub);
		
	}
	
	//---- GET
	@Test
	public void controllerFindActiveGiveAwayByDateUT() throws Exception {

		//Date
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse("2019-11-27");
		
		//Dao
		Mockito.when(giveAwayDao.findActiveGiveAwayByDate(date)).thenReturn(Optional.of(giveAwayList));

		//Mapper
		Mockito.when(giveAwayDtoMapper.giveAwayToGiveAwayDto(giveAwayStub)).thenReturn(giveAwayDtoStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/giveaways/date/2019-11-27")	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 200;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
		
	}
	
	@Test
	public void controllerFindActiveGiveAwayByDateExceptionUT() throws Exception {

		//Date
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse("2019-11-27");
		
		//Dao
		Mockito.when(giveAwayDao.findActiveGiveAwayByDate(date)).thenReturn(Optional.empty());
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/giveaways/date/2019-11-27")	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 404;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void controllerFindActiveGiveAwayUT() throws Exception {
		
		//Dao
		Mockito.when(giveAwayDao.findAllActiveGiveAway()).thenReturn(Optional.of(giveAwayList));

		//Mapper
		Mockito.when(giveAwayDtoMapper.giveAwayToGiveAwayDto(giveAwayStub)).thenReturn(giveAwayDtoStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/giveaways/active")	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 200;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
		
	}
	
	@Test
	public void controllerFindActiveGiveAwayExceptionUT() throws Exception {
		
		//Dao
		Mockito.when(giveAwayDao.findAllActiveGiveAway()).thenReturn(Optional.empty());
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/giveaways/active")	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 404;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
		
	}
	
	@Test
	public void controllerFindAllGiveAwayByOrganisationIdUT() throws Exception {

		int organisationId = 1;
		
		//Dao
		Mockito.when(giveAwayDao.findAllGiveAwayByOrganisationId(organisationId)).thenReturn(Optional.of(giveAwayList));

		//Mapper
		Mockito.when(giveAwayDtoMapper.giveAwayToGiveAwayDto(giveAwayStub)).thenReturn(giveAwayDtoStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/giveaways/all/"+ organisationId)	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 200;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
		
	}
	
	@Test
	public void controllerFindAllGiveAwayByOrganisationIdExceptionUT() throws Exception {

		int organisationId = 1;
		
		//Dao
		Mockito.when(giveAwayDao.findAllGiveAwayByOrganisationId(organisationId)).thenReturn(Optional.empty());
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/giveaways/all/"+ organisationId)	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 404;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void controllerFindGiveAwayUT() throws Exception {

		int giveAwayId = 1;
		
		//Dao
		Mockito.when(giveAwayDao.findById(giveAwayId)).thenReturn(Optional.of(giveAwayStub));

		//Mapper
		Mockito.when(giveAwayDtoMapper.giveAwayToGiveAwayDto(giveAwayStub)).thenReturn(giveAwayDtoStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/giveaways/"+ giveAwayId)	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 200;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
		
	}
	
	@Test
	public void controllerFindGiveAwayExceptionUT() throws Exception {

		int giveAwayId = 1;
		
		//Dao
		Mockito.when(giveAwayDao.findById(giveAwayId)).thenReturn(Optional.empty());
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/giveaways/"+ giveAwayId)	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 404;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	//---- POST
	@Test
	public void controllerAddGiveAwayUT() throws Exception {

		giveAwayStub.setId(1);
		
		//Dto to Json
		String giveAwayJson = objectWriter.writeValueAsString(giveAwayDtoStub);
		
		//Mapper
		Mockito.when(giveAwayDtoMapper.giveAwayDtoToGiveAway(giveAwayDtoStub)).thenReturn(giveAwayStub);

		//Dao
		Mockito.when(giveAwayDao.save(null)).thenReturn(giveAwayStub);

		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.post("/giveaways")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(giveAwayJson)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 201;
		String[] location = mvcResult.getResponse().getHeader("Location").split("/");
		int pathId = Integer.valueOf(location[location.length-1]);
		
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
		assertEquals("Unexpected location : ", giveAwayStub.getId(), pathId);
	}
	
	@Test
	public void controllerAddGiveAwayIAmATeaPotExceptionUT() throws Exception {
		
		//Dto to Json
		String giveAwayJson = objectWriter.writeValueAsString(giveAwayDtoStub);
		
		//Mapper
		Mockito.when(giveAwayDtoMapper.giveAwayDtoToGiveAway(giveAwayDtoStub)).thenReturn(giveAwayStub);

		//Dao
		Mockito.when(giveAwayDao.save(null)).thenThrow(DataIntegrityViolationException.class);

		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.post("/giveaways")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(giveAwayJson)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 418;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	
	}
	
	@Test(expected = Exception.class)
	public void controllerAddGiveAwayExceptionUT() throws Exception {

		//Dto to Json
		String giveAwayJson = objectWriter.writeValueAsString(giveAwayDtoStub);
		
		//Mapper
		Mockito.when(giveAwayDtoMapper.giveAwayDtoToGiveAway(giveAwayDtoStub)).thenReturn(giveAwayStub);

		//Dao
		Mockito.when(giveAwayDao.save(null)).thenThrow(Exception.class);
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.post("/giveaways")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(giveAwayJson)
				).andReturn();
	
	}
	
	
	
	
	
	

}
