package com.biocycle.offerCRUD;

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

import com.biocycle.offerCRUD.dao.OfferDao;
import com.biocycle.offerCRUD.dto.OfferDto;
import com.biocycle.offerCRUD.dto.mapper.OfferDtoMapper;
import com.biocycle.offerCRUD.model.Offer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@RunWith(SpringRunner.class)
@WebMvcTest
public class OfferCrudControllerUnitTests {

	@Autowired
	MockMvc mockMvc;
	@MockBean
	OfferDao offerDao;
	@MockBean
	OfferDtoMapper offerDtoMapper;
	
	private ObjectWriter objectWriter;
	private Offer offerStub;
	private OfferDto offerDtoStub;
	private List<Offer> offerList;

	@Before
	public void executeBeforeEach() {
		
		//jSon object writer
		ObjectMapper jsonM = new ObjectMapper();
		jsonM.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		objectWriter = jsonM.writer().withDefaultPrettyPrinter();
		
		//--Stub
		//GiveAway
		offerStub = new Offer();
		offerStub.setId(1);
		//GiveAwayDtoStub
		offerDtoStub = new OfferDto();
		offerDtoStub.setId(1);
		//GiveAwayList
		offerList = new ArrayList<>();
		offerList.add(offerStub);
		offerList.add(offerStub);
	}
	
	//---- GET
	@Test
	public void controllerFindOfferByIdUT() throws Exception {

		int offerId = 1;
		
		//Dao
		Mockito.when(offerDao.findById(offerId)).thenReturn(Optional.of(offerStub));

		//Mapper
		Mockito.when(offerDtoMapper.offerToOfferDto(offerStub)).thenReturn(offerDtoStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/offers/"+ offerId)	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 200;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
		
	}
	
	@Test
	public void controllerFindOfferByIdExceptionUT() throws Exception {

		int offerId = 1;
		
		//Dao
		Mockito.when(offerDao.findById(offerId)).thenReturn(Optional.empty());
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/offers/"+ offerId)	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 404;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	//---- POST
	@Test
	public void controllerAddOfferUT() throws Exception {

		offerStub.setId(1);
		
		//Dto to Json
		String offerJson = objectWriter.writeValueAsString(offerDtoStub);
		
		//Mapper
		Mockito.when(offerDtoMapper.offerDtoToOffer(offerDtoStub)).thenReturn(offerStub);

		//Dao
		Mockito.when(offerDao.save(null)).thenReturn(offerStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.post("/offers")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(offerJson)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 201;
		String[] location = mvcResult.getResponse().getHeader("Location").split("/");
		int pathId = Integer.valueOf(location[location.length-1]);
		
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
		assertEquals("Unexpected location : ", offerStub.getId(), pathId);
	}
	
	@Test
	public void controllerAddOfferExceptionUT() throws Exception {

		offerStub.setId(1);
		
		//Dto to Json
		String offerJson = objectWriter.writeValueAsString(offerDtoStub);
		
		//Mapper
		Mockito.when(offerDtoMapper.offerDtoToOffer(offerDtoStub)).thenReturn(offerStub);

		//Dao
		Mockito.when(offerDao.save(null)).thenReturn(null);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.post("/offers")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(offerJson)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 204;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	//---- PUT
	@Test
	public void controllerUpdateOfferUT() throws Exception {

		offerStub.setId(1);
		
		//Dto to Json
		String offerJson = objectWriter.writeValueAsString(offerDtoStub);
		
		//Mapper
		Mockito.when(offerDtoMapper.offerDtoToOffer(offerDtoStub)).thenReturn(offerStub);

		//Dao
		Mockito.when(offerDao.save(null)).thenReturn(offerStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.put("/offers")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(offerJson)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 202;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	//---- PUT
	@Test
	public void controllerUpdateOfferExceptionUT() throws Exception {

		offerStub.setId(1);
		
		//Dto to Json
		String offerJson = objectWriter.writeValueAsString(offerDtoStub);
		
		//Mapper
		Mockito.when(offerDtoMapper.offerDtoToOffer(offerDtoStub)).thenReturn(offerStub);

		//Dao
		Mockito.when(offerDao.save(null)).thenReturn(null);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.put("/offers")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(offerJson)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 204;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	
}
