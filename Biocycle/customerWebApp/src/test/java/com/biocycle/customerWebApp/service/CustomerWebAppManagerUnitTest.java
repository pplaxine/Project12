package com.biocycle.customerWebApp.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.biocycle.customerWebApp.dto.ContainerDto;
import com.biocycle.customerWebApp.dto.GiveAwayBeanDto;
import com.biocycle.customerWebApp.dto.view.ContainerViewDto;
import com.biocycle.customerWebApp.proxy.GiveAwayCRUDMSProxy;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {GiveAwayManager.class})
public class CustomerWebAppManagerUnitTest {
	
	@Autowired
	GiveAwayManager giveAwayManager;
	@MockBean
	GiveAwayCRUDMSProxy giveAwayCRUDMSProxy;
	
	private GiveAwayBeanDto giveAwayBeanDto;
	private List<GiveAwayBeanDto> giveAwayBeanDtoList;
	private ContainerDto containerDto;
	private List<ContainerDto> containerDtoList;
	private ContainerViewDto containerViewDto;
	private Map<String, ContainerViewDto> containerViewDtoMap;
	
	@Before
	public void executeBeforeEach() {
		 
		 //-- STUB
		 //GiveAwayBeanDto
		 giveAwayBeanDto = new GiveAwayBeanDto();
		 giveAwayBeanDto.setId(1);
		 
		 //ContainerDto
		 containerDto = new ContainerDto();
		 containerDto.setId(1);
		 
		 //containerViewDto
		 containerViewDto = new ContainerViewDto();
		 containerViewDto.setId("1");;
		 containerViewDto.setDescription("Description");
		 
		 //containerViewDtoMap
		 containerViewDtoMap = new HashMap<>();
		 containerViewDtoMap.put("container1", containerViewDto);
		 containerViewDto.setId("2");
		 containerViewDtoMap.put("container2", containerViewDto);
	}

	@Test
	public void allContainerRefusedCheckerUT() {
		
		 //GiveAwayBeanDtoList
		 giveAwayBeanDtoList = new ArrayList<>();
		 int giveAwayId = 1;
		 int containerId = 1;
		 for (int i = 0; i < 5; i++) {
			containerDtoList = new ArrayList<>();
			giveAwayBeanDto = new GiveAwayBeanDto();
			for (int j = 0; j < 3; j++) {
				containerDto = new ContainerDto();
				containerDto.setId(containerId++);
				containerDtoList.add(containerDto);
				if(giveAwayId%2 == 0) {				// all even giveAway have their container accepter 
					containerDto.setAccepted(false);
				}
			}
			giveAwayBeanDto.setId(giveAwayId++);
			giveAwayBeanDto.setContainerList(containerDtoList);
			giveAwayBeanDtoList.add(giveAwayBeanDto);
		}
		
		//Manager
		List<GiveAwayBeanDto> giveAwayBeanDtoListResp = giveAwayManager.allContainerRefusedChecker(giveAwayBeanDtoList);
		
		//Assert 
		int numberOfGiveAwaySetHasCollected = 0;
		for (GiveAwayBeanDto gabd : giveAwayBeanDtoListResp) {
			if(gabd.getIsCollected() != null && gabd.getIsCollected() == true) {
				numberOfGiveAwaySetHasCollected++;
			}
		}
		
		//assert
		assertTrue("Number of giveAway set to collected should be 2", numberOfGiveAwaySetHasCollected == 2);
	}
	
	@Test
	public void containerViewDtoToContainerDtoUT() {
		
		//Manager
		ContainerDto containerDto = giveAwayManager.containerViewDtoToContainerDto(containerViewDto);
		
		//Assert
		assertTrue("ContainerDto and containerViewDto descritpions should be equals. ", containerDto.getDescription().equals(containerViewDto.getDescription()));
		assertTrue("ContainerDto is collected should be false", containerDto.getIsCollected() == false);
	}
	
	@Test
	public void ViewDtoMapToDtoListUT() {
		
		//Manager
		List<ContainerDto> containerDtoList = giveAwayManager.ViewDtoMapToDtoList(containerViewDtoMap);
		
		//Assert
		assertTrue("containerViewMap and containerDtoList sizes should be equal", containerDtoList.size() == containerViewDtoMap.size());
	}
	
	
}
