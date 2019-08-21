package com.biocycle.entWebApp.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.entWebApp.bean.organisation.OrganisationBean;
import com.biocycle.entWebApp.dto.OrganisationBeanDto;

@Mapper(componentModel = "spring")
public interface OrganisationBeanDtoMapper {
	OrganisationBeanDto organisationBeanToOrganisationBeanDto(OrganisationBean organisationBean);
	OrganisationBean organisationBeanDtoToOrganisationBean(OrganisationBeanDto organisationBean);
}
