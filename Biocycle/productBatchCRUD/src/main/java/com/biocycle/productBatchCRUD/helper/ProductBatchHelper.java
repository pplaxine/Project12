package com.biocycle.productBatchCRUD.helper;

import java.util.ArrayList;
import java.util.List;

import com.biocycle.productBatchCRUD.dto.ProductBatchDto;
import com.biocycle.productBatchCRUD.dto.mapper.ProductBatchDtoMapper;
import com.biocycle.productBatchCRUD.model.ProductBatch;

/**
 * The Class ProductBatchHelper.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
public class ProductBatchHelper {

	/**
	 * List entity to list dto.
	 *
	 * @param productBatchList the product batch list
	 * @param productBatchDtoMapper the product batch dto mapper
	 * @return the list
	 */
	public static List<ProductBatchDto> ListEntityToListDto(List<ProductBatch> productBatchList, ProductBatchDtoMapper productBatchDtoMapper) {
		List<ProductBatchDto> productBatchDtoList = new ArrayList<>();
		productBatchList.forEach( e -> {
			ProductBatchDto productBatchdto = productBatchDtoMapper.productBatchToProductBatchDto(e);
			productBatchDtoList.add(productBatchdto);
		});
		
		return productBatchDtoList;
	}
}
