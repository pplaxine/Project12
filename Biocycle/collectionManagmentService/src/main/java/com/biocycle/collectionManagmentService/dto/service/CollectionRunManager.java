package com.biocycle.collectionManagmentService.dto.service;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.internal.util.CollectionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.biocycle.collectionManagmentService.bean.CollectionRunBean;
import com.biocycle.collectionManagmentService.bean.GiveAwayBean;
import com.biocycle.collectionManagmentService.bean.OrganisationBean;
import com.biocycle.collectionManagmentService.dto.GiveAwayBeanDto;
import com.biocycle.collectionManagmentService.dto.OrganisationBeanDto;
import com.biocycle.collectionManagmentService.dto.helper.CollectionManagmentHelper;
import com.biocycle.collectionManagmentService.dto.mapper.GiveAwayBeanDtoMapper;
import com.biocycle.collectionManagmentService.dto.mapper.OrganisationBeanDtoMapper;
import com.biocycle.collectionManagmentService.dto.proxy.GiveAwayCRUDMSProxy;
import com.biocycle.collectionManagmentService.dto.proxy.OrganisationCRUDMSProxy;

@Service
public class CollectionRunManager {

	@Autowired
	private GiveAwayCRUDMSProxy giveAwayCRUDMSProxy; 
	@Autowired
	private OrganisationCRUDMSProxy organisationCRUDMSProxy;
	@Autowired
	private GiveAwayBeanDtoMapper giveAwayBeanDtoMapper;
	@Autowired
	private OrganisationBeanDtoMapper organisationBeanDtoMapper;
	
	private final int CONTAINER_PER_TRICYCLE = 8;
	private final int MAX_HOURS_PER_DAY = 7;
	
	public List<CollectionRunBean> getList(Date date){
		
		LocalTime maxWorkingHoursADay = LocalTime.of(MAX_HOURS_PER_DAY, 0);
		
		CollectionRunBean collectionRunBean = new CollectionRunBean();
		
		//--- RETRIEVE ALL GIVE AWAYS FOR THE DAY 
		ResponseEntity<List<GiveAwayBeanDto>> activeGiveAwayBeanDtoListResp = giveAwayCRUDMSProxy.getAllActiveGiveAwayByDate(date);
		
		if(activeGiveAwayBeanDtoListResp.getStatusCode() == HttpStatus.NOT_FOUND) {
			//TODO exception NOT FOUND  
		}else if(activeGiveAwayBeanDtoListResp.getStatusCode() != HttpStatus.OK) {
			//TODO exception 
		}
		List<GiveAwayBean> activeGiveAwayBeanList = CollectionManagmentHelper.listDtoToListEntity(activeGiveAwayBeanDtoListResp.getBody(), giveAwayBeanDtoMapper);
		
		
		//--- FOR EACH GIVE AWAY 
		for (GiveAwayBean gab : activeGiveAwayBeanList) {
			
			if(collectionRunBean.getContainerIdList().size() <= CONTAINER_PER_TRICYCLE && collectionRunBean.getGlobalEstimatedTime().isBefore(maxWorkingHoursADay)) {
				ResponseEntity<OrganisationBeanDto> obResp = organisationCRUDMSProxy.getOrgnisationBeanById(gab.getOrganisationId());
				if(obResp.getStatusCode() == HttpStatus.NO_CONTENT) {
					//TODO exception 
				}else if(obResp.getStatusCode() != HttpStatus.OK) {
					//TODO exception
				}
				
				OrganisationBean organisationBean = organisationBeanDtoMapper.organisationBeanDtoToOrganisationBean(obResp.getBody());
				
				
				//get estimation from a to B 
				//
				
				
				//dispatch container by collectionRun 
				
				//see how many collectionRun for a day work 
			}else{
				collectionRunBean = new CollectionRunBean();
				collectionRunBean.setCollectionRunDate(date);
			}
			
			
		}
				
				
			
			
			
			
	
		
		return null;
	}
	
}
