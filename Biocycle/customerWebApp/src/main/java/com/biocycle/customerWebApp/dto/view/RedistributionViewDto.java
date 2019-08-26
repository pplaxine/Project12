package com.biocycle.customerWebApp.dto.view;

import java.util.List;

import com.biocycle.customerWebApp.dto.ProductRequestBeanDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter 
@Setter
@ToString(of = {"id","organisationId","productRequestBeanDtoList","offerViewDto","isCompleted"})
public class RedistributionViewDto {
	private int id;
	private int organisationId;
	private List<ProductRequestBeanDto> productRequestBeanDtoList;
	private OfferViewDto offerViewDto;
	private Boolean isCompleted;
}
