package com.biocycle.collectionRunCRUD.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biocycle.collectionRunCRUD.model.CollectionRun;

@Repository
public interface CollectionRunDao extends JpaRepository<CollectionRun, Integer> {
}
