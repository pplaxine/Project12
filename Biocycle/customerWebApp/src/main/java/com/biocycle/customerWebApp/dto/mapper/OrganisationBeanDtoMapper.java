package com.biocycle.customerWebApp.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.customerWebApp.bean.organisation.OrganisationBean;
import com.biocycle.customerWebApp.dto.OrganisationBeanDto;

@Mapper(componentModel = "spring")
public interface OrganisationBeanDtoMapper {
	OrganisationBeanDto organisationBeanToOrganisationBeanDto(OrganisationBean organisationBean);
	OrganisationBean organisationBeanDtoToOrganisationBean(OrganisationBeanDto organisationBean);
}
