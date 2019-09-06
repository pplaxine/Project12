package com.biocycle.staffCRUD;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.biocycle.staffCRUD.dao.StaffDao;
import com.biocycle.staffCRUD.dto.StaffDto;
import com.biocycle.staffCRUD.dto.mapper.StaffDtoMapper;
import com.biocycle.staffCRUD.model.Staff;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@RunWith(SpringRunner.class)
@WebMvcTest
public class StaffCrudApplicationUnitTests {

	@Autowired
	MockMvc mockMvc;
	@MockBean
	StaffDao staffDao;
	@MockBean
	StaffDtoMapper staffDtoMapper;
	@MockBean
	Staff staff;
	@MockBean
	StaffDto staffDto;
	

	private Staff staffStub;
	private StaffDto staffDtoStub;
	private List<Staff> staffList;
	
	private ObjectWriter objectWriter;

	@Before
	public void excuteBeforeEach() {
		
		//jSon object writer
		ObjectMapper jsonM = new ObjectMapper();
		jsonM.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		objectWriter = jsonM.writer().withDefaultPrettyPrinter();
		
		//STUB -----
		//staffDto
		staffDtoStub = new StaffDto();
		staffDtoStub.setUserName("Phil");
		staffDtoStub.setSurname("Plaxine");
		
		//staff
		staffStub = new Staff();
		staffStub.setId(1);
		
		//staffList
		staffList = new ArrayList<>();
		staffList.add(staff);
	}
	
	//---- GET
	@Test
	public void ControllerFindAllStaffUT() throws Exception {
		
		//Dao
		Mockito.when(staffDao.findAll()).thenReturn(staffList);
		
		//Mapper
		Mockito.when(staffDtoMapper.staffToStaffDto(staff)).thenReturn(staffDtoStub);
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
								MockMvcRequestBuilders.get("/staff")
													.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 200;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void ControllerFindAllStaffExceptionUT() throws Exception {
		
		//Dao
		Mockito.when(staffDao.findAll()).thenReturn(Collections.emptyList());
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
								MockMvcRequestBuilders.get("/staff")
													.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 404;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void ControllerFindStaffByIdUT() throws Exception {
		
		//Dao
		Mockito.when(staffDao.findById(1)).thenReturn(Optional.of(staff));
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
								MockMvcRequestBuilders.get("/staff/1")
													.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Mapper
		Mockito.when(staffDtoMapper.staffToStaffDto(staff)).thenReturn(staffDtoStub);
		
		//Assert
		int expectedStatusCode = 200;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void ControllerFindStaffByIdExceptionUT() throws Exception {
		
		//Dao
		Mockito.when(staffDao.findById(1)).thenReturn(Optional.empty());
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
								MockMvcRequestBuilders.get("/staff/1")
													.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 404;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void ControllerFindStaffByUserNameUT() throws Exception {
		
		String userName = "Philippe";
		
		//Dao
		Mockito.when(staffDao.findStaffByUserName(userName)).thenReturn(Optional.of(staff));
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
								MockMvcRequestBuilders.get("/staff/username/" + userName)
													.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Mapper
		Mockito.when(staffDtoMapper.staffToStaffDto(staff)).thenReturn(staffDtoStub);
		
		//Assert
		int expectedStatusCode = 200;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void ControllerFindStaffByUserNameExceptionUT() throws Exception {
		
		String userName = "Philippe";
		
		//Dao
		Mockito.when(staffDao.findStaffByUserName(userName)).thenReturn(Optional.empty());
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
								MockMvcRequestBuilders.get("/staff/username/" + userName)
													.accept(MediaType.APPLICATION_JSON)
				).andReturn();
		
		//Assert
		int expectedStatusCode = 404;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}
	
	//---- POST
	@Test
	public void ControllerAddStaffUT() throws Exception {
		
		staffStub.setId(1);
		
		//Dto to Json
		String staffJson = objectWriter.writeValueAsString(staffDtoStub);

		//Dao
		Mockito.when(staffDao.save(null)).thenReturn(staffStub);		//MapStruc bug fix 
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
								MockMvcRequestBuilders.post("/staff")
													.contentType(MediaType.APPLICATION_JSON_UTF8)
													.content(staffJson)
				).andReturn();

		//Assert
		int expectedStatusCode = 201;
		String[] location = mvcResult.getResponse().getHeader("Location").split("/");
		int pathId = Integer.valueOf(location[location.length-1]);
		
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
		assertEquals("Unexpected location : ", staffStub.getId(), pathId);
	}
	
	@Test
	public void ControllerAddStaffExceptionUT() throws Exception {
		
		staffStub.setId(1);
		
		//Dto to Json
		String staffJson = objectWriter.writeValueAsString(staffDtoStub);

		//Dao
		Mockito.when(staffDao.save(staffStub)).thenReturn(null);		//MapStruc bug fix 
		
		//Request
		MvcResult mvcResult = mockMvc.perform(
								MockMvcRequestBuilders.post("/staff")
													.contentType(MediaType.APPLICATION_JSON_UTF8)
													.content(staffJson)
				).andReturn();

		//Assert
		int expectedStatusCode = 204;
		assertEquals("Unexpected status : ", expectedStatusCode, mvcResult.getResponse().getStatus());
	}

}
