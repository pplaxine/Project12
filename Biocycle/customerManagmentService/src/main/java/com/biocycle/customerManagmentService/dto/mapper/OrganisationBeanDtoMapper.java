package com.biocycle.customerManagmentService.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.customerManagmentService.bean.OrganisationBean;
import com.biocycle.customerManagmentService.dto.OrganisationBeanDto;

@Mapper(componentModel = "spring")
public interface OrganisationBeanDtoMapper {
	OrganisationBeanDto organisationBeanToOrganisationBeanDto(OrganisationBean organisation);
	OrganisationBean organisatonBeanDtoToOrganisationBean(OrganisationBeanDto organisationBeanDto);
}
