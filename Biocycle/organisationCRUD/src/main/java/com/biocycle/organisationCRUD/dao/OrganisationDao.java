package com.biocycle.organisationCRUD.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biocycle.organisationCRUD.model.Organisation;

@Repository
public interface OrganisationDao extends JpaRepository<Organisation, Integer> {
}
