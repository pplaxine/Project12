package com.biocycle.productDispatchService.helper;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.biocycle.productDispatchService.bean.ProductRequestBean;
import com.biocycle.productDispatchService.dto.ProductRequestBeanDto;
import com.biocycle.productDispatchService.dto.mapper.ProductRequestBeanDtoMapper;

public class ProductDispatchHelper {
	
	public static List<ProductRequestBean> ListDtoToListEntity(List<ProductRequestBeanDto> productRequestBeanDtoList, ProductRequestBeanDtoMapper productRequestBeanDtoMapper){
		
		List<ProductRequestBean> productRequestBeanList = new ArrayList<>();
		
		productRequestBeanDtoList.forEach(e -> {
			ProductRequestBean productRequestBean = productRequestBeanDtoMapper.productRequestBeanDtoToProductRequestBean(e);
			productRequestBeanList.add(productRequestBean);
		});
		return productRequestBeanList;
	}
	
	public static String getURILastPart(URI location) {
		String[] parts = location.getPath().split("/");
		return parts[parts.length-1];
	}
}
