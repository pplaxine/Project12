package com.biocycle.giveAwayCRUD.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biocycle.giveAwayCRUD.model.GiveAway;

@Repository
public interface GiveAwayDao extends JpaRepository<GiveAway, Integer>{
}
