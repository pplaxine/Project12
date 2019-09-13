package com.biocycle.entWebApp.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.anything;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.biocycle.entWebApp.dto.OfferBeanDto;
import com.biocycle.entWebApp.dto.OrganisationBeanDto;
import com.biocycle.entWebApp.dto.ProductBatchBeanDto;
import com.biocycle.entWebApp.dto.ProductRequestBeanDto;
import com.biocycle.entWebApp.dto.RedistributionBeanDto;
import com.biocycle.entWebApp.dto.view.OfferViewDto;
import com.biocycle.entWebApp.dto.view.RedistributionViewDto;
import com.biocycle.entWebApp.proxy.InventoryServiceProxy;
import com.biocycle.entWebApp.proxy.OfferCRUDMSProxy;
import com.biocycle.entWebApp.proxy.OrganisationCRUDMSProxy;
import com.biocycle.entWebApp.proxy.ProductBatchCRUDMSProxy;
import com.biocycle.entWebApp.proxy.ProductDispatchServiceProxy;
import com.biocycle.entWebApp.proxy.ProductRequestCRUDMSProxy;
import com.biocycle.entWebApp.proxy.RedistributionCRUDMSProxy;
import com.biocycle.entWebApp.proxy.StorageContainerCRUDMSProxy;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProductManagmentManager.class})
public class EntWebAppManagerUnitTests {

	@SpyBean
	ProductManagmentManager productManagmentManager;
	
	@MockBean
	InventoryServiceProxy inventoryServiceProxy;
	@MockBean
	CustomerManagmentManager customerManagmentManager;
	@MockBean
	ProductBatchCRUDMSProxy productBatchCRUDMSProxy;
	@MockBean
	StorageContainerCRUDMSProxy storageContainerCRUDMSProxy;
	@MockBean
	RedistributionCRUDMSProxy redistributionCRUDMSProxy;
	@MockBean
	OrganisationCRUDMSProxy organisationCRUDMSProxy;
	@MockBean
	ProductRequestCRUDMSProxy productRequestCRUDMSProxy;
	@MockBean
	OfferCRUDMSProxy offerCRUDMSProxy;
	@MockBean
	ProductDispatchServiceProxy productDispatchServiceProxy;

	@Captor
	private ArgumentCaptor<Integer> productBatchIdCaptor, productRequestId; 
	@Captor
	private ArgumentCaptor<RedistributionBeanDto> rbd;
	
	private ProductBatchBeanDto productBatchBeanDto;
	private List<ProductBatchBeanDto> productBatchBeanDtoList;
	private RedistributionBeanDto redistributionBeanDto;
	private List<RedistributionBeanDto> redistributionBeanDtoList;
	private List<Integer> productRequestIdList, productBatchIdList;
	private OfferBeanDto offerBeanDto;
	private LocalDate randomDate;
	private ProductRequestBeanDto productRequestBeanDto;
	private OrganisationBeanDto organisationBeanDto;
	private OfferViewDto offerViewDto;
	private RedistributionViewDto redistributionViewDto;
	
	@Before
	public void executeBeforeEach() {
		
		//---- STUB 
		//productBatchBeanDtoList
		productBatchBeanDtoList = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			productBatchBeanDto = new ProductBatchBeanDto();
			productBatchBeanDto.setId(i+1);
			if(i == 0) {
				productBatchBeanDto.setIsAvailable(true);
				productBatchBeanDto.setIsAwaitingForCollection(false);
			}else if (i == 1) {
				productBatchBeanDto.setIsAvailable(true);
				productBatchBeanDto.setIsAwaitingForCollection(null);
			}else {
				productBatchBeanDto.setIsAvailable(false);
			}
			productBatchBeanDtoList.add(productBatchBeanDto);
		}
		
