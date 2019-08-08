package com.biocycle.storageContainerCRUD.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.biocycle.storageContainerCRUD.model.StorageContainer;

@Repository
public interface StorageContainerDao extends JpaRepository<StorageContainer, Integer> {
	
	@Query(value = "SELECT * FROM STORAGE_CONTAINER S WHERE S.IS_AVAILABLE = true", nativeQuery = true )
	Optional<List<StorageContainer>> findAllEmptyStorageContainer();
}
