package com.biocycle.organisationCRUD.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.biocycle.organisationCRUD.model.Organisation;

// TODO: Auto-generated Javadoc
/**
 * The Interface OrganisationDao.
 */
@NoRepositoryBean
public interface OrganisationDao extends JpaRepository<Organisation, Integer> {
		
	/**
	 * Find organisation by email.
	 *
	 * @param email the email
	 * @return the optional
	 */
	Optional<Organisation> findOrganisationByEmail(String email);
	
	/**
	 * Find all organisation by is validated.
	 *
	 * @param validated the validated
	 * @return the optional
	 */
	Optional<List<Organisation>> findAllOrganisationByIsValidated(boolean validated);
}
