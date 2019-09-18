package com.biocycle.redistributionCRUD.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.redistributionCRUD.dto.RedistributionDto;
import com.biocycle.redistributionCRUD.model.Redistribution;


/**
 * The Interface RedistributionDtoMapper.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface RedistributionDtoMapper {
	
	/**
	 * Redistribtion to redistribution dto.
	 *
	 * @param redistribution the redistribution
	 * @return the redistribution dto
	 */
	RedistributionDto RedistribtionToRedistributionDto(Redistribution redistribution);
	
	/**
	 * Redistribution dto to redistribution.
	 *
	 * @param redistributionDto the redistribution dto
	 * @return the redistribution
	 */
	Redistribution redistributionDtoToRedistribution(RedistributionDto redistributionDto);
}
