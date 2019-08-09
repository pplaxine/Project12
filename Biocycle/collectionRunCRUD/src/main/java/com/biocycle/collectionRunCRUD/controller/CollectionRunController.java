package com.biocycle.collectionRunCRUD.controller;

import java.net.URI;
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

import com.biocycle.collectionRunCRUD.dao.CollectionRunDao;
import com.biocycle.collectionRunCRUD.dto.CollectionRunDto;
import com.biocycle.collectionRunCRUD.dto.mapper.CollectionRunDtoMapper;
import com.biocycle.collectionRunCRUD.exception.CollectionRunNotFoundException;
import com.biocycle.collectionRunCRUD.model.CollectionRun;

@RestController
public class CollectionRunController {
	
	@Autowired
	private CollectionRunDao collectionRunDao;
	@Autowired
	private CollectionRunDtoMapper collectionRunDtoMapper;
	
	@GetMapping(value = "/collectionruns/{id}")
	public CollectionRunDto findCollectionRunById(@PathVariable int id){
		
		Optional<CollectionRun> collectionRun = collectionRunDao.findById(id);
		
		if(!collectionRun.isPresent()) {
			throw new CollectionRunNotFoundException("collectionRun with id: " + id + " does not exist.");
		}
		
		return collectionRunDtoMapper.collectionRunToCollectionRunDto(collectionRun.get()); 
	}
	
	@DeleteMapping(value = "/collectionruns/{id}")
	public void deleteCollectionRun(@PathVariable int id) {
		collectionRunDao.deleteById(id);
	}
	
	@PostMapping(value = "/collectionruns")
	public ResponseEntity<Void> addCollectionRun(@RequestBody CollectionRunDto collectionRunDto){
		
		CollectionRun collectionRun = collectionRunDtoMapper.collectionRunDtoToCollectionRun(collectionRunDto);
		
		CollectionRun cr = collectionRunDao.save(collectionRun);
		
		if(cr == null) {
			return ResponseEntity.noContent().build();
		}
		
		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(cr.getId())
						.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping(value = "/collectionruns")
	public void updateCollectionRun(@RequestBody CollectionRunDto collectionRunDto) {
		CollectionRun collectionRun = collectionRunDtoMapper.collectionRunDtoToCollectionRun(collectionRunDto);
		collectionRunDao.save(collectionRun);
	}
	
}
