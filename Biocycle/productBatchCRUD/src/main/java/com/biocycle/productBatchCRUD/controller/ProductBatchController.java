package com.biocycle.productBatchCRUD.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.biocycle.productBatchCRUD.dao.ProductBatchDao;
import com.biocycle.productBatchCRUD.dto.ProductBatchDto;
import com.biocycle.productBatchCRUD.dto.mapper.ProductBatchDtoMapper;
import com.biocycle.productBatchCRUD.exception.ProductBatchNotFoundException;
import com.biocycle.productBatchCRUD.helper.ProductBatchHelper;
import com.biocycle.productBatchCRUD.model.ProductBatch;

/**
 * The Class ProductBatchController.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@RestController
public class ProductBatchController  {
	
	/** The product batch dao. */
	@Autowired
	private ProductBatchDao productBatchDao; 
	
	/** The product batch dto mapper. */
	@Autowired
	private ProductBatchDtoMapper productBatchDtoMapper;
	
	/**
	 * Find all product batch.
	 *
	 * @return the response entity
	 */
	@GetMapping(value = "/productbatches")
	public ResponseEntity<List<ProductBatchDto>> findAllProductBatch(){
		List<ProductBatch> productBatchList = productBatchDao.findAll();
		if(productBatchList == null || productBatchList.size() <= 0) {
			throw new ProductBatchNotFoundException("No product batch were found.");
		}
		List<ProductBatchDto> productBatchDtoList = ProductBatchHelper.ListEntityToListDto(productBatchList, productBatchDtoMapper);
		return ResponseEntity.ok(productBatchDtoList);
	}
	
	/**
	 * Find product batch by id.
	 *
	 * @param id the id
	 * @return the product batch dto
	 */
	@GetMapping(value = "/productbatches/{id}")
	public ProductBatchDto findProductBatchById(@PathVariable int id){
		
		Optional<ProductBatch> productBatch = productBatchDao.findById(id);
		
		if(!productBatch.isPresent()) {
			throw new ProductBatchNotFoundException("ProductBatch with id: " + id + " does not exist.");
		}
		
		return productBatchDtoMapper.productBatchToProductBatchDto(productBatch.get());
	}
	
	/**
	 * Gets the product soon to expire.
	 *
	 * @return the product soon to expire
	 */
	@GetMapping(value = "/productbatches/soon/expired")
	public ResponseEntity<List<ProductBatchDto>> getProductSoonToExpire(){
		List<ProductBatch> productBatchList = productBatchDao.findTop15ByIsAvailableAndIsAwaitingForCollectionOrderByToBeUsedByAsc(true, false);
		if(productBatchList == null || productBatchList.size() <= 0) {
			throw new ProductBatchNotFoundException("No product batch were found.");
		}
		List<ProductBatchDto> productBatchDtoList = ProductBatchHelper.ListEntityToListDto(productBatchList, productBatchDtoMapper);
		return ResponseEntity.ok(productBatchDtoList);
	}
	
	/**
	 * Delete prodict batch.
	 *
	 * @param id the id
	 */
	@DeleteMapping(value = "/productbatches/{id}")
	public void deleteProdictBatch(@PathVariable int id) {
		productBatchDao.deleteById(id);
	}
	
	/**
	 * Adds the product batch.
	 *
	 * @param productBatchDto the product batch dto
	 * @return the response entity
	 */
	@PostMapping(value = "/productbatches")
	public ResponseEntity<Void> addProductBatch(@RequestBody ProductBatchDto productBatchDto){
		
		ProductBatch productBatch = productBatchDtoMapper.productBatchDtoToProductBatch(productBatchDto);
		
		ProductBatch pb = productBatchDao.save(productBatch);
		if(pb == null) {
			return ResponseEntity.noContent().build();
		}
		
		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(pb.getId())
						.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	/**
	 * Update product batch.
	 *
	 * @param productBatchDto the product batch dto
	 */
	@PutMapping(value = "/productbatches")
	public void updateProductBatch(@RequestBody ProductBatchDto productBatchDto) {
		ProductBatch productBatch = productBatchDtoMapper.productBatchDtoToProductBatch(productBatchDto);
		productBatchDao.save(productBatch);
	}
	
	/**
	 * Update product batch is awaiting to be collected status.
	 *
	 * @param productBatchId the product batch id
	 * @param status the status
	 * @return the response entity
	 */
	@PutMapping(value = "/productbatches/is-awaiting-for-collection/{productBatchId}/{status}")
	public ResponseEntity<Void> updateProductBatchIsAwaitingToBeCollectedStatus(@PathVariable int productBatchId, @PathVariable Boolean status) {
		
		Optional <ProductBatch> productBatchOpt = productBatchDao.findById(productBatchId);
		if(!productBatchOpt.isPresent()) {
			return ResponseEntity.noContent().build();
		} 
		
		ProductBatch productBatchFromDB =  productBatchOpt.get(); 
		productBatchFromDB.setIsAwaitingForCollection(status);
		productBatchDao.save(productBatchFromDB);
		
		return ResponseEntity.ok().build(); 
	}
	
}
