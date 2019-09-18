package com.biocycle.redistributionCRUD.helper;

import java.util.ArrayList;
import java.util.List;

import com.biocycle.redistributionCRUD.dto.RedistributionDto;
import com.biocycle.redistributionCRUD.dto.mapper.RedistributionDtoMapper;
import com.biocycle.redistributionCRUD.model.Redistribution;

/**
 * The Class RedistributionHelper.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
public class RedistributionHelper {
	
	/**
	 * Entity list to dto list.
	 *
	 * @param redistributionList the redistribution list
	 * @param redistributionDtoMapper the redistribution dto mapper
	 * @return the list
	 */
	public static List<RedistributionDto> entityListToDtoList(List<Redistribution> redistributionList, RedistributionDtoMapper redistributionDtoMapper) {
		List<RedistributionDto> redistributionDtoList = new ArrayList<>();
		redistributionList.forEach(e -> {
			RedistributionDto  redistributionDto = redistributionDtoMapper.RedistribtionToRedistributionDto(e);
			redistributionDtoList.add(redistributionDto);
		});
		return redistributionDtoList;
	}
}
