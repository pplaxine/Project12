package com.biocycle.customerWebApp.dto.view;

import java.util.Date;
import java.util.List;

import com.biocycle.customerWebApp.dto.ProductBatchBeanDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of = {"id","productBatchBeanList","availableForCollection","offerEndingDate","isAccepted"})
public class OfferViewDto {
	
	private Integer id;
	private List<ProductBatchBeanDto> productBatchBeanList;
	private Date availableForCollection;
	private Date offerEndingDate;
	private Boolean isAccepted;
	
}
