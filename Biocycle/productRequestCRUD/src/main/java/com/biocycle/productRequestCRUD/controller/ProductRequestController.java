package com.biocycle.productRequestCRUD.controller;

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

import com.biocycle.productRequestCRUD.dao.ProductRequestDao;
import com.biocycle.productRequestCRUD.exception.ProductRequestNotFoundException;
import com.biocycle.productRequestCRUD.model.ProductRequest;

@RestController
public class ProductRequestController {
	
	@Autowired
	ProductRequestDao productRequestDao;
	
	@GetMapping(value = "/productrequests/{id}")
	public Optional<ProductRequest> findProductRequestById(@PathVariable int id){
		Optional<ProductRequest> productRequest = productRequestDao.findById(id);
		
		if(!productRequest.isPresent()) {
			throw new ProductRequestNotFoundException("productRequest with id: " + id + " does not exist.");
		}
		
		return productRequest;
	}
	
	@DeleteMapping(value = "/productrequests/{id}")
	public void deleteProductRequest(@PathVariable int id) {
		productRequestDao.deleteById(id);
	}
	
	@PostMapping(value = "/productrequests")
	public ResponseEntity<Void> addProductRequest(@RequestBody ProductRequest productRequest){
		ProductRequest pr = productRequestDao.save(productRequest);
		if(pr == null) {
			return ResponseEntity.noContent().build();
		}
		
		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(pr.getId())
						.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping(value = "/productrequests")
	public void updateProductRequest(@RequestBody ProductRequest productRequest) {
		productRequestDao.save(productRequest);
	}
}
