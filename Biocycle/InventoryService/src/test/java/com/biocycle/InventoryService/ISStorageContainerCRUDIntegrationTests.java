package com.biocycle.InventoryService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.biocycle.InventoryService.dto.ProductBatchBeanDto;
import com.biocycle.InventoryService.dto.StorageContainerBeanDto;
import com.biocycle.InventoryService.proxy.ProductBatchCRUDMSProxy;
import com.biocycle.InventoryService.proxy.ProductStorageMSProxy;
import com.biocycle.InventoryService.proxy.StorageContainerCRUDMSProxy;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ISStorageContainerCRUDIntegrationTests {
	
	@Autowired
	MockMvc mockMvc;
	@Autowired
	StorageContainerCRUDMSProxy storageContainerCRUDMSProxy;
	
	@MockBean
	private ProductStorageMSProxy productStorageMSProxy;
	@MockBean
	private ProductBatchCRUDMSProxy productBatchCRUDMSProxy;
	
	@Captor
	private ArgumentCaptor<ProductBatchBeanDto> productBatchBeanDto;
	
	//---- POST 
	@Test
	public void iT01createEntry() throws Exception {
		
		//STUB
		//storageContainerIdList
		List<Integer> storageContainerIdList = new ArrayList<>();
		storageContainerIdList.add(8);
		
		//storageContainerBeanDtoList
		List<StorageContainerBeanDto> storageContainerBeanDtoList = new ArrayList<>();
		 StorageContainerBeanDto storageContainerBeanDto = new StorageContainerBeanDto();
		storageContainerBeanDto.setId(8);
		storageContainerBeanDto.setRowRef(1);
		storageContainerBeanDto.setShelfRef("H");
		storageContainerBeanDto.setLevelRef(1);
		storageContainerBeanDto.setIsAvailable(true);
		storageContainerBeanDtoList.add(storageContainerBeanDto);	
		
		//Mock other Proxies
		when(productStorageMSProxy.getOptimizedSpaceStorageContainers(2)).thenReturn(ResponseEntity.ok(storageContainerIdList));
		when(productBatchCRUDMSProxy.addProductBatch(productBatchBeanDto.capture())).thenReturn(ResponseEntity.ok().build());
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.post("/inventory/2")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(ISTestHelper.getProductBatchDtoPOSTJson())
				).andExpect(status().is2xxSuccessful());
		
		storageContainerBeanDto.setIsAvailable(true);
		storageContainerCRUDMSProxy.updateStorageContainer(storageContainerBeanDto);
	}
}