package com.biocycle.storageContainerCRUD.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.biocycle.storageContainerCRUD.model.StorageContainer;


@NoRepositoryBean
public interface StorageContainerDao extends JpaRepository<StorageContainer, Integer> {
	
	Optional<List<StorageContainer>> findAllEmptyStorageContainer();

}
