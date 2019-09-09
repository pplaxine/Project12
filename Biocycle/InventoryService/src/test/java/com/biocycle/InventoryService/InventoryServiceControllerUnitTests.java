package com.biocycle.InventoryService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.biocycle.InventoryService.bean.ProductBatchBean;
import com.biocycle.InventoryService.dto.ProductBatchBeanDto;
import com.biocycle.InventoryService.dto.ProductBatchDto;
import com.biocycle.InventoryService.dto.StorageContainerBeanDto;
import com.biocycle.InventoryService.dto.mapper.ProductBatchBeanMapper;
import com.biocycle.InventoryService.dto.mapper.ProductBatchDtoMapper;
import com.biocycle.InventoryService.model.ProductBatch;
import com.biocycle.InventoryService.proxy.ProductBatchCRUDMSProxy;
import com.biocycle.InventoryService.proxy.ProductStorageMSProxy;
import com.biocycle.InventoryService.proxy.StorageContainerCRUDMSProxy;
import com.biocycle.InventoryService.service.ProductBatchManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;


@RunWith(SpringRunner.class)
@WebMvcTest
public class InventoryServiceControllerUnitTests {

	@Autowired
	MockMvc mockMvc;
	@MockBean
	private ProductBatchCRUDMSProxy productBatchCRUDMSProxy;
	@MockBean
	private ProductStorageMSProxy productStorageMSProxy;
	@MockBean
	private StorageContainerCRUDMSProxy storageContainerCRUDMSProxy;
	@MockBean
	private ProductBatchDtoMapper productBatchDtoMapper;
	@MockBean
	private ProductBatchBeanMapper productBatchBeanMapper;
	@MockBean
	private ProductBatchManager productBatchManager;
	
	private ObjectWriter objectWriter;
	private ProductBatch productBatch;
	private ProductBatchDto productBatchDto;
	private ProductBatchBean productBatchBean;
	private ProductBatchBeanDto productBatchBeanDto;
	private StorageContainerBeanDto storageContainerBeanDto;
	private List<Integer> storageContainerIdList;
	private List<StorageContainerBeanDto> storageContainerBeanDtoList;
	
	@Before
	public void executeBeforeEach() {
		
		//jSon object writer
		ObjectMapper jsonM = new ObjectMapper();
		jsonM.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		objectWriter = jsonM.writer().withDefaultPrettyPrinter();
		
		//--Stub
		//ProductBatchStub
		productBatch = new ProductBatch();
		productBatch.setId(1);

		//ProductBatchDtoStub
		productBatchDto = new ProductBatchDto();
		productBatchDto.setId(1);
		
		//ProductBatchBeanStub
		productBatchBean = new ProductBatchBean();
		productBatchBean.setId(1);
		
		//ProductBatchStub
		productBatchBeanDto = new ProductBatchBeanDto();
		productBatchBeanDto.setId(1);
		
		//StorageContainerBean
		storageContainerBeanDto = new StorageContainerBeanDto();
		storageContainerBeanDto.setId(1);
		
		//storageContainerIdList
		storageContainerIdList = new ArrayList<>();
		storageContainerIdList.add(1);
		storageContainerIdList.add(2);
		
		//storageContainerBeanDtoList
		storageContainerBeanDtoList = new ArrayList<>();
		storageContainerBeanDtoList.add(storageContainerBeanDto);
		productBatchBean.setId(2);
		storageContainerBeanDtoList.add(storageContainerBeanDto);
		
	}
	
	@Test
	public void createEntryUT() throws Exception {
		
		int numberOfContainer = 2;
		Integer[]containerIdList = {1,2};
		
		String productBatchDtoJsonFormat = objectWriter.writeValueAsString(productBatchDto);
		
		//Mapper
		when(productBatchDtoMapper.productBatchDtoToProductBatch(productBatchDto)).thenReturn(productBatch);
		when(productBatchBeanMapper.productBatchToProductBatchBean(productBatch)).thenReturn(productBatchBean);
		when(productBatchDtoMapper.productBatchBeanToProductBatchBeanDto(productBatchBean)).thenReturn(productBatchBeanDto);
		
		//Proxy
		when(productStorageMSProxy.getOptimizedSpaceStorageContainers(numberOfContainer)).thenReturn(ResponseEntity.ok(storageContainerIdList));
		when(productBatchCRUDMSProxy.addProductBatch(productBatchBeanDto)).thenReturn(ResponseEntity.created(null).build());
		when(storageContainerCRUDMSProxy.getStorageContainers(containerIdList)).thenReturn(Optional.of(storageContainerBeanDtoList));
		
		//Manager
		when(productBatchManager.createProductBatch(productBatchDto, numberOfContainer)).thenReturn(ResponseEntity.ok().build());
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.post("/inventory/" + numberOfContainer)	
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(productBatchDtoJsonFormat)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 200;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}

}
