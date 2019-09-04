package com.biocycle.productRequestCRUD.profile;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.biocycle.productRequestCRUD.dao.ProductRequestDao;

@Configuration
@Profile("prod")
public interface ProdProfile extends ProductRequestDao {
	

	
}
