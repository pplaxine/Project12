package com.biocycle.entWebApp.service;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biocycle.entWebApp.bean.productBatch.ProductBatchBean;
import com.biocycle.entWebApp.bean.storageContainer.StorageContainerBean;
import com.biocycle.entWebApp.dto.OrganisationBeanDto;
import com.biocycle.entWebApp.dto.ProductBatchBeanDto;
import com.biocycle.entWebApp.dto.StorageContainerBeanDto;
import com.biocycle.entWebApp.dto.mapper.ProductBatchBeanDtoMapper;
import com.biocycle.entWebApp.proxy.InventoryServiceProxy;
import com.biocycle.entWebApp.proxy.ProductBatchCRUDMSProxy;
import com.biocycle.entWebApp.proxy.StorageContainerCRUDMSProxy;

@Service
public class ProductManagmentManager {

	@Autowired
	private InventoryServiceProxy inventoryServiceProxy;
	@Autowired
	private CustomerManagmentManager customerManagmentManager;
	@Autowired
	private ProductBatchCRUDMSProxy productBatchCRUDMSProxy;
	@Autowired
	private StorageContainerCRUDMSProxy storageContainerCRUDMSProxy;
	
	public String productBatchForm(ProductBatchBeanDto productBatchBeanDto, Model model) {
		
		try {
			List<OrganisationBeanDto> organisationBeanDtoList = customerManagmentManager.getAllDonor();
			model.addAttribute("organisationBeanDtoList", organisationBeanDtoList);

		} catch (ResponseStatusException e) {
			String error ="Error occured while getting organisations names. Please try again.";
			model.addAttribute("error", error);
			return "productBatchForm";
		}
		return "productBatchForm";
	}
 
	public String createProductBatch(ProductBatchBeanDto productBatchBeanDto, Model model, RedirectAttributes red) {

		int numberOfContainer = productBatchBeanDto.getStorageContainerId().get(0);
		productBatchBeanDto.setStorageContainerId(null);
		productBatchBeanDto.setDateOfCollection(LocalDate.now());
		productBatchBeanDto.setIsAvailable(true);
		try {
			//persist productBatch 
			ResponseEntity<Void> resp = inventoryServiceProxy.createEntry(productBatchBeanDto, numberOfContainer);
			URI location = resp.getHeaders().getLocation();
			
			int productBatchId = Integer.parseInt(getURILastPart(location));
					
			//retrieve productBatch with id 
			ProductBatchBeanDto productBatchBeanDtoResp = productBatchCRUDMSProxy.findProductBatchById(productBatchId).getBody();
			List<Integer> storageContainerIdList = productBatchBeanDtoResp.getStorageContainerId();
			Integer[] containerIdArray = storageContainerIdList.toArray(new Integer[storageContainerIdList.size()]);
			
			
			//retrieve containers 
			Optional<List<StorageContainerBeanDto>> storageContainerBeanDtoList = storageContainerCRUDMSProxy.findStorageContainerFromIdList(containerIdArray);
			if(storageContainerBeanDtoList.isPresent()) {
				red.addFlashAttribute("storageContainerBeanDtoList", storageContainerBeanDtoList.get());
			}
			
			String info = "Entry successfully registered.";
			red.addFlashAttribute("info", info);
			return "redirect:/pme/entry-forms";
			
		} catch (ResponseStatusException rse) {
			
			List<OrganisationBeanDto> organisationBeanDtoList = customerManagmentManager.getAllDonor();
			model.addAttribute("organisationBeanDtoList", organisationBeanDtoList);
			
			if(rse.getStatus() == HttpStatus.INSUFFICIENT_STORAGE || rse.getStatus() == HttpStatus.NOT_FOUND) {
				String error ="No more storage space available.";
				model.addAttribute("error", error);
				return "productBatchForm";
			}else {
				String error ="Could not register your entry. Please try again.";
				model.addAttribute("error", error);
				return "productBatchForm";
			}
		} catch (Exception e) {
			
			List<OrganisationBeanDto> organisationBeanDtoList = customerManagmentManager.getAllDonor();
			model.addAttribute("organisationBeanDtoList", organisationBeanDtoList);
			String error= e.getLocalizedMessage();
			model.addAttribute("error", error);
			return "productBatchForm";
		}
	}
	
	//UTILITY METHODS 
	
	private String getURILastPart(URI location) {
		String[] parts = location.getPath().split("/");
		return parts[parts.length-1];
	}
	
	//retrieve container from productBatch
	
	
	
	
}
