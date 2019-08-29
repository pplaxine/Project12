package com.biocycle.entWebApp.dto;

import java.util.Date;
import java.util.List;

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
	private Date availableForCollection;
	private Date offerEndingDate;
	private Boolean isAccepted;
}
