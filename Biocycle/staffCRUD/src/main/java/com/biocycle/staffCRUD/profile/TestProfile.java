package com.biocycle.staffCRUD.profile;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.biocycle.staffCRUD.dao.StaffDao;

@Configuration
@Profile("test")
public interface TestProfile extends StaffDao{
	
}
