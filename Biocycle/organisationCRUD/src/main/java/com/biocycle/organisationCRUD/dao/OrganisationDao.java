package com.biocycle.organisationCRUD.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.biocycle.organisationCRUD.model.Organisation;

@NoRepositoryBean
public interface OrganisationDao extends JpaRepository<Organisation, Integer> {
		
	Optional<Organisation> findOrganisationByEmail(String email);
	
	Optional<List<Organisation>> findAllOrganisationByIsValidated(boolean validated);
}
