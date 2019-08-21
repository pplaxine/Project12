package com.biocycle.entWebApp.helper;

import java.util.ArrayList;
import java.util.List;

import com.biocycle.entWebApp.bean.organisation.OrganisationBean;
import com.biocycle.entWebApp.dto.OrganisationBeanDto;
import com.biocycle.entWebApp.dto.mapper.OrganisationBeanDtoMapper;

public class entWebAppHelper {

	
	public static List<OrganisationBean> dtoListToEntityList(List<OrganisationBeanDto> organisationBeanDtoList, OrganisationBeanDtoMapper organisationBeanDtoMapper){
		List<OrganisationBean> organisationListBean = new ArrayList<>();
		organisationBeanDtoList.forEach(e -> {
			OrganisationBean organisationBean = organisationBeanDtoMapper.organisationBeanDtoToOrganisationBean(e);
			organisationListBean.add(organisationBean);
		});
		return organisationListBean;
	}
	
}
