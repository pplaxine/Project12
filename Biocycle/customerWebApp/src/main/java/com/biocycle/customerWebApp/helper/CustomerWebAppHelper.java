package com.biocycle.customerWebApp.helper;

import java.util.ArrayList;
import java.util.List;

import com.biocycle.customerWebApp.bean.giveAway.GiveAwayBean;
import com.biocycle.customerWebApp.dto.GiveAwayBeanDto;
import com.biocycle.customerWebApp.dto.mapper.GiveAwayBeanDtoMapper;

/**
 * The Class CustomerWebAppHelper.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
public class CustomerWebAppHelper {
	
	/**
	 * Dto list to bean list.
	 *
	 * @param giveAwayBeanDtoList the give away bean dto list
	 * @param giveAwayBeanDtoMapper the give away bean dto mapper
	 * @return the list
	 */
	public static List<GiveAwayBean> dtoListToBeanList(List<GiveAwayBeanDto> giveAwayBeanDtoList, GiveAwayBeanDtoMapper giveAwayBeanDtoMapper) {
		List<GiveAwayBean> giveAwayBeanList = new ArrayList<>();
		giveAwayBeanDtoList.forEach(e -> {
			GiveAwayBean giveAwayBean = giveAwayBeanDtoMapper.giveAwayBeanDtoToGiveAwayBean(e);
			giveAwayBeanList.add(giveAwayBean);
		});
		return giveAwayBeanList;
	}
}
