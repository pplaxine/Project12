package com.biocycle.productDispatchService.helper;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.biocycle.productDispatchService.bean.ProductRequestBean;
import com.biocycle.productDispatchService.dto.ProductRequestBeanDto;
import com.biocycle.productDispatchService.dto.mapper.ProductRequestBeanDtoMapper;

/**
 * The Class ProductDispatchHelper.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
public class ProductDispatchHelper {
	
	/**
	 * List dto to list entity.
	 *
	 * @param productRequestBeanDtoList the product request bean dto list
	 * @param productRequestBeanDtoMapper the product request bean dto mapper
	 * @return the list
	 */
	public static List<ProductRequestBean> ListDtoToListEntity(List<ProductRequestBeanDto> productRequestBeanDtoList, ProductRequestBeanDtoMapper productRequestBeanDtoMapper){
		
		List<ProductRequestBean> productRequestBeanList = new ArrayList<>();
		
		productRequestBeanDtoList.forEach(e -> {
			ProductRequestBean productRequestBean = productRequestBeanDtoMapper.productRequestBeanDtoToProductRequestBean(e);
			productRequestBeanList.add(productRequestBean);
		});
		return productRequestBeanList;
	}
	
	/**
	 * Gets the URI last part.
	 *
	 * @param location the location
	 * @return the URI last part
	 */
	public static String getURILastPart(URI location) {
		String[] parts = location.getPath().split("/");
		return parts[parts.length-1];
	}
}
