package com.biocycle.storageContainerCRUD.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.biocycle.storageContainerCRUD.model.StorageContainer;


/**
 * The Interface StorageContainerDao.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@NoRepositoryBean
public interface StorageContainerDao extends JpaRepository<StorageContainer, Integer> {
	
	/**
	 * Find all empty storage container.
	 *
	 * @return the optional
	 */
	Optional<List<StorageContainer>> findAllEmptyStorageContainer();

}
