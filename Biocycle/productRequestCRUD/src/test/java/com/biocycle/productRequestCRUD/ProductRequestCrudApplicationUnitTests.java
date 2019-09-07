package com.biocycle.productRequestCRUD;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

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

import com.biocycle.productRequestCRUD.dao.ProductRequestDao;
import com.biocycle.productRequestCRUD.dto.ProductRequestDto;
import com.biocycle.productRequestCRUD.dto.mapper.ProductRequestDtoMapper;
import com.biocycle.productRequestCRUD.model.ProductRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ProductRequestCrudApplicationUnitTests {

	@Autowired
	MockMvc mockMvc;
	@MockBean
	ProductRequestDao productRequestDao;
	@MockBean
	ProductRequestDtoMapper productRequestDtoMapper;
	
	private ObjectWriter objectWriter;
	private ProductRequest productRequestStub;
	private ProductRequestDto productRequestDtoStub;
	private List<ProductRequest> productRequestList;
	private List<ProductRequestDto> productRequestDtoList;

	@Before
	public void executeBeforeEach() {
		
		//jSon object writer
		ObjectMapper jsonM = new ObjectMapper();
		jsonM.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		objectWriter = jsonM.writer().withDefaultPrettyPrinter();
		
		//--Stub
		//GiveAway
		productRequestStub = new ProductRequest();
		productRequestStub.setId(1);
		//GiveAwayDtoStub
		productRequestDtoStub = new ProductRequestDto();
		productRequestDtoStub.setId(1);
		//GiveAwayList
		productRequestList = new ArrayList<>();
		productRequestList.add(productRequestStub);
		productRequestList.add(productRequestStub);
		
		//GiveAwayDtoList
		productRequestDtoList = new ArrayList<>();
		productRequestDtoList.add(productRequestDtoStub);
		productRequestDtoList.add(productRequestDtoStub);
		
	}
	
	@Test
	public void controllerFindProductRequestByIdUT() throws Exception {

		int productRequestId = 1;
		
		//Dao
		Mockito.when(productRequestDao.findById(productRequestId)).thenReturn(Optional.of(productRequestStub));

		//Mapper
		Mockito.when(productRequestDtoMapper.productRequestToProductRequestDto(productRequestStub)).thenReturn(productRequestDtoStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/productrequests/"+ productRequestId)	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 200;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
		
	}
	
	@Test
	public void controllerFindProductRequestByIdExceptionUT() throws Exception {

		int productRequestId = 1;
		
		//Dao
		Mockito.when(productRequestDao.findById(productRequestId)).thenReturn(Optional.empty());
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/productrequests/"+ productRequestId)	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 404;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	//---- POST
	@Test
	public void controllerAddProductRequestUT() throws Exception {

		productRequestStub.setId(1);
		
		//Dto to Json
		String productRequestJson = objectWriter.writeValueAsString(productRequestDtoStub);
		
		//Mapper
		Mockito.when(productRequestDtoMapper.productRequestDtoToProductRequest(productRequestDtoStub)).thenReturn(productRequestStub);

		//Dao
		Mockito.when(productRequestDao.save(null)).thenReturn(productRequestStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.post("/productrequests")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(productRequestJson)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 201;
		String[] location = mvcResult.getResponse().getHeader("Location").split("/");
		int pathId = Integer.valueOf(location[location.length-1]);
		
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
		assertEquals("Unexpected location : ", productRequestStub.getId(), pathId);
	}
	
	@Test
	public void controllerAddProductRequestErrorUT() throws Exception {

		productRequestStub.setId(1);
		
		//Dto to Json
		String productRequestJson = objectWriter.writeValueAsString(productRequestDtoStub);
		
		//Mapper
		Mockito.when(productRequestDtoMapper.productRequestDtoToProductRequest(productRequestDtoStub)).thenReturn(productRequestStub);

		//Dao
		Mockito.when(productRequestDao.save(null)).thenReturn(null);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.post("/productrequests")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(productRequestJson)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 204;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void controllerAddProductRequestListUT() throws Exception {

		productRequestStub.setId(1);
		
		//Dto to Json
		String productRequestDtoListJson = objectWriter.writeValueAsString(productRequestDtoList);
		
		//Mapper
		Mockito.when(productRequestDtoMapper.productRequestDtoToProductRequest(productRequestDtoStub)).thenReturn(productRequestStub);
		Mockito.when(productRequestDtoMapper.productRequestToProductRequestDto(productRequestStub)).thenReturn(productRequestDtoStub);
		
		//Dao
		Mockito.when(productRequestDao.save(null)).thenReturn(productRequestStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.post("/productrequests/list")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(productRequestDtoListJson)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 200;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
		assertEquals("No list in response: ", productRequestDtoListJson.replaceAll("\\s+",""), mvcResult.getResponse().getContentAsString());		//replaceAll remove all spaces from jSon format
	}
	
	@Test
	public void controllerAddProductRequestListIAmTeaPotExceptionUT() throws Exception {

		productRequestStub.setId(1);
		
		//Dto to Json
		String productRequestDtoListJson = objectWriter.writeValueAsString(productRequestDtoList);
		
		//Mapper
		Mockito.when(productRequestDtoMapper.productRequestDtoToProductRequest(productRequestDtoStub)).thenReturn(productRequestStub);
		Mockito.when(productRequestDtoMapper.productRequestToProductRequestDto(productRequestStub)).thenReturn(productRequestDtoStub);
		
		//Dao
		Mockito.when(productRequestDao.save(null)).thenThrow(ConstraintViolationException.class);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.post("/productrequests/list")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(productRequestDtoListJson)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 418;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}

}
