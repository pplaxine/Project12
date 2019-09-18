package com.biocycle.offerCRUD.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.biocycle.offerCRUD.model.Offer;

/**
 * The Interface OfferDao.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@NoRepositoryBean
public interface OfferDao extends JpaRepository<Offer, Integer>{
}
