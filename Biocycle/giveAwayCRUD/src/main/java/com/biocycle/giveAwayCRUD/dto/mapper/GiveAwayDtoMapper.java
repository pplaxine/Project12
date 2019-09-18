package com.biocycle.giveAwayCRUD.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.giveAwayCRUD.dto.ContainerDto;
import com.biocycle.giveAwayCRUD.dto.GiveAwayDto;
import com.biocycle.giveAwayCRUD.model.Container;
import com.biocycle.giveAwayCRUD.model.GiveAway;


/**
 * The Interface GiveAwayDtoMapper.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Mapper(componentModel = "spring")
public interface GiveAwayDtoMapper {
	
	/**
	 * Give away to give away dto.
	 *
	 * @param giveAway the give away
	 * @return the give away dto
	 */
	GiveAwayDto giveAwayToGiveAwayDto(GiveAway giveAway);
	
	/**
	 * Give away dto to give away.
	 *
	 * @param giveAwayDto the give away dto
	 * @return the give away
	 */
	GiveAway giveAwayDtoToGiveAway(GiveAwayDto giveAwayDto);
	
	/**
	 * Container to container dto.
	 *
	 * @param container the container
	 * @return the container dto
	 */
	ContainerDto containerToContainerDto(Container container);
	
	/**
	 * Container to container dto.
	 *
	 * @param containerDto the container dto
	 * @return the container
	 */
	Container containerToContainerDto(ContainerDto containerDto);
}
