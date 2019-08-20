package com.biocycle.giveAwayCRUD.controller;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.biocycle.giveAwayCRUD.dao.GiveAwayDao;
import com.biocycle.giveAwayCRUD.dto.GiveAwayDto;
import com.biocycle.giveAwayCRUD.dto.mapper.GiveAwayDtoMapper;
import com.biocycle.giveAwayCRUD.exception.GiveAwayNotFoundException;
import com.biocycle.giveAwayCRUD.model.GiveAway;
import com.biocycle.giveAwayCRUD.model.helper.GiveAwayHelper;

@RestController
public class GiveAwayController {
	
	@Autowired
	private GiveAwayDao giveAwayDao;
	
	@Autowired
	private GiveAwayDtoMapper giveAwayDtoMapper;
	
	//---- GET 
	@GetMapping(value = "/giveaways/date/{date}")
	public ResponseEntity<List<GiveAwayDto>> findActiveGiveAwayByDate(@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date date){
		Optional<List<GiveAway>> activeGiveAwayList = giveAwayDao.findActiveGiveAwayByDate(date);
		
		if(!activeGiveAwayList.isPresent()) {
			throw new GiveAwayNotFoundException("No active giveAway could be found");
		}
		
		List<GiveAwayDto> activeGiveAwayDtoList = GiveAwayHelper.EntityListToDtoList(activeGiveAwayList.get(), giveAwayDtoMapper);
		
		return ResponseEntity.ok(activeGiveAwayDtoList);
	}
	
	@GetMapping(value = "/giveaways/active")
	public ResponseEntity<List<GiveAwayDto>> findActiveGiveAway(){
		Optional<List<GiveAway>> activeGiveAwayList = giveAwayDao.findAllActiveGiveAway();
		
		if(!activeGiveAwayList.isPresent()) {
			throw new GiveAwayNotFoundException("No active giveAway could be found");
		}
		
		List<GiveAwayDto> activeGiveAwayDtoList = GiveAwayHelper.EntityListToDtoList(activeGiveAwayList.get(), giveAwayDtoMapper);
		
		return ResponseEntity.ok(activeGiveAwayDtoList);
	}
	
	@GetMapping(value = "/giveaways/{id}")
	public GiveAwayDto findGiveAway(@PathVariable int id){
		Optional<GiveAway> giveAway = giveAwayDao.findById(id);
		
		if(!giveAway.isPresent()) {
			throw new GiveAwayNotFoundException("GiveAway with id: " + id + " doesn't exist.");
		}
		return giveAwayDtoMapper.giveAwayToGiveAwayDto(giveAway.get());
	}
	
	//---- DELETE
	@DeleteMapping(value = "/giveaways/{id}")
	public void deleteGiveAway(@PathVariable int id) {
		giveAwayDao.deleteById(id);
	}
	
	//---- POST 
	@PostMapping(value = "/giveaways")
	public ResponseEntity<Void> addGiveAway(@RequestBody GiveAwayDto giveAwayDto){
		
		GiveAway giveAway = giveAwayDtoMapper.giveAwayDtoToGiveAway(giveAwayDto);
		
		try {
			GiveAway ga = giveAwayDao.save(giveAway);
			
			//FIX 
			
			 //NEED TO HAVE ADDRESS
			//---------------
			
			URI location = ServletUriComponentsBuilder
							.fromCurrentRequest()
							.path("/{id}")
							.buildAndExpand(ga.getId())
							.toUri();
			
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			if(e.getClass() == DataIntegrityViolationException.class) {
				return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
			}else {
				throw e;
			}
		}	
	}
	
	//---- PUT
	@PutMapping(value = "/giveaways")
	public void updateGiveAway(@RequestBody GiveAwayDto giveAwayDto) {
		GiveAway giveAway = giveAwayDtoMapper.giveAwayDtoToGiveAway(giveAwayDto);
		giveAwayDao.save(giveAway);
	}

}
