package com.biocycle.productBatchCRUD.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@ToString(of = {"id","name","description","donorId","dateOfCollection","toBeUsedBy","quantity","unitOfMeasure","storageContainerId","isAvailable"})
public class ProductBatch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String name;
	private String description;
	@NotNull
	private int donorId;
	@NotNull
	private Date dateOfCollection;
	@NotNull
	private Date toBeUsedBy;
	
	@NotNull
	@Column(precision = 10, scale = 3)
	private BigDecimal quantity;
	@NotNull
	@Enumerated(value = EnumType.STRING)
	private UnitOfMeasure unitOfMeasure;
	
	@ElementCollection
	@CollectionTable(name = "productBatch_storageContainer_mapping", 
		joinColumns = {@JoinColumn(name="productBatch_id", referencedColumnName = "id")} 
		)
	@Column(name = "storageContainer_id",unique = true)
	@NotEmpty
	private List<@NotNull Integer> storageContainerId; 
	private Boolean isAvailable; 
}
