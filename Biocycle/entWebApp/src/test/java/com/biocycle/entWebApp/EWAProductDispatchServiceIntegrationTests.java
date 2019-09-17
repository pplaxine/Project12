package com.biocycle.entWebApp;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.biocycle.entWebApp.proxy.ProductDispatchServiceProxy;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EWAProductDispatchServiceIntegrationTests {
	
	@Autowired
	MockMvc mockMvc;
	@Autowired
	ProductDispatchServiceProxy productDispatchServiceProxy;
	
	//---- Tests 
	@WithMockUser(username = "pme",roles = {"PME"})
	@Test
	public void iT01ProductManagmentManagerAddOfferToRedistribution() throws Exception {

		//Request
		mockMvc.perform(
			MockMvcRequestBuilders.get("/pme/redistribution/offer/add")
									.flashAttr("redistributionViewDto", EWATestHelper.getRedistributionViewDto())
									.sessionAttr("productBatchBeanDtoMap", EWATestHelper.getProductBatchBeanDtoMap())
				).andExpect(status().is3xxRedirection());
		
	}
}