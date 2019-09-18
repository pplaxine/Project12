package com.biocycle.redistributionCRUD.profile;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.biocycle.redistributionCRUD.dao.RedistributionDao;

/**
 * The Interface TestProfile.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Configuration
@Profile("test")
public interface TestProfile extends RedistributionDao{
	
}
