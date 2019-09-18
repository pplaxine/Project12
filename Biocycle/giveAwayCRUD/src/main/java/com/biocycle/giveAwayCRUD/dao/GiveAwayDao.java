package com.biocycle.giveAwayCRUD.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.biocycle.giveAwayCRUD.model.GiveAway;

/**
 * The Interface GiveAwayDao.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@NoRepositoryBean
public interface GiveAwayDao extends JpaRepository<GiveAway, Integer>{
	
	/**
	 * Find all active give away.
	 *
	 * @return the optional
	 */
	Optional<List<GiveAway>> findAllActiveGiveAway();
	
	/**
	 * Find active give away by date.
	 *
	 * @param date the date
	 * @return the optional
	 */
	Optional<List<GiveAway>> findActiveGiveAwayByDate(Date date);
	
	/**
	 * Find all give away by organisation id.
	 *
	 * @param organisationId the organisation id
	 * @return the optional
	 */
	Optional<List<GiveAway>> findAllGiveAwayByOrganisationId(int organisationId);
}
