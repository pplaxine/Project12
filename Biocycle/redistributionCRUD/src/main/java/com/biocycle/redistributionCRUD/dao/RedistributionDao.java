package com.biocycle.redistributionCRUD.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biocycle.redistributionCRUD.model.Redistribution;

@Repository
public interface RedistributionDao extends JpaRepository<Redistribution, Integer> {
	
	Optional<List<Redistribution>> findAllRedistributionByOrganisationId(int organisationId);
}
