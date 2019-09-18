package com.biocycle.productStorageService.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.biocycle.productStorageService.bean.StorageContainerBean;
import com.biocycle.productStorageService.exception.OutOfStorageContainerException;
import com.biocycle.productStorageService.proxy.StorageContainerCRUDMSProxy;

/**
 * The Class ProductStorageManager.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Service
public class ProductStorageManager {
	
	/** The storage container CRUDMS proxy. */
	@Autowired
	StorageContainerCRUDMSProxy storageContainerCRUDMSProxy;
	
	/**
	 * Gets the optimized storage container space.
	 *
	 * @param numberOfContainer the number of container
	 * @return the optimized storage container space
	 */
	public ResponseEntity<List<Integer>> getOptimizedStorageContainerSpace(int numberOfContainer){
		
		//get all empty storageContainer
		Optional<List<StorageContainerBean>> emptyStorageContainerList =  storageContainerCRUDMSProxy.findEmptyStorageContainer();
		
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
		
		Map<Integer, Map<Integer, List<StorageContainerBean>>> setOfOrganizedEmptyList = createSetOfStoreContainerBeanList(lastRow, lastLev);
		Map<Integer, Map<Integer, List<StorageContainerBean>>> setOfFilledList = StoreContainerBeanDispatcher(emptyStorageContainerList, setOfOrganizedEmptyList);
		List<StorageContainerBean> optimalSpace = (findOptimalContainerSuit(setOfFilledList, numberOfContainer));
		optimalSpace = optimalSpace != null ? findOptimalContainerSuit(setOfFilledList, numberOfContainer) 
											: findRandomContainerSuit(emptyStorageContainerList, numberOfContainer);

		return ResponseEntity.of(Optional.of(toIdList(optimalSpace)));		
	}
	
	//UTILITY METHHOD
	
	/**
	 * Creates the set of store container bean list.
	 *
	 * @param totalRows the total rows
	 * @param totalLevs the total levs
	 * @return the map
	 */
	//create set of List<storageContainerBean> organize by row and level
	protected Map<Integer, Map<Integer, List<StorageContainerBean>>> createSetOfStoreContainerBeanList(int totalRows, int totalLevs){
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
	
	/**
	 * Store container bean dispatcher.
	 *
	 * @param scbList the scb list
	 * @param setOfOrganizedscbList the set of organizedscb list
	 * @return the map
	 */
	//Dispatch empty StorageContainerBean amonsgt those lists
	protected Map<Integer, Map<Integer, List<StorageContainerBean>>> StoreContainerBeanDispatcher(Optional<List<StorageContainerBean>> scbList, Map<Integer, Map<Integer, List<StorageContainerBean>>> setOfOrganizedscbList){
		for (StorageContainerBean  scb : scbList.get()) {
			setOfOrganizedscbList.get(scb.getRowRef()).get(scb.getLevelRef()).add(scb);
		}
		return setOfOrganizedscbList;
	}
	
	/**
	 * Find optimal container suit.
	 *
	 * @param organizedSetOfStorageContainerBeanList the organized set of storage container bean list
	 * @param numberOfContainer the number of container
	 * @return the list
	 */
	//find the best suit of n empty StorageContainerBean
	protected List<StorageContainerBean> findOptimalContainerSuit(Map<Integer, Map<Integer, List<StorageContainerBean>>> organizedSetOfStorageContainerBeanList, int numberOfContainer){
		
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
	
	/**
	 * Find random container suit.
	 *
	 * @param emptyContainerList the empty container list
	 * @param numberOfContainer the number of container
	 * @return the list
	 */
	//return a random set of container space 
	protected List<StorageContainerBean> findRandomContainerSuit(Optional<List<StorageContainerBean>> emptyContainerList, int numberOfContainer ){
		
		if(numberOfContainer > emptyContainerList.get().size()) {		
			throw new OutOfStorageContainerException("There is not enough free storage container space available for " + numberOfContainer + " more containers.");
		}
		
		List<StorageContainerBean> randomSpace = new ArrayList<>();
		for (int i = 0; i < numberOfContainer; i++) {
			randomSpace.add(emptyContainerList.get().get(i));
		}
		return randomSpace;
	}
	
	/**
	 * To id list.
	 *
	 * @param storageContainerBeanList the storage container bean list
	 * @return the list
	 */
	//convert storageContainerBean list in idList 
	protected List<Integer> toIdList(List<StorageContainerBean> storageContainerBeanList){
		List<Integer> storageContainerBeanIdList = new ArrayList<>();
		for (StorageContainerBean scb : storageContainerBeanList) {
			storageContainerBeanIdList.add(scb.getId());
		}
		return storageContainerBeanIdList;
	}
	
	
}
