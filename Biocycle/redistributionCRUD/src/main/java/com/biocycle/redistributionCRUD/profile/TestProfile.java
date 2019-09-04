package com.biocycle.redistributionCRUD.profile;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.biocycle.redistributionCRUD.dao.RedistributionDao;

@Configuration
@Profile("test")
public interface TestProfile extends RedistributionDao{
	
}
