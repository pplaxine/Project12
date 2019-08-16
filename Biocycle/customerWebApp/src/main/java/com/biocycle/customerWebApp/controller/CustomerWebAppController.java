package com.biocycle.customerWebApp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biocycle.customerWebApp.bean.ProductRequestBean;
import com.biocycle.customerWebApp.bean.RedistributionBean;
import com.biocycle.customerWebApp.dto.ProductRequestBeanDto;
import com.biocycle.customerWebApp.dto.RedistributionBeanDto;
import com.biocycle.customerWebApp.dto.mapper.ProductRequestBeanDtoMapper;
import com.biocycle.customerWebApp.dto.mapper.RedistributionBeanDtoMapper;
import com.biocycle.customerWebApp.proxy.ProductRequestCRUDMSProxy;
import com.biocycle.customerWebApp.proxy.RedistributionCRUDMSProxy;

@Controller
public class CustomerWebAppController {
	
	@Autowired
	private RedistributionCRUDMSProxy redistributionCRUDMSProxy;
	@Autowired
	private ProductRequestCRUDMSProxy productRequestCRUDMSProxy;
	
	@Autowired
	private RedistributionBeanDtoMapper redistributionBeanDtoMapper;
	@Autowired
	private ProductRequestBeanDtoMapper productRequestBeanDtoMapper;
	
	
	@RequestMapping("/")
	public String home(Model model) {
		
		//retrieve redistribution 
		ResponseEntity<RedistributionBeanDto> resp = redistributionCRUDMSProxy.getRedistributionById(1);
		RedistributionBeanDto rbd =  resp.getBody();
		RedistributionBean rb = redistributionBeanDtoMapper.redistributionBeanDtoToRedistributionBean(rbd);
		
		//retrieve productRequests
		List<Integer> productRequestIdList = rb.getProductRequestId();
		List<ProductRequestBean> productRequestBeanList = new ArrayList<>();
		
		for (Integer productRequestId : productRequestIdList) {
			
			ProductRequestBeanDto prb = productRequestCRUDMSProxy.findProductRequestById(productRequestId);
			ProductRequestBean pr = productRequestBeanDtoMapper.productRequestBeanDtoToProductRequestBean(prb);
			productRequestBeanList.add(pr);
		}
		
		
		
		//send to view 
		model.addAttribute("rb", rb );
		model.addAttribute("prList", productRequestBeanList);
		
		return "home";
	}
	
	@RequestMapping("/partnership/request")
	public String partnershipRequest(Model model) {
		return "requestPartnership";
	}
}
