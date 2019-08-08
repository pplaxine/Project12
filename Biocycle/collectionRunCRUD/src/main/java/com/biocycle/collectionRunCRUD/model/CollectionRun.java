package com.biocycle.collectionRunCRUD.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter 
@Setter
@ToString(includeFieldNames = true, of = {"id","collectionRunDate","collectorId","startTime","endTime","containerIdList"})
public class CollectionRun {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private Date collectionRunDate;
	private Integer collectorId;
	private Date startTime;
	private Date endTime; 
	@ElementCollection
	@CollectionTable(name = "collectionRun_container_mapping", 
		joinColumns = {@JoinColumn(name="collectionRun_id", referencedColumnName = "id")} 
		)
	@Column(name = "container_id",unique = true)
	@NotEmpty
	private List<@NotNull Integer> containerIdList;
}
