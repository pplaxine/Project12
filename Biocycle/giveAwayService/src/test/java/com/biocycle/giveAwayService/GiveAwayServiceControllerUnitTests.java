package com.biocycle.giveAwayService;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.biocycle.giveAwayService.bean.GiveAwayBean;
import com.biocycle.giveAwayService.dto.GiveAwayBeanDto;
import com.biocycle.giveAwayService.dto.proxy.GiveAwayCRUDMSProxy;
import com.biocycle.giveAwayService.service.GiveAwayManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@RunWith(SpringRunner.class)
@WebMvcTest
public class GiveAwayServiceControllerUnitTests {

	@Autowired
	MockMvc mockMvc;
	@MockBean
	GiveAwayCRUDMSProxy giveAwayCRUDMSProxy;
	@MockBean
	GiveAwayManager giveAwayManager;
	@MockBean
	GiveAwayBeanDto giveAwayBeanDtoMock;
	
	private ObjectWriter objectWriter;
	private GiveAwayBean giveAwayBeanStub;
	private GiveAwayBeanDto giveAwayBeanDtoStub;
	private List<GiveAwayBeanDto> giveAwayBeanDtoList;

	@Before
	public void executeBeforeEach() {
		
		//jSon object writer
		ObjectMapper jsonM = new ObjectMapper();
		jsonM.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		objectWriter = jsonM.writer().withDefaultPrettyPrinter();
		
		//--Stub
		//GiveAway
		giveAwayBeanStub = new GiveAwayBean();
		giveAwayBeanStub.setId(1);
		//GiveAwayDtoStub
		giveAwayBeanDtoStub = new GiveAwayBeanDto();
		giveAwayBeanDtoStub.setId(1);
		//GiveAwayList
		giveAwayBeanDtoList = new ArrayList<>();
		giveAwayBeanDtoList.add(giveAwayBeanDtoStub);
		giveAwayBeanDtoList.add(giveAwayBeanDtoStub);
	}
	
	//-- GET 
	
	@Test
	public void controllergetAllActiveGiveAwayUT() throws Exception {
		
		String giveAwayBeanDtoListJsonFormat = objectWriter.writeValueAsString(giveAwayBeanDtoList);
		
		//Dao
		Mockito.when(giveAwayCRUDMSProxy.getAllActiveGiveAway()).thenReturn(ResponseEntity.ok(giveAwayBeanDtoList));
		
		//Manager
		Mockito.when(giveAwayManager.getAllActiveGiveAway()).thenReturn(ResponseEntity.ok(giveAwayBeanDtoList));
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/giveaways/active")	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 200;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
		assertEquals("Response content is different", giveAwayBeanDtoListJsonFormat.replaceAll("\\s+","") ,mvcResult.getResponse().getContentAsString());	//replaceAll remove all spaces from jSon format
	}
}
