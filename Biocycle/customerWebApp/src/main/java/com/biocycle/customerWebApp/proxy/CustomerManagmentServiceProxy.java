package com.biocycle.customerWebApp.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.biocycle.customerWebApp.dto.OrganisationBeanDto;

@FeignClient(name = "zuul-server", contextId = "customermanagmentservice")
@RibbonClient(name = "customerManagmentService")
public interface CustomerManagmentServiceProxy {

	@PostMapping(value = "/customermanagmentservice/organisations")
	ResponseEntity<Void> addOrganisation(@RequestBody OrganisationBeanDto organisationBeanDto);
}
