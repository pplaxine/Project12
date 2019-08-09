package com.biocycle.giveAwayCRUD.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.giveAwayCRUD.dto.ContainerDto;
import com.biocycle.giveAwayCRUD.dto.GiveAwayDto;
import com.biocycle.giveAwayCRUD.model.Container;
import com.biocycle.giveAwayCRUD.model.GiveAway;

@Mapper(componentModel = "spring")
public interface GiveAwayDtoMapper {
	
	GiveAwayDto giveAwayToGiveAwayDto(GiveAway giveAway);
	GiveAway giveAwayDtoToGiveAway(GiveAwayDto giveAwayDto);
	
	ContainerDto containerToContainerDto(Container container);
	Container containerToContainerDto(ContainerDto containerDto);
}
