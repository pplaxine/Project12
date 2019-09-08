package com.biocycle.customerManagmentService.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.biocycle.customerManagmentService.bean.Address;
import com.biocycle.customerManagmentService.bean.OrganisationBean;
import com.biocycle.customerManagmentService.dto.mapper.OrganisationBeanDtoMapper;
import com.biocycle.customerManagmentService.proxy.OrganisationCRUDMSProxy;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {OrganisationManager.class})
public class CustomerManagmentServiceManagerUnitTests {

	@Autowired
	private OrganisationManager organisationBeanManager;
	@MockBean
	private OrganisationCRUDMSProxy organisationCRUDMSProxy;
	@MockBean
	private OrganisationBeanDtoMapper organisationBeanDtoMapper;
	
	private OrganisationBean organisationBeanStub;
	
	
	@Before
	public void executeBeforeEach() {

		//--Stub
		
		//Address
		Address organisationAddress = new Address();
		organisationAddress.setStreetNumber("12");
		organisationAddress.setStreetName("rue Paul Berte");
		organisationAddress.setCity("Paris");
		organisationAddress.setPostCode("75011");
		
		//OrganisationBeanStub
		organisationBeanStub = new OrganisationBean();
		organisationBeanStub.setId(1);
		organisationBeanStub.setOrganisationAddress(organisationAddress);
	}
	
	@Test
	public void addFirstSpotAddressFromOrganisationAddress() {

		assertNull("Organisation collectionSpotAddress is not null ", organisationBeanStub.getCollectionAddressList());
		
		OrganisationBean org = organisationBeanManager.addFirstSpotAddressFromOrganisationAddress(organisationBeanStub);
		assertEquals("Organisation id is not equal ",organisationBeanStub.getId(), org.getId());
		assertEquals("Organisation address is not equal ", organisationBeanStub.getOrganisationAddress().getStreetName(), org.getOrganisationAddress().getStreetName());
		assertNotNull("Organisation collectionSpotAddress is null ", org.getCollectionAddressList());
		
	}
	
}























