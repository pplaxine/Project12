package com.biocycle.giveAwayCRUD.model.helper;

import java.util.ArrayList;
import java.util.List;

import com.biocycle.giveAwayCRUD.dto.GiveAwayDto;
import com.biocycle.giveAwayCRUD.dto.mapper.GiveAwayDtoMapper;
import com.biocycle.giveAwayCRUD.model.GiveAway;


/**
 * The Class GiveAwayHelper.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
public class GiveAwayHelper {
	
	/**
	 * Entity list to dto list.
	 *
	 * @param giveAwayList the give away list
	 * @param giveAwayDtoMapper the give away dto mapper
	 * @return the list
	 */
	public static List<GiveAwayDto> EntityListToDtoList(List<GiveAway> giveAwayList, GiveAwayDtoMapper giveAwayDtoMapper){
		List<GiveAwayDto> giveAwayDtoList = new ArrayList<>();
		
		giveAwayList.forEach( e -> { 
			GiveAwayDto giveAwayDto = giveAwayDtoMapper.giveAwayToGiveAwayDto(e);
			giveAwayDtoList.add(giveAwayDto);
		});
		
		return giveAwayDtoList;
	}
}
