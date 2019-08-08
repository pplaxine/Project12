package com.biocycle.productStorageService.bean.controller.service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biocycle.productStorageService.bean.StorageContainerBean;
import com.biocycle.productStorageService.bean.controller.proxy.StorageContainerCRUDMSProxy;

@Service
public class ProductStorageManager {
	
	@Autowired
	StorageContainerCRUDMSProxy storageContainerCRUDMSProxy;
	
	public Optional<List<Integer>> getOptimizedStorageContainerSpace(int numberOfContainer){
		Optional<List<StorageContainerBean>> emptyStorageContainerList =  storageContainerCRUDMSProxy.findEmptyStorageContainer();
		if(!emptyStorageContainerList.isPresent()) {
			//exception ...
		}
		
		//find shelf / row / lev size  
		String lastEmptyShelf = "A";
		int lastRow = 1;
		int lastLev = 1;
		
		for (StorageContainerBean  scb : emptyStorageContainerList.get()) {
			String shelfRef = scb.getShelfRef();
			int rowRef = scb.getRowRef();
			int levRef = scb.getLevelRef();
			
			lastEmptyShelf = shelfRef.compareTo(lastEmptyShelf) > 0 ? shelfRef: lastEmptyShelf ;
			lastRow = rowRef > lastRow ? rowRef : lastRow;
			lastLev  = levRef > lastLev ? levRef : lastLev;
		}

		
		//create set of List<storageContainerBean> organize by row and level  
//		Map<Integer, Map<Integer, List<StorageContainerBean>>> rowMap = new HashMap<>();
//		
//		for (int i = 0; i < lastRow; i++) {
//			Map<Integer, List<StorageContainerBean>> levMap = new HashMap<>();
//			for (int j = 0; j < lastLev; j++) {
//				List<StorageContainerBean> shelfList = new ArrayList<>();
//				levMap.put(j+1, shelfList);
//			}
//			rowMap.put(i+1, levMap);
//		}
		
//		//Dispatch empty StorageContainerBean amonsgt those lists
//		for (StorageContainerBean  scb : emptyStorageContainerList.get()) {
//			rowMap.get(scb.getRowRef()).get(scb.getLevelRef()).add(scb);
//		}
//		
		
//		//find the first suit of n empty StorageContainerBean
//		setOfFilledList.forEach((k,v)-> {
//			v.forEach((r,m) -> {
//				Collections.sort(m);	//sort list alphabeticaly (shelfRef) 
//				List<StorageContainerBean> finalList = new ArrayList<>();
//				int i = 0;
//				while(finalList.size() < numberOfContainer || i < m.size()) {
//					m.forEach( s -> { 		// s represent each element of list<StoreContainerBean>
//						
//						if(!finalList.isEmpty()) {
//							StorageContainerBean previousStorageContainerBean = finalList.get(finalList.size() -1);			
//							int AsciiValueOfShelfRefPreviousBean = (int)previousStorageContainerBean.getShelfRef().charAt(0);	
//							int asciiValueOfShelfRefActualBean = (int)s.getShelfRef().charAt(0);								
//							
//							if(AsciiValueOfShelfRefPreviousBean != asciiValueOfShelfRefActualBean-1) {
//								finalList.clear();
//							}
//							finalList.add(s);
//							
//						}else {
//							finalList.add(s);
//						}
//					});
//					i++;
//				}
//			});
//		});
		
		
		Map<Integer, Map<Integer, List<StorageContainerBean>>> setOfOrganizedEmptyList = createSetOfStoreContainerBeanList(lastRow, lastLev);
		Map<Integer, Map<Integer, List<StorageContainerBean>>> setOfFilledList = StoreContainerBeanDispatcher(emptyStorageContainerList, setOfOrganizedEmptyList);
		List<StorageContainerBean> optimalSpace = (findOptimalContainerSuit(setOfFilledList, numberOfContainer));
		optimalSpace = optimalSpace != null ? findOptimalContainerSuit(setOfFilledList, numberOfContainer) 
											: findRandomContainerSuit(emptyStorageContainerList, numberOfContainer);
		
		
		
		return Optional.of(toIdList(optimalSpace));		
	}
	
