package com.biocycle.giveAwayCRUD.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.biocycle.giveAwayCRUD.model.GiveAway;

@NoRepositoryBean
public interface GiveAwayDao extends JpaRepository<GiveAway, Integer>{
	
	Optional<List<GiveAway>> findAllActiveGiveAway();
	
	Optional<List<GiveAway>> findActiveGiveAwayByDate(Date date);
	
	Optional<List<GiveAway>> findAllGiveAwayByOrganisationId(int organisationId);
}
