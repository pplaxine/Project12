package com.biocycle.offerCRUD.controller;

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

import com.biocycle.offerCRUD.dao.OfferDao;
import com.biocycle.offerCRUD.dto.OfferDto;
import com.biocycle.offerCRUD.dto.mapper.OfferDtoMapper;
import com.biocycle.offerCRUD.exception.OfferNotFoundException;
import com.biocycle.offerCRUD.model.Offer;

@RestController
public class OfferController {
	
	@Autowired
	private OfferDao offerDao;
	
	@Autowired
	private OfferDtoMapper offerDtoMapper;
	
	@GetMapping(value = "/offers/{id}")
	public ResponseEntity<OfferDto> findOfferById(@PathVariable int id){
		Optional<Offer> offer = offerDao.findById(id);
		
		if(!offer.isPresent()) {
			throw new OfferNotFoundException("offer with id: " + id + " does not exist.");
		}
		
		OfferDto offerDto = offerDtoMapper.offerToOfferDto(offer.get());
		
		return ResponseEntity.ok(offerDto);
	}
	
	@DeleteMapping(value = "/offers/{id}")
	public void deleteOffer(@PathVariable int id) {
		offerDao.deleteById(id);
	}
	
	@PostMapping(value = "/offers")
	public ResponseEntity<Void> addOffer(@RequestBody OfferDto offerDto){
		
		Offer offer = offerDtoMapper.offerDtoToOffer(offerDto);
		
		Offer off = offerDao.save(offer);
		
		if(off == null) {
			return ResponseEntity.noContent().build();
		}
		
		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(off.getId())
						.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping(value = "/offers")
	public ResponseEntity<Void> updateOffer(@RequestBody OfferDto offerDto) {
		Offer offer = offerDtoMapper.offerDtoToOffer(offerDto);
		Offer off = offerDao.save(offer);
		if(off == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.accepted().build();
	}
}






