package com.biocycle.productDispatchService.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import com.biocycle.productDispatchService.bean.ProductRequestBean;
import com.biocycle.productDispatchService.bean.RedistributionBean;
import com.biocycle.productDispatchService.dto.OfferBeanDto;
import com.biocycle.productDispatchService.dto.ProductRequestBeanDto;
import com.biocycle.productDispatchService.dto.RedistributionBeanDto;
import com.biocycle.productDispatchService.dto.mapper.ProductRequestBeanDtoMapper;
import com.biocycle.productDispatchService.dto.mapper.RedistributionBeanDtoMapper;
import com.biocycle.productDispatchService.exception.RedistributionCreationException;
import com.biocycle.productDispatchService.proxy.OfferCRUDMSProxy;
import com.biocycle.productDispatchService.proxy.ProductBatchCRUDMSProxy;
import com.biocycle.productDispatchService.proxy.ProductRequestCRUDMSProxy;
import com.biocycle.productDispatchService.proxy.RedistributionCRUDMSProxy;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProductDispatchManager.class})
public class ProductDispatchServiceManagerUnitTest {
	
	@SpyBean		//partially mock object
	private ProductDispatchManager productDispatchManager;
	@MockBean
	private RedistributionCRUDMSProxy redistributionCRUDMSProxy;
	@MockBean
	private ProductRequestCRUDMSProxy productRequestCRUDMSProxy;
	@MockBean
	private OfferCRUDMSProxy offerCRUDMSProxy;
	@MockBean
	private ProductBatchCRUDMSProxy ProductBatchCRUDMSProxy;
	@MockBean
	private RedistributionBeanDtoMapper redistributionBeanDtoMapper;
	@MockBean
	private ProductRequestBeanDtoMapper productRequestBeanDtoMapper;
	
	
	
	private RedistributionBean redistributionBean;
	private RedistributionBeanDto redistributionBeanDto;
	private OfferBeanDto offerBeanDto;
	private ProductRequestBean productRequestBean;
	private ProductRequestBeanDto productRequestBeanDto;
	private List<ProductRequestBeanDto> productRequestBeanDtoList;
	private List<Integer> productRequestIdList;
	
	@Before
	public void executeBeforeEach() {
		
		//--STUB 
		//RedistributionBean 
		redistributionBean = new RedistributionBean();
		redistributionBean.setId(1);
		//RedistributionBeanDto 
		redistributionBeanDto = new RedistributionBeanDto();
		redistributionBeanDto.setId(1);
		//OfferBeanDto 
		offerBeanDto = new OfferBeanDto();
		offerBeanDto.setId(1);
		//ProductRequestBeanDto
		productRequestBean = new ProductRequestBean();
		productRequestBean.setId(1);
		//ProductRequestBeanDto
		productRequestBeanDto = new ProductRequestBeanDto();
		productRequestBeanDto.setId(1);
		//ProductRequestBeanDtoList
		productRequestBeanDtoList = new ArrayList<>();
		productRequestBeanDtoList.add(productRequestBeanDto);
		productRequestBeanDtoList.add(productRequestBeanDto);
		//ProductRequestIdList
		productRequestIdList = new ArrayList<>();
		productRequestIdList.add(1);
		productRequestIdList.add(2);
	}
	
	@Test
	public void persistRedistributionUT() {
		
		//Mapper
		when(redistributionBeanDtoMapper.redistributionBeanToRedistributionBeanDto(redistributionBean)).thenReturn(redistributionBeanDto);
		
		//Proxy
		when(redistributionCRUDMSProxy.addRedistribution(redistributionBeanDto)).thenReturn(ResponseEntity.created(null).build());
		
		ResponseEntity<Void> resp = productDispatchManager.persistRedistribution(redistributionBean);
		
		//assert
		int expectedStatusCode = 201;
		assertEquals("Unexpected status : ",expectedStatusCode, resp.getStatusCode().value());
	}
	
	@Test(expected = ResponseStatusException.class)
	public void persistRedistributionExceptionUT() {
		
		//Mapper
		when(redistributionBeanDtoMapper.redistributionBeanToRedistributionBeanDto(redistributionBean)).thenReturn(redistributionBeanDto);
		
		//Proxy
		when(redistributionCRUDMSProxy.addRedistribution(redistributionBeanDto)).thenThrow(ResponseStatusException.class);
		
		ResponseEntity<Void> resp = productDispatchManager.persistRedistribution(redistributionBean);
	}
	
	@Test
	public void persistOfferBeanUT() {
		
		URI location = URI.create("/offercrud/offer/1");
		
		//Proxy
		when(offerCRUDMSProxy.addOffer(offerBeanDto)).thenReturn(ResponseEntity.created(location).build());
		
		Integer offerId = productDispatchManager.persistOfferBean(offerBeanDto);
		
		Integer offerIdExpected = 1;
		
		//assert
		assertEquals("Unexpected offerId : ",offerIdExpected, offerId);
	}
	
	@Test(expected = ResponseStatusException.class)
	public void persistOfferBeanExceptionUT() {
		
		//Proxy
		when(offerCRUDMSProxy.addOffer(offerBeanDto)).thenThrow(ResponseStatusException.class);
		
		productDispatchManager.persistOfferBean(offerBeanDto);

	}
	
