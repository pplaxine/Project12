package com.biocycle.organisationCRUD.profile;

import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;

import com.biocycle.organisationCRUD.dao.OrganisationDao;
import com.biocycle.organisationCRUD.model.Organisation;

// TODO: Auto-generated Javadoc
/**
 * The Interface TestProfile.
 */
@Configuration
@Profile("test")
public interface TestProfile extends OrganisationDao{
	
	/**
	 * Find organisation by email.
	 *
	 * @param email the email
	 * @return the optional
	 */
	@Override
	@Query(value = "SELECT * FROM ORGANISATION O WHERE O.EMAIL_ADDRESS =:email", nativeQuery = true )		
	Optional<Organisation> findOrganisationByEmail(String email);
	
}
