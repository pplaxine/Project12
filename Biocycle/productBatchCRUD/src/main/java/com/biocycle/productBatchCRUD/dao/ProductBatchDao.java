package com.biocycle.productBatchCRUD.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.biocycle.productBatchCRUD.model.ProductBatch;

/**
 * The Interface ProductBatchDao.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@NoRepositoryBean
public interface ProductBatchDao extends JpaRepository<ProductBatch, Integer>{
	
	/**
	 * Find top 15 by is available and is awaiting for collection order by to be used by asc.
	 *
	 * @param isAvailable the is available
	 * @param isAwaitingForCollection the is awaiting for collection
	 * @return the list
	 */
	List<ProductBatch> findTop15ByIsAvailableAndIsAwaitingForCollectionOrderByToBeUsedByAsc(Boolean isAvailable, Boolean isAwaitingForCollection); 
}

