package com.biocycle.customerWebApp.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.customerWebApp.bean.organisation.OrganisationBean;
import com.biocycle.customerWebApp.dto.OrganisationBeanDto;

/**
 * The Interface OrganisationBeanDtoMapper.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface OrganisationBeanDtoMapper {
	
	/**
	 * Organisation bean to organisation bean dto.
	 *
	 * @param organisationBean the organisation bean
	 * @return the organisation bean dto
	 */
	OrganisationBeanDto organisationBeanToOrganisationBeanDto(OrganisationBean organisationBean);
	
	/**
	 * Organisation bean dto to organisation bean.
	 *
	 * @param organisationBean the organisation bean
	 * @return the organisation bean
	 */
	OrganisationBean organisationBeanDtoToOrganisationBean(OrganisationBeanDto organisationBean);
}
