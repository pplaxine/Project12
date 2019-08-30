package com.biocycle.entWebApp.dto.view;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.biocycle.entWebApp.dto.ProductBatchBeanDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of = {"id","productBatchBeanDtoList","availableForCollection","offerEndingDate","isAccepted"})
public class OfferViewDto {
	
	private int id;
	private List<ProductBatchBeanDto> productBatchBeanDtoList;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate availableForCollection;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate offerEndingDate;
	private Boolean isAccepted;
}
