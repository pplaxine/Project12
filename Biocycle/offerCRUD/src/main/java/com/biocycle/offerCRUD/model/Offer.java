package com.biocycle.offerCRUD.model;

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
@ToString(of = {"id","productBatchIdList","availableForCollection","offerEndingDate","isAccepted"})
public class Offer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ElementCollection
	@CollectionTable(name = "offer_productBatch_mapping", 
		joinColumns = {@JoinColumn(name="offer_id", referencedColumnName = "id")} 
		)
	@Column(name = "productBatch_id",unique = true)
	@NotEmpty
	private List<Integer> productBatchIdList;
	@NotNull
	private Date availableForCollection;
	@NotNull
	private Date offerEndingDate;
	private Boolean isAccepted;
	
}
