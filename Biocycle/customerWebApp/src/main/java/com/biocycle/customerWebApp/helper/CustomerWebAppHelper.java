package com.biocycle.customerWebApp.helper;

import java.util.ArrayList;
import java.util.List;

import com.biocycle.customerWebApp.bean.giveAway.GiveAwayBean;
import com.biocycle.customerWebApp.dto.GiveAwayBeanDto;
import com.biocycle.customerWebApp.dto.mapper.GiveAwayBeanDtoMapper;

public class CustomerWebAppHelper {
	
	public static List<GiveAwayBean> dtoListToBeanList(List<GiveAwayBeanDto> giveAwayBeanDtoList, GiveAwayBeanDtoMapper giveAwayBeanDtoMapper) {
		List<GiveAwayBean> giveAwayBeanList = new ArrayList<>();
		giveAwayBeanDtoList.forEach(e -> {
			GiveAwayBean giveAwayBean = giveAwayBeanDtoMapper.giveAwayBeanDtoToGiveAwayBean(e);
			giveAwayBeanList.add(giveAwayBean);
		});
		return giveAwayBeanList;
	}
}
