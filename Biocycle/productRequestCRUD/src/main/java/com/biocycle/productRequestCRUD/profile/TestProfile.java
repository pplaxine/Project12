package com.biocycle.productRequestCRUD.profile;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.biocycle.productRequestCRUD.dao.ProductRequestDao;

/**
 * The Interface TestProfile.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Configuration
@Profile("test")
public interface TestProfile extends ProductRequestDao{
	
}
