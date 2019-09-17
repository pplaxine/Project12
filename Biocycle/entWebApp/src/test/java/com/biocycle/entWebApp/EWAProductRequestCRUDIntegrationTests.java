package com.biocycle.entWebApp;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.biocycle.entWebApp.dto.OrganisationBeanDto;
import com.biocycle.entWebApp.dto.RedistributionBeanDto;
import com.biocycle.entWebApp.proxy.OrganisationCRUDMSProxy;
import com.biocycle.entWebApp.proxy.ProductRequestCRUDMSProxy;
import com.biocycle.entWebApp.proxy.RedistributionCRUDMSProxy;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EWAProductRequestCRUDIntegrationTests {
	
	@Autowired
	MockMvc mockMvc;
	@Autowired
	ProductRequestCRUDMSProxy productRequestCRUDMSProxy;
	
	@MockBean
	OrganisationCRUDMSProxy organisationCRUDMSProxy;
	@MockBean
	RedistributionCRUDMSProxy redistributionCRUDMSProxy;
	
	@Captor
	private ArgumentCaptor<Integer> organisationIdCaptor;
	
	//---- Tests 
	@WithMockUser(username = "pme",roles = {"PME"})
	@Test
	public void iT01ProductManagmentManagerRedistribution() throws Exception {
		
		OrganisationBeanDto orga2 = EWATestHelper.getOrganisationBeanDto(true);
		orga2.setId(2);
		
		List<RedistributionBeanDto> redistributionBeanDtoList = EWATestHelper.getRedistributionBeanDtoList();
		RedistributionBeanDto rbd = redistributionBeanDtoList.get(0);
		rbd.setOfferId(null);
		redistributionBeanDtoList.set(0, rbd);
		
		//Mock 
		when(redistributionCRUDMSProxy.findAllActiveRedistributions()).thenReturn(ResponseEntity.ok(redistributionBeanDtoList));
		when(organisationCRUDMSProxy.findOrganisationById(organisationIdCaptor.capture())).thenReturn(ResponseEntity.ok(orga2));
		
		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/pme/redistribution")
				).andExpect(status().is2xxSuccessful());
		
	}

}