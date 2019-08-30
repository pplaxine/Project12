package com.biocycle.productDispatchService.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of = {"id","productBatchIdList","availableForCollection","offerEndingDate","isAccepted"})
public class OfferBeanDto {
	
	private int id;
	private List<Integer> productBatchIdList;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate availableForCollection;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate offerEndingDate;
	private Boolean isAccepted;
}
