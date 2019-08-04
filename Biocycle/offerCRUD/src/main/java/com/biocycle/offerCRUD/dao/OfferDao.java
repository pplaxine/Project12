package com.biocycle.offerCRUD.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biocycle.offerCRUD.model.Offer;

@Repository
public interface OfferDao extends JpaRepository<Offer, Integer>{
}
