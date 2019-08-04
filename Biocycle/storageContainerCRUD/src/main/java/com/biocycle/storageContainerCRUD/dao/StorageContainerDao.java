package com.biocycle.storageContainerCRUD.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biocycle.storageContainerCRUD.model.StorageContainer;

@Repository
public interface StorageContainerDao extends JpaRepository<StorageContainer, Integer> {
}
