package com.biocycle.customerWebApp.dto.view;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

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
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate availableForCollection;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate offerEndingDate;
	private Boolean isAccepted;
	
}
