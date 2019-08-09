package com.biocycle.storageContainerCRUD.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import com.biocycle.storageContainerCRUD.dto.StorageContainerDto;
import com.biocycle.storageContainerCRUD.dto.mapper.StorageContainerDtoMapper;
import com.biocycle.storageContainerCRUD.exception.StorageContainerNotFoundException;
import com.biocycle.storageContainerCRUD.helper.StorageContainerHelper;
import com.biocycle.storageContainerCRUD.model.StorageContainer;

@RestController
public class StorageContainerController {
	
	@Autowired
	private StorageContainerDao storageContainerDao; 
	@Autowired
	private StorageContainerDtoMapper storageContainerDtoMapper; 
	
	@GetMapping(value = "/storagecontainers/empty")
	public List<StorageContainerDto> findAllEmptyStorageContainer(){
		Optional<List<StorageContainer>> emptyStorageContainerList = storageContainerDao.findAllEmptyStorageContainer();
		
		if(!emptyStorageContainerList.isPresent()) {
			throw new StorageContainerNotFoundException("No Storage container available in the Warehouse.");
		}
		
		List<StorageContainerDto> emptyStorageContainerDtoList = StorageContainerHelper.entityListToDtoList(emptyStorageContainerList.get(), storageContainerDtoMapper);
		
		return emptyStorageContainerDtoList;
	}
	
	
	@GetMapping(value = "/storagecontainers/{id}" )
	public StorageContainerDto findStorageContainerById(@PathVariable int id){
		Optional<StorageContainer> storageContainer = storageContainerDao.findById(id);
		
		if(!storageContainer.isPresent()) {
			throw new StorageContainerNotFoundException("storageContainer with" + id + " does not exist.");
		}
		
		return storageContainerDtoMapper.storageContainerToStorageContainerDto(storageContainer.get());
	}
	
	@DeleteMapping(value = "/storagecontainers/{id}")
	public void deleteStorageContainer(@PathVariable int id) {
		storageContainerDao.deleteById(id);
	}
	
	@PostMapping(value = "/storagecontainers")
	public ResponseEntity<Void> addStorageContainer(@RequestBody StorageContainerDto storageContainerDto){
		
		StorageContainer storageContainer = storageContainerDtoMapper.storageContainerDtoToStorageContainer(storageContainerDto);
		
		StorageContainer sc = storageContainerDao.save(storageContainer);
		
		if(sc == null) {
			return ResponseEntity.noContent().build();
		}
		
		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(sc.getId())
						.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping(value = "/storagecontainers")
	public void updateStorageContainer(@RequestBody StorageContainerDto storageContainerDto) {
		StorageContainer storageContainer = storageContainerDtoMapper.storageContainerDtoToStorageContainer(storageContainerDto);
		storageContainerDao.save(storageContainer);
	}
	
	
	
	
	
	
	
}
