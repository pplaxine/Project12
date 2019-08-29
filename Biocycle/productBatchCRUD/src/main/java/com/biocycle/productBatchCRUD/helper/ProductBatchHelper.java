package com.biocycle.productBatchCRUD.helper;

import java.util.ArrayList;
import java.util.List;

import com.biocycle.productBatchCRUD.dto.ProductBatchDto;
import com.biocycle.productBatchCRUD.dto.mapper.ProductBatchDtoMapper;
import com.biocycle.productBatchCRUD.model.ProductBatch;

public class ProductBatchHelper {

	public static List<ProductBatchDto> ListEntityToListDto(List<ProductBatch> productBatchList, ProductBatchDtoMapper productBatchDtoMapper) {
		List<ProductBatchDto> productBatchDtoList = new ArrayList<>();
		productBatchList.forEach( e -> {
			ProductBatchDto productBatchdto = productBatchDtoMapper.productBatchToProductBatchDto(e);
			productBatchDtoList.add(productBatchdto);
		});
		
		return productBatchDtoList;
	}
}
