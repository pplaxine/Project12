package com.biocycle.entWebApp.feign;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import feign.Response;
import feign.codec.ErrorDecoder;

@Component
public class FeignErrorDecoder implements ErrorDecoder{

	@Override
	public Exception decode(String methodKey, Response response) {
		
		//StaffCRUDMSProxy
		if(methodKey.equals("StaffCRUDMSProxy#findStaffByUserName(String)") && response.status() == 404) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "StaffCRUDMSProxy#findStaffByUserName(String) replied : Staff not found.");
		}
		
		//OrganisationCRUDMSProxy
		if(methodKey.equals("OrganisationCRUDMSProxy#findAllOrganisationByIsValidated(boolean)") && response.status() == 404) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "OrganisationCRUDMSProxy#findAllOrganisationByIsValidated(boolean) replied : No organisation found.");
		}
		if(methodKey.equals("OrganisationCRUDMSProxy#findOrganisationById(int)") && response.status() == 404) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "OrganisationCRUDMSProxy#findOrganisationById(int) replied : No organisation found.");
		}
		
		//InventoryServiceProxy
		if(methodKey.equals("InventoryServiceProxy#createEntry(ProductBatchBeanDto,int)") && response.status() == 404) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "InventoryServiceProxy#createEntry(ProductBatchBeanDto, int) replied : Not found.");
		}
		if(methodKey.equals("InventoryServiceProxy#createEntry(ProductBatchBeanDto,int)") && response.status() == 507) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "InventoryServiceProxy#createEntry(ProductBatchBeanDto, int) replied : There is not enough free storage container space available.");
		}
		if(methodKey.equals("InventoryServiceProxy#createEntry(ProductBatchBeanDto,int)") && response.status() == 418) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "InventoryServiceProxy#createEntry(ProductBatchBeanDto, int) replied : ContraintViolationException.");
		}
		
		//ProductBatchCRUDMSProxy
		if(methodKey.equals("ProductBatchCRUDMSProxy#findProductBatchById(int)") && response.status() == 404) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "ProductBatchCRUDMSProxy#findProductBatchById(int) replied : Not found.");
		}
		if(methodKey.equals("ProductBatchCRUDMSProxy#findAllProductBatch()") && response.status() == 404) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "ProductBatchCRUDMSProxy#findAllProductBatch() replied : Not found.");
		}
		
		//RedistributionCRUDMSProxy
		if(methodKey.equals("RedistributionCRUDMSProxy#findAllActiveRedistributions()") && response.status() == 404) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "RedistributionCRUDMSProxy#findAllActiveRedistributions() replied : Not found.");
		}
		
		//ProductRequestCRUDMSProxy
		if(methodKey.equals("ProductRequestCRUDMSProxy#findProductRequestById(int)") && response.status() == 404) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "ProductRequestCRUDMSProxy#findProductRequestById(int) replied : Not found.");
		}
		
		//OfferCRUDMSProxy
		if(methodKey.equals("OfferCRUDMSProxy#findOfferById(int)") && response.status() == 404) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "OfferCRUDMSProxy#findOfferById(int) replied : Not found.");
		}
		
		//ProductDispatchServiceProxy
		if(methodKey.equals("ProductDispatchServiceProxy#updateRedistributionWithoffer(int, OfferBeanDto)") && response.status() == 424) {
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "ProductDispatchServiceProxy#updateRedistributionWithoffer(int, OfferBeanDto) replied : Error while creating Redistribution or Offer.");
		}
		
		return new Exception("Feign Exception : Status " + response.status() + " . Error while accessing " + methodKey); 
	}
	
}
