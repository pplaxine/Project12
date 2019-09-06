package com.biocycle.productDispatchService.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.biocycle.productDispatchService.bean.ProductRequestBean;
import com.biocycle.productDispatchService.bean.RedistributionBean;
import com.biocycle.productDispatchService.dto.OfferBeanDto;
import com.biocycle.productDispatchService.dto.ProductRequestBeanDto;
import com.biocycle.productDispatchService.dto.RedistributionBeanDto;
import com.biocycle.productDispatchService.dto.mapper.ProductRequestBeanDtoMapper;
import com.biocycle.productDispatchService.dto.mapper.RedistributionBeanDtoMapper;
import com.biocycle.productDispatchService.exception.RedistributionCreationException;
import com.biocycle.productDispatchService.helper.ProductDispatchHelper;
import com.biocycle.productDispatchService.proxy.OfferCRUDMSProxy;
import com.biocycle.productDispatchService.proxy.ProductBatchCRUDMSProxy;
import com.biocycle.productDispatchService.proxy.ProductRequestCRUDMSProxy;
import com.biocycle.productDispatchService.proxy.RedistributionCRUDMSProxy;

@Service
public class ProductDispatchManager {
	
	@Autowired
	private RedistributionCRUDMSProxy redistributionCRUDMSProxy;
	@Autowired
	private ProductRequestCRUDMSProxy productRequestCRUDMSProxy;
	@Autowired
	private OfferCRUDMSProxy offerCRUDMSProxy;
	@Autowired
	private ProductBatchCRUDMSProxy ProductBatchCRUDMSProxy;
	@Autowired
	private RedistributionBeanDtoMapper redistributionBeanDtoMapper;
	@Autowired
	private ProductRequestBeanDtoMapper productRequestBeanDtoMapper;
	
	public ResponseEntity<Void> createRedistributionForRequest(int organisationId, List<ProductRequestBeanDto> productRequestBeanDtoList) {
		
		//Persistence of productRequests 
		List<Integer> productRequestIdList = persistProductRequestBean(productRequestBeanDtoList);
		
		//creation of new Redistribution (with returned product request id) 
		RedistributionBean redistributionBean = new RedistributionBean();
		redistributionBean.setOrganisationId(organisationId);
		redistributionBean.setProductRequestId(productRequestIdList);
		redistributionBean.setIsCompleted(false);
		
		//persistance of Redistribution 
		try {
			ResponseEntity<Void> resp = persistRedistribution(redistributionBean);
			return resp;
		} catch (Exception e) {
			Integer[]productRequestIdArray = productRequestIdList.toArray(new Integer[productRequestBeanDtoList.size()]);
			productRequestCRUDMSProxy.deleteProductRequestList(productRequestIdArray);
			throw new RedistributionCreationException("Probleme occured while building Redistribution Object");
		}
	}
	
	public ResponseEntity<Void> createRedistributionForOffer(int organisationId, OfferBeanDto offerBeanDto){
		
		//persist offerBean
		int offerBeanId = persistOfferBean(offerBeanDto);
		
		//creation of new Redistribution (with returned product request id) 
		RedistributionBean redistributionBean = new RedistributionBean();
		redistributionBean.setOrganisationId(organisationId);
		redistributionBean.setOfferId(offerBeanId);
		redistributionBean.setIsCompleted(false);
		
		//persistance of Redistribution 
		
		try {
			ResponseEntity<Void> resp = persistRedistribution(redistributionBean);
			return resp;
		} catch (Exception e) {
			offerCRUDMSProxy.deleteOffer(offerBeanId);
			throw new RedistributionCreationException("Probleme occured while building Redistribution Object");
		}
	}
	
	public ResponseEntity<Void> addOfferToRedistribution(int redistributionId, OfferBeanDto offerBeanDto){
		
		Integer offerId = null; 
		
		try {
			//retrieve redistribution from id 
			RedistributionBeanDto redistributionBeanDtoFromDB = redistributionCRUDMSProxy.getRedistributionById(redistributionId).getBody();
			
			//persist offer 
			ResponseEntity<Void> resp = offerCRUDMSProxy.addOffer(offerBeanDto);
			offerId = Integer.parseInt(ProductDispatchHelper.getURILastPart(resp.getHeaders().getLocation()));
			//update productbatch stat 
			updateProductBatchStat(offerBeanDto.getProductBatchIdList(), true);
			
			//add id to redistribution 
			redistributionBeanDtoFromDB.setOfferId(offerId);
			//update redistribution 
			redistributionCRUDMSProxy.updateRedistribution(redistributionBeanDtoFromDB);
			
		} catch ( ResponseStatusException rse) {
			throw new RedistributionCreationException("Probleme occured while building Redistribution Object");
		}
		
		//update redistribution crud 
		
		return ResponseEntity.ok().build();
	}
	
	//UTILITY METHOD 
	private List<Integer> persistProductRequestBean(List<ProductRequestBeanDto> productRequestBeanDtoList) throws ResponseStatusException {
		ResponseEntity<List<ProductRequestBeanDto>> productRequestBeanDtoPersistedListResp  = productRequestCRUDMSProxy.addProductRequestList(productRequestBeanDtoList);
		List<ProductRequestBean> productRequestBeanPersistedList = ProductDispatchHelper.ListDtoToListEntity(productRequestBeanDtoPersistedListResp.getBody(), productRequestBeanDtoMapper);
		List<Integer> productRequestIdList = new ArrayList<>();
		productRequestBeanPersistedList.forEach(e -> productRequestIdList.add(e.getId()));
		return productRequestIdList;
	}
	
	private Integer persistOfferBean(OfferBeanDto offerBeanDto) throws ResponseStatusException {
		ResponseEntity<Void> resp = offerCRUDMSProxy.addOffer(offerBeanDto);
		String[] path = resp.getHeaders().getLocation().getRawPath().split("/");
		return Integer.parseInt(path[path.length-1]);
	}
	
	private ResponseEntity<Void> persistRedistribution(RedistributionBean redistributionBean) throws ResponseStatusException {
		RedistributionBeanDto redistributionBeanDto = redistributionBeanDtoMapper.redistributionBeanToRedistributionBeanDto(redistributionBean);
		ResponseEntity<Void> resp = redistributionCRUDMSProxy.addRedistribution(redistributionBeanDto);
		if(resp.getStatusCode() != HttpStatus.CREATED) {
			System.out.println("EXCEPTION !!! ");
		}
		return resp;
	}
	
	private void updateProductBatchStat(List<Integer> productBatchDtoList, Boolean status) throws ResponseStatusException {
		for (Integer pbd : productBatchDtoList) {
			ProductBatchCRUDMSProxy.updateProductBatchIsAwaitingToBeCollectedStatus(pbd, status); 
		}
	}
	
	
}
