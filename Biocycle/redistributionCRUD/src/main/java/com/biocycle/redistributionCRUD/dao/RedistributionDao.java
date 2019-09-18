package com.biocycle.redistributionCRUD.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import com.biocycle.redistributionCRUD.model.Redistribution;

/**
 * The Interface RedistributionDao.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@NoRepositoryBean
public interface RedistributionDao extends JpaRepository<Redistribution, Integer> {
	
	/**
	 * Find all redistribution by organisation id.
	 *
	 * @param organisationId the organisation id
	 * @return the optional
	 */
	Optional<List<Redistribution>> findAllRedistributionByOrganisationId(int organisationId);
	
	/**
	 * Find all active redistribution.
	 *
	 * @return the optional
	 */
	@Query(value = "SELECT r FROM Redistribution r WHERE r.isCompleted = false")
	Optional<List<Redistribution>> findAllActiveRedistribution();
	
	
}
