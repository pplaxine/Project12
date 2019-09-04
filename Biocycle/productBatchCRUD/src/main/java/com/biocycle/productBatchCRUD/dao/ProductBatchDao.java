package com.biocycle.productBatchCRUD.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.biocycle.productBatchCRUD.model.ProductBatch;

@NoRepositoryBean
public interface ProductBatchDao extends JpaRepository<ProductBatch, Integer>{
}

