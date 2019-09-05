package com.biocycle.productBatchCRUD.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.biocycle.productBatchCRUD.model.ProductBatch;

@NoRepositoryBean
public interface ProductBatchDao extends JpaRepository<ProductBatch, Integer>{
	
	List<ProductBatch> findTop15ByIsAvailableAndIsAwaitingForCollectionOrderByToBeUsedByAsc(Boolean isAvailable, Boolean isAwaitingForCollection); 
}

