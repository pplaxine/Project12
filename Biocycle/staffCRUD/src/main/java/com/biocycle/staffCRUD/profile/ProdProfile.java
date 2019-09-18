package com.biocycle.staffCRUD.profile;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.biocycle.staffCRUD.dao.StaffDao;

/**
 * The Interface ProdProfile.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Configuration
@Profile("prod")
public interface ProdProfile extends StaffDao {

}
