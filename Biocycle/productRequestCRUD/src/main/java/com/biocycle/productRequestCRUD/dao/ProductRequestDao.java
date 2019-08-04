package com.biocycle.productRequestCRUD.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biocycle.productRequestCRUD.model.ProductRequest;

@Repository
public interface ProductRequestDao extends JpaRepository<ProductRequest, Integer>{
}
