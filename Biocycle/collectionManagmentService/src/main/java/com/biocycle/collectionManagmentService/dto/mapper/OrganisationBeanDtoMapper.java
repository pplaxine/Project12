package com.biocycle.collectionManagmentService.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.collectionManagmentService.bean.OrganisationBean;
import com.biocycle.collectionManagmentService.dto.OrganisationBeanDto;

@Mapper(componentModel = "spring")
public interface OrganisationBeanDtoMapper {
	
	OrganisationBeanDto organisationBeanToOrganisationBeanDto(OrganisationBean organisationBean);
	OrganisationBean organisationBeanDtoToOrganisationBean(OrganisationBeanDto organisationBeanDto);
}
