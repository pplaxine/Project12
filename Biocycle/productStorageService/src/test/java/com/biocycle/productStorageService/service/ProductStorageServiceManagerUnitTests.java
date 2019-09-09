package com.biocycle.productStorageService.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.biocycle.productStorageService.bean.StorageContainerBean;
import com.biocycle.productStorageService.exception.OutOfStorageContainerException;
import com.biocycle.productStorageService.proxy.StorageContainerCRUDMSProxy;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProductStorageManager.class})
public class ProductStorageServiceManagerUnitTests {

	@SpyBean
	ProductStorageManager productStorageManager;
	@MockBean
	StorageContainerCRUDMSProxy storageContainerCRUDMSProxy;

	private StorageContainerBean storageContainerBean;
	private List<StorageContainerBean> storageContainerBeanList;
	private int numberOfContainer;
	private Map<Integer, Map<Integer, List<StorageContainerBean>>> organizedSetOfStorageContainerBeanList;
	private String[] shelfRef = {"A","B","C","D","E","F","G","H","I","J"};
	
	@Before
	public void executeBeforeEach() {

		//--STUB 
		//StorageContainerBean
		storageContainerBean = new StorageContainerBean();
		storageContainerBean.setId(1);
		//StorageContainerBeanList
		storageContainerBeanList = new ArrayList<>();
		for (int i = 0; i < 30; i++) {
			storageContainerBean = new StorageContainerBean();
			storageContainerBean.setId(i+1);
			storageContainerBean.setRowRef(1);
			if(i<10) {
				storageContainerBean.setLevelRef(1);
				storageContainerBean.setShelfRef(shelfRef[i]);
			}
			if(i>=10 && i<20) {
				storageContainerBean.setLevelRef(2);
				storageContainerBean.setShelfRef(shelfRef[i-10]);
			}
			if(i>=20) {
				storageContainerBean.setLevelRef(3);
				storageContainerBean.setShelfRef(shelfRef[i-20]);
			}
			
			storageContainerBean.setIsAvailable(true);
			storageContainerBeanList.add(storageContainerBean);
		}
		
	}
	
	@Test
	public void toIdListUT() {
		List<Integer> storageContainerBeanIdList = productStorageManager.toIdList(storageContainerBeanList);
		assertEquals("storageContainerBeanList and storageContainerBeanIdList have different values", storageContainerBeanList.size(), storageContainerBeanIdList.size());
	}
	
	@Test
	public void findRandomContainerSuitUT() {
		
		//All storages with even id in the list set unavailable  
		for (StorageContainerBean storageContainerBean : storageContainerBeanList) {
			if(storageContainerBean.getId()%2 == 0) {
				storageContainerBean.setIsAvailable(false);
			}
		}
		
		numberOfContainer = 2;
		
		List<StorageContainerBean> RandomStorageContainerSuit = productStorageManager.findRandomContainerSuit(Optional.of(storageContainerBeanList), numberOfContainer);
		assertEquals("Number of required storageContainer and Number of storageContainer given are not equal ", numberOfContainer, RandomStorageContainerSuit.size());
	}
	
	@Test(expected = OutOfStorageContainerException.class)
	public void findRandomContainerSuitExceptionUT() {
		
		int numberOfContainer = storageContainerBeanList.size() +1;
		
		productStorageManager.findRandomContainerSuit(Optional.of(storageContainerBeanList), numberOfContainer);
	}
	
	@Test
	public void createSetOfStoreContainerBeanListUT() {
		
		int totalRows = 1;
		int totalLevs = 3;
		
		organizedSetOfStorageContainerBeanList = productStorageManager.createSetOfStoreContainerBeanList(totalRows, totalLevs);
		
		for ( Map<Integer, List<StorageContainerBean>> storageContainerLevMap : organizedSetOfStorageContainerBeanList.values()) {
			assertTrue(storageContainerLevMap.size() == totalLevs);
		}
		
		assertTrue(organizedSetOfStorageContainerBeanList.size() == totalRows);
	}
	
	@Test
	public void storeContainerBeanDispatcherUT() {
		
		int numberOfRow = 0;
		int numberOfLev = 0; 
		
		for (StorageContainerBean scb : storageContainerBeanList) {
			if(scb.getRowRef()>numberOfRow) {
				numberOfRow++;
			}
			if(scb.getLevelRef()>numberOfLev) {
				numberOfLev++;
			}
		}
		organizedSetOfStorageContainerBeanList = productStorageManager.createSetOfStoreContainerBeanList(numberOfRow, numberOfLev);
		
		Map<Integer, Map<Integer, List<StorageContainerBean>>> setOfOrganizedscbList = productStorageManager.StoreContainerBeanDispatcher(Optional.of(storageContainerBeanList), organizedSetOfStorageContainerBeanList);
		assertEquals("Unexpected number of row",numberOfRow, setOfOrganizedscbList.values().size());
		for (Map<Integer,List<StorageContainerBean>> row : setOfOrganizedscbList.values()) {
			assertEquals("unexpected number of lev", numberOfLev, row.values().size());
		}
		
	}
	
	@Test
	public void findOptimalContainerSuit() {
		
		numberOfContainer = 2;
		organizedSetOfStorageContainerBeanList = productStorageManager.StoreContainerBeanDispatcher(Optional.of(storageContainerBeanList), productStorageManager.createSetOfStoreContainerBeanList(1, 3));
		
		List<StorageContainerBean> storageContainerList = productStorageManager.findOptimalContainerSuit(organizedSetOfStorageContainerBeanList, numberOfContainer);
		assertEquals("Unexpected number of containers : ", numberOfContainer, storageContainerList.size());
	}
	
	@Test
	public void getOptimizedStorageContainerSpace() {
		
		int numberOfContainer = 2;
		
		//proxy
		when(storageContainerCRUDMSProxy.findEmptyStorageContainer()).thenReturn(Optional.of(storageContainerBeanList));
		
		ResponseEntity<List<Integer>> resp = productStorageManager.getOptimizedStorageContainerSpace(numberOfContainer);
		
		//assert 
		assertTrue("Unexpected number of containers", resp.getBody().size() == numberOfContainer);
	}
	
	

}
