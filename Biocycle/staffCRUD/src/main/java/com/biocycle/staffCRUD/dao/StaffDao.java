package com.biocycle.staffCRUD.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.biocycle.staffCRUD.model.Staff;

/**
 * The Interface StaffDao.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@NoRepositoryBean
public interface StaffDao extends JpaRepository<Staff, Integer> {
	
	/**
	 * Find staff by user name.
	 *
	 * @param name the name
	 * @return the optional
	 */
	Optional<Staff> findStaffByUserName(String name);
}
