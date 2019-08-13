package com.biocycle.giveAwayCRUD.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.biocycle.giveAwayCRUD.model.GiveAway;

@Repository
public interface GiveAwayDao extends JpaRepository<GiveAway, Integer>{
	
	@Query(value = "SELECT * FROM GIVE_AWAY WHERE GIVE_AWAY.COLLECTION_DATE IS NULL",nativeQuery = true)
	Optional<List<GiveAway>> findAllActiveGiveAway();
	
	@Query(value = "SELECT * FROM GIVE_AWAY WHERE GIVE_AWAY.COLLECTION_DATE IS NULL "
			+ "AND GIVE_AWAY.AVAILABLE_TO_BE_COLLECTED_FROM > :date "
			+ "AND GIVE_AWAY.AVAILABLE_TO_BE_COLLECTED_FROM < DATEADD(day,1, :date)"
			,nativeQuery = true)
	Optional<List<GiveAway>> findActiveGiveAwayByDate(Date date);
}