	//UTILITY METHHOD
	
	//create set of List<storageContainerBean> organize by row and level
	private Map<Integer, Map<Integer, List<StorageContainerBean>>> createSetOfStoreContainerBeanList(int totalRows, int totalLevs){
		Map<Integer, Map<Integer, List<StorageContainerBean>>> rowMap = new HashMap<>();
		
		for (int i = 0; i < totalRows; i++) {
			Map<Integer, List<StorageContainerBean>> levMap = new HashMap<>();
			for (int j = 0; j < totalLevs; j++) {
				List<StorageContainerBean> shelfList = new ArrayList<>();
				levMap.put(j+1, shelfList);
			}
			rowMap.put(i+1, levMap);
		}
		return rowMap;
	}
	
	//Dispatch empty StorageContainerBean amonsgt those lists
	private Map<Integer, Map<Integer, List<StorageContainerBean>>> StoreContainerBeanDispatcher(Optional<List<StorageContainerBean>> scbList, Map<Integer, Map<Integer, List<StorageContainerBean>>> setOfOrganizedscbList){
		for (StorageContainerBean  scb : scbList.get()) {
			setOfOrganizedscbList.get(scb.getRowRef()).get(scb.getLevelRef()).add(scb);
		}
		return setOfOrganizedscbList;
	}
	
	//find the best suit of n empty StorageContainerBean
	private List<StorageContainerBean> findOptimalContainerSuit(Map<Integer, Map<Integer, List<StorageContainerBean>>> organizedSetOfStorageContainerBeanList, int numberOfContainer){
		
		Map<Integer, List<StorageContainerBean>> allPossibleSpace = new TreeMap<>();
		
		organizedSetOfStorageContainerBeanList.forEach((k,v)-> {
			v.forEach((r,m) -> {
				Collections.sort(m);	//sort list alphabeticaly (shelfRef) 
				List<StorageContainerBean> finalList = new ArrayList<>();
				int i = 0;
				while(finalList.size() < numberOfContainer && i < m.size()) {
					m.forEach( s -> { 		// s represent each element of list<StoreContainerBean>
						if(finalList.size() < numberOfContainer) {
							if(!finalList.isEmpty()) {
								StorageContainerBean previousStorageContainerBean = finalList.get(finalList.size() -1);			
								int AsciiValueOfShelfRefPreviousBean = (int)previousStorageContainerBean.getShelfRef().charAt(0);	
								int asciiValueOfShelfRefActualBean = (int)s.getShelfRef().charAt(0);								
								
								if(AsciiValueOfShelfRefPreviousBean != asciiValueOfShelfRefActualBean-1) {
									finalList.clear();
								}
								finalList.add(s);
								
							}else {
								finalList.add(s);
							}
						}
					});
					i++;
				}
				
				if(finalList.size() == numberOfContainer) {				// if full list add to possible space + rowRef as id  
					allPossibleSpace.put(finalList.get(0).getRowRef(), finalList);	
				}
			});
		});
		return allPossibleSpace.isEmpty()? null: allPossibleSpace.get(1);
	}
	
	//return a random set of container space 
	private List<StorageContainerBean> findRandomContainerSuit(Optional<List<StorageContainerBean>> emptyContainerList, int numberOfContainer ){
		
		List<StorageContainerBean> randomSpace = new ArrayList<>();
		for (int i = 0; i < numberOfContainer; i++) {
			randomSpace.add(emptyContainerList.get().get(i));
		}
		return randomSpace;
	}
	
	//convert storageContainerBean list in idList 
	private List<Integer> toIdList(List<StorageContainerBean> storageContainerBeanList){
		List<Integer> storageContainerBeanIdList = new ArrayList<>();
		for (StorageContainerBean scb : storageContainerBeanList) {
			storageContainerBeanIdList.add(scb.getId());
		}
		return storageContainerBeanIdList;
	}
	
	
}
