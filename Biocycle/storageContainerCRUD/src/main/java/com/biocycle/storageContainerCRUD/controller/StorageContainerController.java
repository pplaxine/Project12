package com.biocycle.storageContainerCRUD.controller;

import java.net.URI;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	//---- GET 
	
	@GetMapping(value = "/storagecontainers/empty")
	public List<StorageContainerDto> findAllEmptyStorageContainer(){
		Optional<List<StorageContainer>> emptyStorageContainerList = storageContainerDao.findAllEmptyStorageContainer();
		
		if(!emptyStorageContainerList.isPresent()) {
			throw new StorageContainerNotFoundException("No Storage container available in the Warehouse.");
		}
		
		List<StorageContainerDto> emptyStorageContainerDtoList = StorageContainerHelper.entityListToDtoList(emptyStorageContainerList.get(), storageContainerDtoMapper);
		
		return emptyStorageContainerDtoList;
	}
	
	@GetMapping(value = "/storagecontainers/listId")
	@ResponseBody
	public Optional<List<StorageContainerDto>> findStorageContainerFromIdList(@RequestParam("containerIdList") Integer[]storageContainerIdtab){
		
		List<Integer>storageContainerIdList = new ArrayList<Integer>();
		
		for (int sci : storageContainerIdtab) {
			storageContainerIdList.add(sci);
		}
		
		List<StorageContainer> emptyStorageContainerList = storageContainerDao.findAllById(storageContainerIdList);
		
		if(emptyStorageContainerList == null) {
			throw new StorageContainerNotFoundException("No Storage container available in the Warehouse.");
		}
		
		List<StorageContainerDto> emptyStorageContainerDtoList = StorageContainerHelper.entityListToDtoList(emptyStorageContainerList, storageContainerDtoMapper);
		
		return Optional.of(emptyStorageContainerDtoList);
	}
	
	
	@GetMapping(value = "/storagecontainers/{id}" )
	public StorageContainerDto findStorageContainerById(@PathVariable int id){
		Optional<StorageContainer> storageContainer = storageContainerDao.findById(id);
		
		if(!storageContainer.isPresent()) {
			throw new StorageContainerNotFoundException("storageContainer with" + id + " does not exist.");
		}
		
		return storageContainerDtoMapper.storageContainerToStorageContainerDto(storageContainer.get());
	}
	
	//---- DELETE 
	
	@DeleteMapping(value = "/storagecontainers/{id}")
	public void deleteStorageContainer(@PathVariable int id) {
		storageContainerDao.deleteById(id);
	}
	
	//---- POST 
	
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
	
	//---- PUT 
	
	@PutMapping(value = "/storagecontainers")
	public ResponseEntity<Void> updateStorageContainer(@RequestBody StorageContainerDto storageContainerDto) {
		StorageContainer storageContainer = storageContainerDtoMapper.storageContainerDtoToStorageContainer(storageContainerDto);
		StorageContainer sc = storageContainerDao.save(storageContainer);
		if(sc == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/storagecontainers/updatestatus")
	public ResponseEntity<Void> updateStorageContainer(@RequestBody List<StorageContainerDto> storageContainerDtoList ) {
		
		List<StorageContainer> storageContainerList = StorageContainerHelper.dtoListToEntityList(storageContainerDtoList, storageContainerDtoMapper);
		
		for (StorageContainer storageContainer : storageContainerList) {
			StorageContainer sc = storageContainerDao.save(storageContainer);
			if(sc == null) {
				return ResponseEntity.notFound().build();
			}
		}
		
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	
	
	
	
}
