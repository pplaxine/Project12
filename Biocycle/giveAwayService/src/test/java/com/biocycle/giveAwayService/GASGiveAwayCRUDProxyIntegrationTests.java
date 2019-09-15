package com.biocycle.giveAwayService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GASGiveAwayCRUDProxyIntegrationTests {

	@Autowired
	MockMvc mockMvc;
	
	//---- GET
	@Test
	public void iT01getAllActiveGiveAwayn() throws Exception {
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/giveaways/active")	
								.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
	}
}
