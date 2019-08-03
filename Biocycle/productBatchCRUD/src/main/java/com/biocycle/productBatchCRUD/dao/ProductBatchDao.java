package com.biocycle.productBatchCRUD.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biocycle.productBatchCRUD.model.ProductBatch;

@Repository
public interface ProductBatchDao extends JpaRepository<ProductBatch, Integer>{
}

