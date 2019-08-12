package com.biocycle.giveAwayCRUD.model.helper;

import java.util.ArrayList;
import java.util.List;

import com.biocycle.giveAwayCRUD.dto.GiveAwayDto;
import com.biocycle.giveAwayCRUD.dto.mapper.GiveAwayDtoMapper;
import com.biocycle.giveAwayCRUD.model.GiveAway;

public class GiveAwayHelper {
	
	public static List<GiveAwayDto> EntityListToDtoList(List<GiveAway> giveAwayList, GiveAwayDtoMapper giveAwayDtoMapper){
		List<GiveAwayDto> giveAwayDtoList = new ArrayList<>();
		
		giveAwayList.forEach( e -> { 
			GiveAwayDto giveAwayDto = giveAwayDtoMapper.giveAwayToGiveAwayDto(e);
			giveAwayDtoList.add(giveAwayDto);
		});
		
		return giveAwayDtoList;
	}
}
