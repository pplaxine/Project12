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

@RestController
public class ProductBatchController  {
	
	@Autowired
	private ProductBatchDao productBatchDao; 
	
	@Autowired
	private ProductBatchDtoMapper productBatchDtoMapper;
	
	@GetMapping(value = "/productbatches")
	public ResponseEntity<List<ProductBatchDto>> findAllProductBatch(){
		List<ProductBatch> productBatchList = productBatchDao.findAll();
		if(productBatchList == null || productBatchList.size() <= 0) {
			throw new ProductBatchNotFoundException("No product batch were found.");
		}
		List<ProductBatchDto> productBatchDtoList = ProductBatchHelper.ListEntityToListDto(productBatchList, productBatchDtoMapper);
		return ResponseEntity.ok(productBatchDtoList);
	}
	
	@GetMapping(value = "/productbatches/{id}")
	public ProductBatchDto findProductBatchById(@PathVariable int id){
		
		Optional<ProductBatch> productBatch = productBatchDao.findById(id);
		
		if(!productBatch.isPresent()) {
			throw new ProductBatchNotFoundException("ProductBatch with id: " + id + " does not exist.");
		}
		
		return productBatchDtoMapper.productBatchToProductBatchDto(productBatch.get());
	}
	
	@DeleteMapping(value = "/productbatches/{id}")
	public void deleteProdictBatch(@PathVariable int id) {
		productBatchDao.deleteById(id);
	}
	
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
	
	@PutMapping(value = "/productbatches")
	public void updateProductBatch(@RequestBody ProductBatchDto productBatchDto) {
		ProductBatch productBatch = productBatchDtoMapper.productBatchDtoToProductBatch(productBatchDto);
		productBatchDao.save(productBatch);
	}
}
