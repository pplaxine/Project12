package com.biocycle.redistributionCRUD.model;

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
@ToString(of = {"id","organisationId","productRequestId","offerId","isCompleted"})
public class Redistribution {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private int organisationId;
	
	@ElementCollection
	@CollectionTable(name = "redistribution_productRequest_mapping", 
		joinColumns = {@JoinColumn(name="redistribution_id", referencedColumnName = "id")} 
		)
	@Column(name = "productRequest_id",unique = true)
	private List<Integer> productRequestId;
	@Column(unique = true)
	private Integer offerId;
	private Boolean isCompleted;

}
