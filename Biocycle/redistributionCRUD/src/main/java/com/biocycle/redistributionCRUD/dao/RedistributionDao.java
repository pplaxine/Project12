package com.biocycle.redistributionCRUD.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import com.biocycle.redistributionCRUD.model.Redistribution;

@NoRepositoryBean
public interface RedistributionDao extends JpaRepository<Redistribution, Integer> {
	
	Optional<List<Redistribution>> findAllRedistributionByOrganisationId(int organisationId);
	
	@Query(value = "SELECT r FROM Redistribution r WHERE r.isCompleted = false")
	Optional<List<Redistribution>> findAllActiveRedistribution();
	
	
}
