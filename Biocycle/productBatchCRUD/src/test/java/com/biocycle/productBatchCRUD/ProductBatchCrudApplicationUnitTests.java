package com.biocycle.productBatchCRUD;

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

import com.biocycle.productBatchCRUD.dao.ProductBatchDao;
import com.biocycle.productBatchCRUD.dto.ProductBatchDto;
import com.biocycle.productBatchCRUD.dto.mapper.ProductBatchDtoMapper;
import com.biocycle.productBatchCRUD.model.ProductBatch;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ProductBatchCrudApplicationUnitTests {

	@Autowired
	MockMvc mockMvc;
	@MockBean
	ProductBatchDao productBatchDao;
	@MockBean
	ProductBatchDtoMapper productBatchDtoMapper;
	
	private ObjectWriter objectWriter;
	private ProductBatch productBatchStub;
	private ProductBatchDto productBatchDtoStub;
	private List<ProductBatch> productBatchList;

	@Before
	public void executeBeforeEach() {
		
		//jSon object writer
		ObjectMapper jsonM = new ObjectMapper();
		jsonM.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		objectWriter = jsonM.writer().withDefaultPrettyPrinter();
		
		//--Stub
		//GiveAway
		productBatchStub = new ProductBatch();
		productBatchStub.setId(1);
		//GiveAwayDtoStub
		productBatchDtoStub = new ProductBatchDto();
		productBatchDtoStub.setId(1);
		//GiveAwayList
		productBatchList = new ArrayList<>();
		productBatchList.add(productBatchStub);
		productBatchList.add(productBatchStub);
	}
	
	//---- GET
	@Test
	public void controllerFindAllProductBatchUT() throws Exception {
		
		//Dao
		Mockito.when(productBatchDao.findAll()).thenReturn(productBatchList);

		//Mapper
		Mockito.when(productBatchDtoMapper.productBatchToProductBatchDto(productBatchStub)).thenReturn(productBatchDtoStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/productbatches")	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 200;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void controllerFindAllProductBatchExceptionFromNullUT() throws Exception {

		productBatchList.clear();
		
		//Dao
		Mockito.when(productBatchDao.findAll()).thenReturn(productBatchList);

		//Mapper
		Mockito.when(productBatchDtoMapper.productBatchToProductBatchDto(productBatchStub)).thenReturn(productBatchDtoStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/productbatches")	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 404;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void controllerFindAllProductBatchExceptionFromEmptyListUT() throws Exception {

		productBatchList.clear();
		
		//Dao
		Mockito.when(productBatchDao.findAll()).thenReturn(productBatchList);

		//Mapper
		Mockito.when(productBatchDtoMapper.productBatchToProductBatchDto(productBatchStub)).thenReturn(productBatchDtoStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/productbatches")	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 404;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void controllerFindProductBatchByIdUT() throws Exception {

		int productBatchId = 1;
		
		//Dao
		Mockito.when(productBatchDao.findById(productBatchId)).thenReturn(Optional.of(productBatchStub));

		//Mapper
		Mockito.when(productBatchDtoMapper.productBatchToProductBatchDto(productBatchStub)).thenReturn(productBatchDtoStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/productbatches/"+ productBatchId)	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 200;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
		
	}
	
	@Test
	public void controllerFindProductBatchByIdExceptionUT() throws Exception {

		int productBatchId = 1;
		
		//Dao
		Mockito.when(productBatchDao.findById(productBatchId)).thenReturn(Optional.empty());
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/productbatches/"+ productBatchId)	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 404;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void controllerGetProductSoonToExpireUT() throws Exception {
		
		//Dao
		Mockito.when(productBatchDao.findTop15ByIsAvailableAndIsAwaitingForCollectionOrderByToBeUsedByAsc(true, false)).thenReturn(productBatchList);

		//Mapper
		Mockito.when(productBatchDtoMapper.productBatchToProductBatchDto(productBatchStub)).thenReturn(productBatchDtoStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/productbatches/soon/expired")	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 200;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
		
	}
	
	@Test
	public void controllerGetProductSoonToExpireExceptionFromNullUT() throws Exception {
		
		//Dao
		Mockito.when(productBatchDao.findTop15ByIsAvailableAndIsAwaitingForCollectionOrderByToBeUsedByAsc(true, false)).thenReturn(null);

		//Mapper
		Mockito.when(productBatchDtoMapper.productBatchToProductBatchDto(productBatchStub)).thenReturn(productBatchDtoStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/productbatches/soon/expired")	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 404;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void controllerGetProductSoonToExpireExceptionFromEmptyListUT() throws Exception {
		
		productBatchList.clear();
		
		//Dao
		Mockito.when(productBatchDao.findTop15ByIsAvailableAndIsAwaitingForCollectionOrderByToBeUsedByAsc(true, false)).thenReturn(productBatchList);

		//Mapper
		Mockito.when(productBatchDtoMapper.productBatchToProductBatchDto(productBatchStub)).thenReturn(productBatchDtoStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/productbatches/soon/expired")	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 404;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	//---- POST
	@Test
	public void controllerAddProductBatchUT() throws Exception {

		productBatchStub.setId(1);
		
		//Dto to Json
		String organisationJson = objectWriter.writeValueAsString(productBatchDtoStub);
		
		//Mapper
		Mockito.when(productBatchDtoMapper.productBatchDtoToProductBatch(productBatchDtoStub)).thenReturn(productBatchStub);

		//Dao
		Mockito.when(productBatchDao.save(null)).thenReturn(productBatchStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.post("/productbatches")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(organisationJson)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 201;
		String[] location = mvcResult.getResponse().getHeader("Location").split("/");
		int pathId = Integer.valueOf(location[location.length-1]);
		
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
		assertEquals("Unexpected location : ", productBatchStub.getId(), pathId);
	}
	
	public void controllerAddProductBatchExceptionUT() throws Exception {

		productBatchStub.setId(1);
		
		//Dto to Json
		String organisationJson = objectWriter.writeValueAsString(productBatchDtoStub);
		
		//Mapper
		Mockito.when(productBatchDtoMapper.productBatchDtoToProductBatch(productBatchDtoStub)).thenReturn(productBatchStub);

		//Dao
		Mockito.when(productBatchDao.save(null)).thenReturn(null);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.post("/productbatches")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(organisationJson)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 404;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	//---- PUT
	@Test
	public void controllerupdateProductBatchIsAwaitingToBeCollectedStatusUT() throws Exception {
		
		int productBatchId = 1;
		productBatchStub.setId(productBatchId);
		
		//Dto to Json
		String organisationJson = objectWriter.writeValueAsString(productBatchDtoStub);

		//Dao
		Mockito.when(productBatchDao.findById(productBatchId)).thenReturn(Optional.of(productBatchStub));
		Mockito.when(productBatchDao.save(productBatchStub)).thenReturn(productBatchStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.put("/productbatches/is-awaiting-for-collection/1/true")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(organisationJson)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 200;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void controllerupdateProductBatchIsAwaitingToBeCollectedStatusExceptionUT() throws Exception {
		
		int productBatchId = 1;
		productBatchStub.setId(productBatchId);
		
		//Dto to Json
		String organisationJson = objectWriter.writeValueAsString(productBatchDtoStub);

		//Dao
		Mockito.when(productBatchDao.findById(productBatchId)).thenReturn(Optional.empty());
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.put("/productbatches/is-awaiting-for-collection/1/true")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(organisationJson)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 204;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}

}
