package com.biocycle.productDispatchService.bean;

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
public class OfferBean {
	
	private int id;
	private List<Integer> productBatchIdList;
	private Date availableForCollection;
	private Date offerEndingDate;
	private Boolean isAccepted;
}
