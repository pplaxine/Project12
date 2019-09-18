package com.biocycle.entWebApp.helper;

import java.util.ArrayList;
import java.util.List;

import com.biocycle.entWebApp.bean.organisation.OrganisationBean;
import com.biocycle.entWebApp.dto.ContainerDto;
import com.biocycle.entWebApp.dto.GiveAwayBeanDto;
import com.biocycle.entWebApp.dto.OrganisationBeanDto;
import com.biocycle.entWebApp.dto.ProductRequestBeanDto;
import com.biocycle.entWebApp.dto.RedistributionBeanDto;
import com.biocycle.entWebApp.dto.mapper.OrganisationBeanDtoMapper;
import com.biocycle.entWebApp.dto.view.GiveAwayViewDto;
import com.biocycle.entWebApp.dto.view.OfferViewDto;
import com.biocycle.entWebApp.dto.view.RedistributionViewDto;

/**
 * The Class EntWebAppHelper.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
public class EntWebAppHelper {

	
	/**
	 * Dto list to entity list.
	 *
	 * @param organisationBeanDtoList the organisation bean dto list
	 * @param organisationBeanDtoMapper the organisation bean dto mapper
	 * @return the list
	 */
	public static List<OrganisationBean> dtoListToEntityList(List<OrganisationBeanDto> organisationBeanDtoList, OrganisationBeanDtoMapper organisationBeanDtoMapper){
		List<OrganisationBean> organisationListBean = new ArrayList<>();
		organisationBeanDtoList.forEach(e -> {
			OrganisationBean organisationBean = organisationBeanDtoMapper.organisationBeanDtoToOrganisationBean(e);
			organisationListBean.add(organisationBean);
		});
		return organisationListBean;
	}
	
	
	/**
	 * Redistribution view builder.
	 *
	 * @param redistributionBeanDto the redistribution bean dto
	 * @param offerViewDto the offer view dto
	 * @param productRequestBeanDtoList the product request bean dto list
	 * @param organisationBeanDto the organisation bean dto
	 * @return the redistribution view dto
	 */
	public static RedistributionViewDto redistributionViewBuilder(RedistributionBeanDto redistributionBeanDto, OfferViewDto offerViewDto, List<ProductRequestBeanDto> productRequestBeanDtoList, OrganisationBeanDto organisationBeanDto){
		RedistributionViewDto redistributionViewDto = new RedistributionViewDto();
		if(redistributionBeanDto == null) {
			return null;
		}
		redistributionViewDto.setId(redistributionBeanDto.getId());
		redistributionViewDto.setIsCompleted(redistributionBeanDto.getIsCompleted());
		
		if(offerViewDto != null) {
			redistributionViewDto.setOfferViewDto(offerViewDto);
		}
		if(productRequestBeanDtoList != null) {
			redistributionViewDto.setProductRequestBeanDtoList(productRequestBeanDtoList);
		}
		if(organisationBeanDto != null) {
			redistributionViewDto.setOrganisationBeanDto(organisationBeanDto);
		}
		
		return redistributionViewDto;
	}
	
	/**
	 * Give away view builder.
	 *
	 * @param organisationBeanDto the organisation bean dto
	 * @param giveAwayBeanDto the give away bean dto
	 * @return the give away view dto
	 */
	public static GiveAwayViewDto giveAwayViewBuilder(OrganisationBeanDto organisationBeanDto, GiveAwayBeanDto giveAwayBeanDto) {
		if(organisationBeanDto == null || giveAwayBeanDto == null) {
			return null;
		}

		GiveAwayViewDto gavd = new GiveAwayViewDto();
		gavd.setOrganisationBeanDto(organisationBeanDto);
		gavd.setId(giveAwayBeanDto.getId());
		if(giveAwayBeanDto.getCollectionSpotAddress() != null) {
			gavd.setCollectionSpotAddress(giveAwayBeanDto.getCollectionSpotAddress());
		}
		if(giveAwayBeanDto.getAvailableToBeCollectedFrom() != null) {
			gavd.setAvailableToBeCollectedFrom(giveAwayBeanDto.getAvailableToBeCollectedFrom());
		}
		if(giveAwayBeanDto.getCollectionDate() != null) {
			gavd.setCollectionDate(giveAwayBeanDto.getCollectionDate());
		}
		if(giveAwayBeanDto.getContainerList() != null) {
			List<ContainerDto> giveAwayBeanDtoList = giveAwayBeanDto.getContainerList();
			gavd.setContainerList(giveAwayBeanDtoList);
			if(!giveAwayBeanDtoList.isEmpty()) {
				gavd.setContainerValidationDone(true);
				giveAwayBeanDtoList.forEach(e -> {
					if(e.getAccepted() == null) {
						gavd.setContainerValidationDone(false);
					}
				});
			}
			
		}
		if(giveAwayBeanDto.getIsCollected() != null) {
			
			if(gavd.getContainerList() != null && !gavd.getContainerList().isEmpty()) {
				gavd.setIsCollected(true);
				gavd.getContainerList().forEach(e -> {
					if(e.getAccepted() == null || e.getAccepted() != false) {
						gavd.setIsCollected(giveAwayBeanDto.getIsCollected());
					}
				});
			}
			
		}
		
		
		return gavd;
	}
}













