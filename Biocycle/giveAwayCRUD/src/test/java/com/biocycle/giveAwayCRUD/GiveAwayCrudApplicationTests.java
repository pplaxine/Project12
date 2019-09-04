package com.biocycle.giveAwayCRUD;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.biocycle.giveAwayCRUD.controller.GiveAwayController;
import com.biocycle.giveAwayCRUD.dto.GiveAwayDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GiveAwayCrudApplicationTests {

	@Autowired
	GiveAwayController giveAwayController;
	
	@Test
	public void contextLoads() {
		
		ResponseEntity<List<GiveAwayDto>> resp = giveAwayController.findActiveGiveAway();
		System.out.println( "NOMBRE DE ACTIVE GIVE AWAY : " + resp.getBody().size());
	}

}
