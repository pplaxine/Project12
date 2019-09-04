package com.biocycle.storageContainerCRUD.profile;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;

import com.biocycle.storageContainerCRUD.dao.StorageContainerDao;
import com.biocycle.storageContainerCRUD.model.StorageContainer;

@Configuration
@Profile("test")
public interface TestProfile extends StorageContainerDao{
	
	@Override
	@Query(value = "SELECT * FROM STORAGE_CONTAINER S WHERE S.IS_AVAILABLE = true", nativeQuery = true )
	Optional<List<StorageContainer>> findAllEmptyStorageContainer();
}
