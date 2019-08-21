package com.biocycle.staffCRUD.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biocycle.staffCRUD.model.Staff;

@Repository
public interface StaffDao extends JpaRepository<Staff, Integer> {
	Optional<Staff> findStaffByUserName(String name);
}
