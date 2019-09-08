package com.biocycle.customerManagmentService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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

import com.biocycle.customerManagmentService.bean.OrganisationBean;
import com.biocycle.customerManagmentService.controller.CustomerManagmentController;
import com.biocycle.customerManagmentService.dto.OrganisationBeanDto;
import com.biocycle.customerManagmentService.dto.mapper.OrganisationBeanDtoMapper;
import com.biocycle.customerManagmentService.proxy.OrganisationCRUDMSProxy;
import com.biocycle.customerManagmentService.service.OrganisationManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerManagmentController.class)
public class CustomerManagmentServiceControllerUnitTests {

	@Autowired
	MockMvc mockMvc;
	@MockBean
	OrganisationCRUDMSProxy organisationCRUDMSProxy;
	@MockBean
	OrganisationManager organisationManager;
	@MockBean
	OrganisationBeanDtoMapper organisationBeanDtoMapper;
	@MockBean
	OrganisationBeanDto organisationBeanDtoMock;
	
	private ObjectWriter objectWriter;
	private OrganisationBean organisationBeanStub;
	private OrganisationBeanDto organisationBeanDtoStub;
	private List<OrganisationBeanDto> organisationBeanDtoList;

	@Before
	public void executeBeforeEach() {
		
		//jSon object writer
		ObjectMapper jsonM = new ObjectMapper();
		jsonM.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		objectWriter = jsonM.writer().withDefaultPrettyPrinter();
		
		//--Stub
		//GiveAway
		organisationBeanStub = new OrganisationBean();
		organisationBeanStub.setId(1);
		//GiveAwayDtoStub
		organisationBeanDtoStub = new OrganisationBeanDto();
		organisationBeanDtoStub.setId(1);
		//GiveAwayList
		organisationBeanDtoList = new ArrayList<>();
		organisationBeanDtoList.add(organisationBeanDtoStub);
		organisationBeanDtoList.add(organisationBeanDtoStub);
		
		
	}
	
	//-- GET 
	
	@Test
	public void controllerFindAllOrganisationUT() throws Exception {
		
		String organisationBeanDtoListJsonFormat = objectWriter.writeValueAsString(organisationBeanDtoList);
		
		//Dao
		Mockito.when(organisationCRUDMSProxy.findAllOrganisation()).thenReturn(ResponseEntity.ok(organisationBeanDtoList));
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/organisations")	
								.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 200;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
		assertEquals("Response content is different", organisationBeanDtoListJsonFormat.replaceAll("\\s+","") ,mvcResult.getResponse().getContentAsString());	//replaceAll remove all spaces from jSon format
	}
	
	//-- POST
	@Test
	public void controllerAddOrganisationUT() throws Exception {
		
		organisationBeanDtoStub.setId(1);
		
		//Dto to Json
		String organisationBeanDtoJson = objectWriter.writeValueAsString(organisationBeanDtoStub);
		
		when(organisationManager.addOrganisation(organisationBeanDtoStub)).thenReturn(ResponseEntity.ok().build());
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.post("/organisations")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(organisationBeanDtoJson)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 200;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void controllerAddPasswordUT() throws Exception {
		
		organisationBeanDtoStub.setId(1);
		
		//Dto to Json
		String organisationBeanDtoJson = objectWriter.writeValueAsString(organisationBeanDtoStub);
		
		//Dao
		when(organisationManager.addPassword(organisationBeanDtoStub)).thenReturn(ResponseEntity.ok().build());
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.post("/organisations/password")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(organisationBeanDtoJson)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 200;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	

}























