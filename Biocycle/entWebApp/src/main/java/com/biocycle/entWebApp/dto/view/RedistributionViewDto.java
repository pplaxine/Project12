package com.biocycle.entWebApp.dto.view;

import java.util.List;

import com.biocycle.entWebApp.dto.OfferBeanDto;
import com.biocycle.entWebApp.dto.OrganisationBeanDto;
import com.biocycle.entWebApp.dto.ProductRequestBeanDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of = {"id","organisationBeanDto","productRequestBeanDtoList","offerViewDto","isCompleted"})
public class RedistributionViewDto {
	
	private int id;
	private OrganisationBeanDto organisationBeanDto;
	private List<ProductRequestBeanDto> productRequestBeanDtoList;
	private OfferViewDto offerViewDto;
	private Boolean isCompleted;
	
}
