package com.biocycle.giveAwayCRUD.profile;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;

import com.biocycle.giveAwayCRUD.dao.GiveAwayDao;
import com.biocycle.giveAwayCRUD.model.GiveAway;


/**
 * The Interface TestProfile.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Configuration
@Profile("test")
public interface TestProfile extends GiveAwayDao{
	
	/**
	 * Find all active give away.
	 *
	 * @return the optional
	 */
	@Override
	@Query(value = "SELECT * FROM give_away WHERE give_away.collection_date IS NULL",nativeQuery = true)	
	Optional<List<GiveAway>> findAllActiveGiveAway();
	
	/**
	 * Find active give away by date.
	 *
	 * @param date the date
	 * @return the optional
	 */
	@Override
	@Query(value = "SELECT * FROM GIVE_AWAY WHERE GIVE_AWAY.COLLECTION_DATE IS NULL "
			+ "AND GIVE_AWAY.AVAILABLE_TO_BE_COLLECTED_FROM > :date "
			+ "AND GIVE_AWAY.AVAILABLE_TO_BE_COLLECTED_FROM < DATEADD(day,1, :date)"
			,nativeQuery = true)
	Optional<List<GiveAway>> findActiveGiveAwayByDate(Date date);
	
	
}
