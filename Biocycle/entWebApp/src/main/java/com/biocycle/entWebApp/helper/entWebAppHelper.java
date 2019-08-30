package com.biocycle.entWebApp.helper;

import java.util.ArrayList;
import java.util.List;

import com.biocycle.entWebApp.bean.organisation.OrganisationBean;
import com.biocycle.entWebApp.dto.OfferBeanDto;
import com.biocycle.entWebApp.dto.OrganisationBeanDto;
import com.biocycle.entWebApp.dto.ProductRequestBeanDto;
import com.biocycle.entWebApp.dto.RedistributionBeanDto;
import com.biocycle.entWebApp.dto.mapper.OrganisationBeanDtoMapper;
import com.biocycle.entWebApp.dto.view.OfferViewDto;
import com.biocycle.entWebApp.dto.view.RedistributionViewDto;

public class entWebAppHelper {

	
	public static List<OrganisationBean> dtoListToEntityList(List<OrganisationBeanDto> organisationBeanDtoList, OrganisationBeanDtoMapper organisationBeanDtoMapper){
		List<OrganisationBean> organisationListBean = new ArrayList<>();
		organisationBeanDtoList.forEach(e -> {
			OrganisationBean organisationBean = organisationBeanDtoMapper.organisationBeanDtoToOrganisationBean(e);
			organisationListBean.add(organisationBean);
		});
		return organisationListBean;
	}
	
	
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
	
}
