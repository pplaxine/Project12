package com.biocycle.offerCRUD.profile;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.biocycle.offerCRUD.dao.OfferDao;

@Configuration
@Profile("prod")
public interface ProdProfile extends OfferDao {
	
}
