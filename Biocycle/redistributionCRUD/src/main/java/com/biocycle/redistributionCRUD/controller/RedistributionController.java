package com.biocycle.redistributionCRUD.controller;

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

import com.biocycle.redistributionCRUD.dao.RedistributionDao;
import com.biocycle.redistributionCRUD.dto.RedistributionDto;
import com.biocycle.redistributionCRUD.dto.mapper.RedistributionDtoMapper;
import com.biocycle.redistributionCRUD.exception.RedistributionNotFoundException;
import com.biocycle.redistributionCRUD.helper.RedistributionHelper;
import com.biocycle.redistributionCRUD.model.Redistribution;

@RestController
public class RedistributionController {

	@Autowired
	private RedistributionDao redistributionDao; 
	
	@Autowired
	private RedistributionDtoMapper redistributionDtoMapper;
	
	@GetMapping(value = "/redistributions/{id}")
	public ResponseEntity<RedistributionDto> findRedistributionById(@PathVariable int id){
		
		Optional<Redistribution> redistribution = redistributionDao.findById(id);
		
		if(!redistribution.isPresent()) {
			throw new RedistributionNotFoundException("redistribution with id: " + id + " does not exist.");
		}
		
		RedistributionDto redistributionDto = redistributionDtoMapper.RedistribtionToRedistributionDto(redistribution.get());
		
		return ResponseEntity.ok(redistributionDto);
	}
	
	@GetMapping(value = "/redistributions/organisations/{organisationId}")
	public ResponseEntity<List<RedistributionDto>> findRedistributionByOrganisationId(@PathVariable int organisationId){
		
		Optional<List<Redistribution>> redistributionList = redistributionDao.findAllRedistributionByOrganisationId(organisationId);
		
		if(!redistributionList.isPresent() || redistributionList.get().isEmpty()) {
			throw new RedistributionNotFoundException("No redistribution for organisation with id: " + organisationId + " has been found.");
		}
		List<RedistributionDto> redistributionDtoList =  RedistributionHelper.entityListToDtoList(redistributionList.get(), redistributionDtoMapper);
		return ResponseEntity.ok(redistributionDtoList);
	}
	
	@GetMapping(value = "/redistributions/active")
	public ResponseEntity<List<RedistributionDto>> findAllActiveRedistributions(){
		Optional<List<Redistribution>> redistributionList = redistributionDao.findAllActiveRedistribution();
		
		if(!redistributionList.isPresent() || redistributionList.get().isEmpty()) {
			throw new RedistributionNotFoundException("No redistribution for organisation has been found.");
		}
		List<RedistributionDto> redistributionDtoList =  RedistributionHelper.entityListToDtoList(redistributionList.get(), redistributionDtoMapper);
		return ResponseEntity.ok(redistributionDtoList);
	}
	
	
	@DeleteMapping(value = "/redistributions/{id}")
	public void deleteRedistribution (@PathVariable int id) {
		redistributionDao.deleteById(id);
	}
	
	@PostMapping(value = "/redistributions")
	public ResponseEntity<Void> addRedistribution(@RequestBody RedistributionDto redistributionDto){
		
		Redistribution redistribution = redistributionDtoMapper.redistributionDtoToRedistribution(redistributionDto);
		
		Redistribution red = redistributionDao.save(redistribution);
		
		if(red == null) {
			return ResponseEntity.noContent().build();
		}
		
		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(red.getId())
						.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping(value = "/redistributions")
	public void updateRedistribution(@RequestBody RedistributionDto redistributionDto) {
		Redistribution redistribution = redistributionDtoMapper.redistributionDtoToRedistribution(redistributionDto);
		redistributionDao.save(redistribution);
	}
	
	
	
}
