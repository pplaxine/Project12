package com.biocycle.organisationCRUD.helper;

import java.util.ArrayList;
import java.util.List;

import com.biocycle.organisationCRUD.dto.OrganisationDto;
import com.biocycle.organisationCRUD.dto.mapper.OrganisationDtoMapper;
import com.biocycle.organisationCRUD.model.Organisation;

// TODO: Auto-generated Javadoc
/**
 * The Class OrganisationHelper.
 */
public class OrganisationHelper {
	
	/**
	 * Entity list to dto list.
	 *
	 * @param organisationList the organisation list
	 * @param organisationDtoMapper the organisation dto mapper
	 * @return the list
	 */
	public static List<OrganisationDto> EntityListToDtoList(List<Organisation> organisationList, OrganisationDtoMapper organisationDtoMapper){
		
		List<OrganisationDto> organisationDtoList = new ArrayList<>();
		
		organisationList.forEach(e -> {
			OrganisationDto organisationDto = organisationDtoMapper.organisationToOrganisationDto(e);
			organisationDtoList.add(organisationDto);
		});
		
		return organisationDtoList;
	}
}
