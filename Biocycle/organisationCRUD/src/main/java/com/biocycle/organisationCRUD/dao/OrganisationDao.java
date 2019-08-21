package com.biocycle.organisationCRUD.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.biocycle.organisationCRUD.model.Organisation;

@Repository
public interface OrganisationDao extends JpaRepository<Organisation, Integer> {
	
	@Query(value = "SELECT * FROM ORGANISATION O WHERE O.EMAIL_ADDRESS =:email", nativeQuery = true )		//modif for organisation by email
	Optional<Organisation> findOrganisationByEmail(String email);
	
	Optional<List<Organisation>> findAllOrganisationByIsValidated(boolean validated);
}
