package com.biocycle.organisationCRUD.profile;

import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;

import com.biocycle.organisationCRUD.dao.OrganisationDao;
import com.biocycle.organisationCRUD.model.Organisation;

@Configuration
@Profile("prod")
public interface ProdProfile extends OrganisationDao {
	
	@Override
	@Query(value = "SELECT * FROM ORGANISATION O WHERE O.EMAIL_ADDRESS =:email", nativeQuery = true )		
	Optional<Organisation> findOrganisationByEmail(String email); //modif
	
}
