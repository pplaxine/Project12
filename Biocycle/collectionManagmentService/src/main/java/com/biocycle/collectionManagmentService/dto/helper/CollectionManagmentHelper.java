package com.biocycle.collectionManagmentService.dto.helper;

import java.util.ArrayList;
import java.util.List;

import com.biocycle.collectionManagmentService.bean.GiveAwayBean;
import com.biocycle.collectionManagmentService.dto.GiveAwayBeanDto;
import com.biocycle.collectionManagmentService.dto.mapper.GiveAwayBeanDtoMapper;

public class CollectionManagmentHelper {
	
	public static List<GiveAwayBean> listDtoToListEntity(List<GiveAwayBeanDto> giveAwayBeanDtoList, GiveAwayBeanDtoMapper giveAwayBeanDtoMapper){
		
		List<GiveAwayBean> giveAwayBeanList = new ArrayList<>();
	
		giveAwayBeanDtoList.forEach(e -> {
			GiveAwayBean giveAwayBean = giveAwayBeanDtoMapper.giveAwayBeanDtoToGiveawayBean(e);
			giveAwayBeanList.add(giveAwayBean);
		});
		return giveAwayBeanList;
	}
}
