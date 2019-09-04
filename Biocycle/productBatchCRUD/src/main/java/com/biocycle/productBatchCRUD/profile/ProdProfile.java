package com.biocycle.productBatchCRUD.profile;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.biocycle.productBatchCRUD.dao.ProductBatchDao;

@Configuration
@Profile("prod")
public interface ProdProfile extends ProductBatchDao {
	

	
}
