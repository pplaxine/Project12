package com.biocycle.storageContainerCRUD.controller;

import java.net.URI;
import java.util.Optional;

import org.hibernate.boot.registry.selector.spi.StrategySelectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.biocycle.storageContainerCRUD.dao.StorageContainerDao;
import com.biocycle.storageContainerCRUD.model.StorageContainer;

@RestController
public class StorageContainerController {
	
	@Autowired
	private StorageContainerDao storageContainerDao; 
	
	@GetMapping(value = "/storagecontainers/{id}" )
	public Optional<StorageContainer> findStorageContainerById(@PathVariable int id){
		Optional<StorageContainer> storageContainer = storageContainerDao.findById(id);
		
		if(!storageContainer.isPresent()) {
			throw new StrategySelectionException("storageContainer with" + id + " does not exist.");
		}
		
		return storageContainer;
	}
	
	@DeleteMapping(value = "/storagecontainers/{id}")
	public void deleteStorageContainer(@PathVariable int id) {
		storageContainerDao.deleteById(id);
	}
	
	@PostMapping(value = "/storagecontainers")
	public ResponseEntity<Void> addStorageContainer(@RequestBody StorageContainer storageContainer){
		
		StorageContainer sc = storageContainerDao.save(storageContainer);
		
		if(sc == null) {
			ResponseEntity.noContent().build();
		}
		
		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(sc.getId())
						.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping(value = "/storagecontainers")
	public void updateStorageContainer(@RequestBody StorageContainer storageContainer) {
		storageContainerDao.save(storageContainer);
	}
	
	
	
	
	
	
	
}
