package com.biocycle.collectionRunCRUD.profile;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.biocycle.collectionRunCRUD.dao.CollectionRunDao;

@Configuration
@Profile("test")
public interface TestProfile extends CollectionRunDao{
	
	
	
}
