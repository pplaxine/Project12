package com.biocycle.staffCRUD.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.biocycle.staffCRUD.model.Staff;

@NoRepositoryBean
public interface StaffDao extends JpaRepository<Staff, Integer> {
	Optional<Staff> findStaffByUserName(String name);
}
