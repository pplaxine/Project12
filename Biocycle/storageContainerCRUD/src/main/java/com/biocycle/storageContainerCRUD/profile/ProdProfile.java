package com.biocycle.storageContainerCRUD.profile;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;

import com.biocycle.storageContainerCRUD.dao.StorageContainerDao;
import com.biocycle.storageContainerCRUD.model.StorageContainer;

@Configuration
@Profile("prod")
public interface ProdProfile extends StorageContainerDao {
	
	@Override
	@Query(value = "SELECT * FROM storage_container_crud.storage_container s WHERE s.is_available = true", nativeQuery = true )
	Optional<List<StorageContainer>> findAllEmptyStorageContainer();
}
