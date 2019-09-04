package com.biocycle.collectionRunCRUD.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.biocycle.collectionRunCRUD.model.CollectionRun;

@NoRepositoryBean
public interface CollectionRunDao extends JpaRepository<CollectionRun, Integer> {
}