		//redistributionBeanDtoList
		redistributionBeanDtoList = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			redistributionBeanDto = new RedistributionBeanDto();
			productRequestIdList = new ArrayList<>();
			redistributionBeanDto.setId(i+1);
			if (i==1) {
				productRequestIdList.add(1);
				productRequestIdList.add(2);
				redistributionBeanDto.setProductRequestId(productRequestIdList);
			}else {
				redistributionBeanDto.setOfferId(i+1);
				redistributionBeanDto.setProductRequestId(productRequestIdList);
			}
			redistributionBeanDtoList.add(redistributionBeanDto);
		}
		
		//offerViewDto
		randomDate = LocalDate.now();
		productBatchIdList = new ArrayList<>();
		productBatchIdList.add(1);
		productBatchIdList.add(2);
		
		offerBeanDto = new OfferBeanDto();
		offerBeanDto.setId(1);
		offerBeanDto.setIsAccepted(true);
		offerBeanDto.setOfferEndingDate(randomDate);
		offerBeanDto.setAvailableForCollection(randomDate);
		offerBeanDto.setProductBatchIdList(productBatchIdList);
		
		//productRequestBeanDto
		productRequestBeanDto = new ProductRequestBeanDto();
		productRequestBeanDto.setId(1);
		
		//organisationBeanDto
		organisationBeanDto = new OrganisationBeanDto();
		organisationBeanDto.setId(1);
		
		//offerViewDto
		offerViewDto = new OfferViewDto();
		offerViewDto.setId(2);
		
		//productRequestIdList
		productRequestIdList = new ArrayList<>();
		
		
		//redistributionViewDto
		redistributionViewDto = new RedistributionViewDto();
	}
	
	//FILTERS 
	@Test
	public void filterProductBatchAvailableForOfferUT() {
		
		//Method
		List<ProductBatchBeanDto> productBatchBeanDtoListResult = productManagmentManager.filterProductBatchAvailableForOffer(productBatchBeanDtoList);
		//assert
		assertTrue("The size of the list should be 2", productBatchBeanDtoListResult.size() == 2);
	}
	@Test
	public void filterProductBatchActiveUT() {
		
		//Method
		List<ProductBatchBeanDto> productBatchBeanDtoListResult = productManagmentManager.filterProductBatchActive(productBatchBeanDtoList);
		//assert
		assertTrue("The size of the list should be 2", productBatchBeanDtoListResult.size() == 2);
	}
	@Test
	public void filterRedistributionWithoutOfferUT() {
		 
		//Method
		List<RedistributionBeanDto> redistributionBeanDtoListResult = productManagmentManager.filterRedistributionWithoutOffer(redistributionBeanDtoList);
		//assert
		assertTrue("The size of the list should be 1", redistributionBeanDtoListResult.size() == 1);
	}
	@Test
	public void filterRedistributionWithRequestOnlyUT() {
		 
		//Method
		List<RedistributionBeanDto> redistributionBeanDtoListResult = productManagmentManager.filterRedistributionWithRequestOnly(redistributionBeanDtoList);
		//assert
		assertTrue("The size of the list should be 2", redistributionBeanDtoListResult.size() == 3);
	}
	
	@Test
	public void offerBeanDtoToOfferviewDtoUT() {
		
		//Proxy
		when(productBatchCRUDMSProxy.findProductBatchById(productBatchIdCaptor.capture())).thenReturn(ResponseEntity.ok(productBatchBeanDto));
		
		//Method
		OfferViewDto offerViewDtoResult = productManagmentManager.offerBeanDtoToOfferviewDto(offerBeanDto);
		//assert 
		assertEquals("Id are not equal", offerBeanDto.getId(), offerViewDtoResult.getId());
		assertEquals("availableForCollection booleans are not equal", offerBeanDto.getAvailableForCollection(), offerViewDtoResult.getAvailableForCollection());
		assertEquals("OfferEndingDate localDates are not equal", offerBeanDto.getOfferEndingDate(), offerViewDtoResult.getOfferEndingDate());
		assertEquals("AvailableForCollection localDates are not equal", offerBeanDto.getAvailableForCollection(), offerViewDtoResult.getAvailableForCollection());
		assertEquals("IsAccepted are not equal", offerBeanDto.getIsAccepted(), offerViewDtoResult.getIsAccepted());
		assertEquals("Id are not equal", offerBeanDto.getProductBatchIdList().size(), offerViewDtoResult.getProductBatchBeanDtoList().size());
	}
	
	@Test
	public void getProductRequestBeanFromIdListUT() {
		
		productRequestIdList.add(1);
		productRequestIdList.add(2);
		
		
		//Proxy 
		when(productRequestCRUDMSProxy.findProductRequestById(productRequestId.capture())).thenReturn(productRequestBeanDto);
		
		//Method 
		List<ProductRequestBeanDto> productRequestBeanDtoList =  productManagmentManager.getProductRequestBeanFromIdList(productRequestIdList);
		
		//assert
		assertEquals("Id are not equal", productRequestIdList.size(), productRequestBeanDtoList.size());
	}
	
	@Test
	public void redistributionBeanDtoToViewDtoUT() {
		
		productRequestIdList.add(1);
		productRequestIdList.add(2);
		productRequestIdList.add(3);
		
		redistributionBeanDto.setOrganisationId(1);
		redistributionBeanDto.setOfferId(2);
		redistributionBeanDto.setProductRequestId(productRequestIdList);
		offerBeanDto.setId(2);
		
		
		//Proxy
		when(organisationCRUDMSProxy.findOrganisationById(redistributionBeanDto.getOrganisationId())).thenReturn(ResponseEntity.ok(organisationBeanDto));
		when(offerCRUDMSProxy.findOfferById(redistributionBeanDto.getOfferId())).thenReturn(ResponseEntity.ok(offerBeanDto));
		
		//Spy
		doReturn(offerViewDto).when(productManagmentManager).offerBeanDtoToOfferviewDto(offerBeanDto);
		doReturn(productBatchBeanDtoList).when(productManagmentManager).getProductRequestBeanFromIdList(productRequestIdList);
		
		//Method
		RedistributionViewDto redistributionViewDtoResp = productManagmentManager.redistributionBeanDtoToViewDto(redistributionBeanDto);
		
		
		//assert 
		assertEquals("Id is not equal",redistributionBeanDto.getId(), redistributionViewDtoResp.getId());
		assertEquals("Offer Id is not equal",redistributionBeanDto.getOfferId().intValue(), redistributionViewDtoResp.getOfferViewDto().getId());
		assertEquals("Requests List size is not equal",redistributionBeanDto.getProductRequestId().size(), redistributionViewDtoResp.getProductRequestBeanDtoList().size());
	}
	
	@Test
	public void  beanDtoListToViewDtoListUT() {
		
		redistributionViewDto.setId(1);
		
		//Spy
		doReturn(redistributionViewDto).when(productManagmentManager).redistributionBeanDtoToViewDto(rbd.capture());
		
		//Mehode
		List<RedistributionViewDto> redistributionViewDtoListResp =  productManagmentManager.beanDtoListToViewDtoList(redistributionBeanDtoList);
		
		//assert 
		assertEquals("Lists sizes are not equal",redistributionBeanDtoList.size(), redistributionViewDtoListResp.size());
	}
	
	public void getURILastPartUT() {
		
		URI location = URI.create("I/Love/URL/3");
		
		//Method
		String lastpart = productManagmentManager.getURILastPart(location);
		
		//assert 
		assertTrue("lastPart should be 3", lastpart.equals("3"));
	}
	
	
}
