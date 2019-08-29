package com.biocycle.entWebApp.bean.redistribution;

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
@ToString(of = {"id","organisationId","productRequestId","offerId","isCompleted"})
public class RedistributionBean {
	
	private int id;
	private int organisationId;
	private List<Integer> productRequestId;
	private Integer offerId;
	private Boolean isCompleted;
	
}
