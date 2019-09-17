package com.biocycle.entWebApp;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
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
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.biocycle.entWebApp.dto.ProductBatchBeanDto;
import com.biocycle.entWebApp.dto.StorageContainerBeanDto;
import com.biocycle.entWebApp.proxy.InventoryServiceProxy;
import com.biocycle.entWebApp.proxy.ProductBatchCRUDMSProxy;
import com.biocycle.entWebApp.proxy.StorageContainerCRUDMSProxy;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EWAProductBatchCRUDIntegrationTests {
	
	@Autowired
	MockMvc mockMvc;
	@Autowired
	ProductBatchCRUDMSProxy productBatchCRUDMSProxy;
	
	@MockBean
	StorageContainerCRUDMSProxy storageContainerCRUDMSProxy;
	@MockBean
	InventoryServiceProxy inventoryServiceProxy;
	
	@Captor
	private ArgumentCaptor<Integer[]> containerIdArrayCaptor;
	@Captor
	private ArgumentCaptor<ProductBatchBeanDto> productBatchBeanDtoCaptor;
	@Captor
	private ArgumentCaptor<Integer> numberOfContainerCaptor;
	@Captor
	private ArgumentCaptor<Integer[]> storageContainerArrayCaptor;
	@Captor
	private ArgumentCaptor<List<StorageContainerBeanDto>> storageContainerBeanDtoListCaptor;
	
	//---- Tests 
	@WithMockUser(username = "pme",roles = {"PME"})
	@Test
	public void iT01ProductManagmentManagerCreateProductBatch() throws Exception {
		
		URI location = URI.create("http://blop/foo/test/1");
		
		//Mock 
		when(storageContainerCRUDMSProxy.findStorageContainerFromIdList(containerIdArrayCaptor.capture())).thenReturn(Optional.of(EWATestHelper.getStorageContainerBeanDtoList(false)));
		when(inventoryServiceProxy.createEntry(productBatchBeanDtoCaptor.capture(), numberOfContainerCaptor.capture())).thenReturn(ResponseEntity.created(location).build());
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/pme/entry-forms/create")
								.flashAttr("productBatchBeanDto", EWATestHelper.getProductBatchBeanDto())
				).andExpect(status().is3xxRedirection());
	}
	
	@WithMockUser(username = "pme",roles = {"PME"})
	@Test
	public void iT02ProductManagmentManagerProductBatchList() throws Exception {
	
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/pme/product-batch/list")
				).andExpect(status().is2xxSuccessful());
	}
	
	@WithMockUser(username = "pme",roles = {"PME"})
	@Test
	public void iT03ProductManagmentManagerValidateCollectionOfProductBatch() throws Exception {
		
		//Mock
		when(storageContainerCRUDMSProxy.findStorageContainerFromIdList(storageContainerArrayCaptor.capture())).thenReturn(Optional.of(EWATestHelper.getStorageContainerBeanDtoList(false)));
		when(storageContainerCRUDMSProxy.updateStorageContainer(storageContainerBeanDtoListCaptor.capture())).thenReturn(ResponseEntity.ok().build()); 
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/pme/product-batch/collect/3")
				).andExpect(status().is3xxRedirection());
	}
	
	
}