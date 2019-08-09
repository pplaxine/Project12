package com.biocycle.collectionRunCRUD.dto;

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
@ToString(includeFieldNames = true, of = {"id","collectionRunDate","collectorId","startTime","endTime","containerIdList"})
public class CollectionRunDto {
	
	private int id;
	private Date collectionRunDate;
	private Integer collectorId;
	private Date startTime;
	private Date endTime; 
	private List<Integer> containerIdList;
}
