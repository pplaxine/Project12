package com.biocycle.productBatchCRUD.controller;

import java.net.URI;
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
import com.biocycle.productBatchCRUD.exception.ProductBatchNotFoundException;
import com.biocycle.productBatchCRUD.model.ProductBatch;

@RestController
public class ProductBatchController  {
	
	@Autowired
	private ProductBatchDao productBatchDao; 
	
	@GetMapping(value = "/productbatches/{id}")
	public Optional<ProductBatch> findProductBatchById(@PathVariable int id){
		
		Optional<ProductBatch> productBatch = productBatchDao.findById(id);
		
		if(!productBatch.isPresent()) {
			throw new ProductBatchNotFoundException("ProductBatch with id: " + id + " does not exist.");
		}
		
		return productBatch;
	}
	
	@DeleteMapping(value = "/productbatches/{id}")
	public void deleteProdictBatch(@PathVariable int id) {
		productBatchDao.deleteById(id);
	}
	
	@PostMapping(value = "/productbatches")
	public ResponseEntity<Void> addProductBatch(@RequestBody ProductBatch productBatch){
		
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
	public void updateProductBatch(@RequestBody ProductBatch productBatch) {
		productBatchDao.save(productBatch);
	}
}
