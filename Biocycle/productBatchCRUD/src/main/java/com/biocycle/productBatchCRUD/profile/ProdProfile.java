package com.biocycle.productBatchCRUD.profile;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.biocycle.productBatchCRUD.dao.ProductBatchDao;

/**
 * The Interface ProdProfile.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Configuration
@Profile("prod")
public interface ProdProfile extends ProductBatchDao {
	

	
}
