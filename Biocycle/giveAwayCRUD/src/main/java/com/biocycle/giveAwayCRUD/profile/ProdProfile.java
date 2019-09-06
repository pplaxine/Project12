package com.biocycle.giveAwayCRUD.profile;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;

import com.biocycle.giveAwayCRUD.dao.GiveAwayDao;
import com.biocycle.giveAwayCRUD.model.GiveAway;

@Configuration
@Profile("prod")
public interface ProdProfile extends GiveAwayDao {
	
	@Override
	@Query(value = "SELECT * FROM give_away_crud.give_away WHERE give_away_crud.give_away.collection_date IS NULL",nativeQuery = true)		//with schema
	Optional<List<GiveAway>> findAllActiveGiveAway();
	
	@Override
	@Query(value = "SELECT * FROM give_away_crud.give_away WHERE give_away_crud.give_away.collection_date IS NULL "
			+ "AND give_away_crud.give_away.available_to_be_collected_from > :date "
			+ "AND give_away_crud.give_away.available_to_be_collected_from < ( to_timestamp(:date,'YYYY-MM-DD HH24:MI:SS') + INTERVAL '1 day') "
			,nativeQuery = true)
	Optional<List<GiveAway>> findActiveGiveAwayByDate(Date date);
	
}