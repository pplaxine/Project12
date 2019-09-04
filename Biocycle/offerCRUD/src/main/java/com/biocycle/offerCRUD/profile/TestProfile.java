package com.biocycle.offerCRUD.profile;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.biocycle.offerCRUD.dao.OfferDao;

@Configuration
@Profile("test")
public interface TestProfile extends OfferDao{

}