	@Test
	public void persistProductRequestBeanUT() {
		
		//Proxy
		when(productRequestCRUDMSProxy.addProductRequestList(productRequestBeanDtoList)).thenReturn(ResponseEntity.ok(productRequestBeanDtoList));
		
		//Mapper 
		when(productRequestBeanDtoMapper.productRequestBeanDtoToProductRequestBean(productRequestBeanDto)).thenReturn(productRequestBean);
		
		List<Integer> productRequestIdList = productDispatchManager.persistProductRequestBean(productRequestBeanDtoList);
		
		//assert
		assertTrue("productRequestIdList is empty : ",productRequestIdList.size() > 0);
	}
	
	@Test
	public void createRedistributionForRequestUT() {
		
		int organisationId = 1;
		
		//Utility methods
		doReturn(productRequestIdList).when(productDispatchManager).persistProductRequestBean(productRequestBeanDtoList);
		doReturn(ResponseEntity.created(null).build()).when(productDispatchManager).persistRedistribution(any(RedistributionBean.class));
		
		ResponseEntity<Void>  resp = productDispatchManager.createRedistributionForRequest(organisationId, productRequestBeanDtoList);
		
		//Assert
		int expectedStatusCode = 201;
		assertEquals("Unexpected status : ", expectedStatusCode, resp.getStatusCode().value());
	}
	
	@Test(expected = RedistributionCreationException.class)
	public void createRedistributionForRequestExceptionUT() {
		
		int organisationId = 1;
		
		//Utility methods
		doReturn(productRequestIdList).when(productDispatchManager).persistProductRequestBean(productRequestBeanDtoList);
		doThrow(RedistributionCreationException.class).when(productDispatchManager).persistRedistribution(any(RedistributionBean.class));	//import manually any(mockito) 
		
		productDispatchManager.createRedistributionForRequest(organisationId, productRequestBeanDtoList);
	}
	
	@Test
	public void createRedistributionForOfferUT() {
		
		int organisationId = 1;
		int offerBeanId = 1;
		
		//Utility methods
		doReturn(offerBeanId).when(productDispatchManager).persistOfferBean(offerBeanDto);
		doReturn(ResponseEntity.created(null).build()).when(productDispatchManager).persistRedistribution(any(RedistributionBean.class));
		
		ResponseEntity<Void>  resp = productDispatchManager.createRedistributionForOffer(organisationId, offerBeanDto);
		
		//Assert
		int expectedStatusCode = 201;
		assertEquals("Unexpected status : ", expectedStatusCode, resp.getStatusCode().value());
	}
	
	@Test(expected = RedistributionCreationException.class)
	public void createRedistributionForOfferExceptionUT() {
		
		int organisationId = 1;
		int offerBeanId = 1;
		
		//Utility methods
		doReturn(offerBeanId).when(productDispatchManager).persistOfferBean(offerBeanDto);
		doThrow(RedistributionCreationException.class).when(productDispatchManager).persistRedistribution(any(RedistributionBean.class));
		
		productDispatchManager.createRedistributionForOffer(organisationId, offerBeanDto);

	}
	
	@Test
	public void addOfferToRedistributionUT() {
		
		int redistributionId = 1;
		URI location = URI.create("/offer/created/2");
		
		//Proxy
		when(redistributionCRUDMSProxy.getRedistributionById(redistributionId)).thenReturn(ResponseEntity.ok(redistributionBeanDto));
		when(offerCRUDMSProxy.addOffer(offerBeanDto)).thenReturn(ResponseEntity.created(location).build());
		doNothing().when(redistributionCRUDMSProxy).updateRedistribution(redistributionBeanDto);
		
		
		//Utility methods
		doNothing().when(productDispatchManager).updateProductBatchStat(offerBeanDto.getProductBatchIdList(), true);
		
		ResponseEntity<Void>  resp = productDispatchManager.addOfferToRedistribution(redistributionId, offerBeanDto);
		
		//Assert
		int expectedStatusCode = 200;
		assertEquals("Unexpected status : ", expectedStatusCode, resp.getStatusCode().value());
	}
	
	@Test(expected = RedistributionCreationException.class)
	public void addOfferToRedistributionExceptionUT() {
		
		int redistributionId = 1;
		URI location = URI.create("/offer/created/2");
		
		//Proxy
		when(redistributionCRUDMSProxy.getRedistributionById(redistributionId)).thenReturn(ResponseEntity.ok(redistributionBeanDto));
		when(offerCRUDMSProxy.addOffer(offerBeanDto)).thenReturn(ResponseEntity.created(location).build());
		doThrow(ResponseStatusException.class).when(redistributionCRUDMSProxy).updateRedistribution(redistributionBeanDto);
		
		
		//Utility methods
		doNothing().when(productDispatchManager).updateProductBatchStat(offerBeanDto.getProductBatchIdList(), true);
		
		ResponseEntity<Void>  resp = productDispatchManager.addOfferToRedistribution(redistributionId, offerBeanDto);
		
		//Assert
		int expectedStatusCode = 200;
		assertEquals("Unexpected status : ", expectedStatusCode, resp.getStatusCode().value());
	}
	
	
}
