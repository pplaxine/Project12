package com.biocycle.productRequestCRUD.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.biocycle.productRequestCRUD.model.ProductRequest;

/**
 * The Interface ProductRequestDao.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@NoRepositoryBean
public interface ProductRequestDao extends JpaRepository<ProductRequest, Integer>{
}
