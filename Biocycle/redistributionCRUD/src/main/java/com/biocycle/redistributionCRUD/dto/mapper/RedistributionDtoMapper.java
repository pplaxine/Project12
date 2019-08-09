package com.biocycle.redistributionCRUD.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.redistributionCRUD.dto.RedistributionDto;
import com.biocycle.redistributionCRUD.model.Redistribution;

@Mapper(componentModel = "spring")
public interface RedistributionDtoMapper {
	
	RedistributionDto RedistribtionToRedistributionDto(Redistribution redistribution);
	Redistribution redistributionDtoToRedistribution(RedistributionDto redistributionDto);
}
