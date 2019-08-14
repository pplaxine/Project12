package com.biocycle.productRequestCRUD.helper;

import java.util.ArrayList;
import java.util.List;

import com.biocycle.productRequestCRUD.dto.ProductRequestDto;
import com.biocycle.productRequestCRUD.dto.mapper.ProductRequestDtoMapper;
import com.biocycle.productRequestCRUD.model.ProductRequest;

public class ProductRequestHelper {
	
	public static List<ProductRequest> dtoListToEntityList(List<ProductRequestDto> productRequestDtoList, ProductRequestDtoMapper productRequestDtoMapper){
		List<ProductRequest> productRequestList = new ArrayList<>();
		productRequestDtoList.forEach(e -> {
			ProductRequest productRequest = productRequestDtoMapper.productRequestDtoToProductRequest(e);
			productRequestList.add(productRequest);
		});
		return productRequestList;
	}
}
