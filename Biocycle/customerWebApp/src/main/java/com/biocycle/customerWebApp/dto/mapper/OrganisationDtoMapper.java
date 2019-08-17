package com.biocycle.customerWebApp.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.customerWebApp.bean.organisation.OrganisationBean;
import com.biocycle.customerWebApp.dto.OrganisationBeanDto;

@Mapper(componentModel = "spring")
public interface OrganisationDtoMapper {
	OrganisationBeanDto organisationToOrganisationDto(OrganisationBean organisation);
	OrganisationBean organisationDtoToOrganisation(OrganisationBeanDto organisation);
}
