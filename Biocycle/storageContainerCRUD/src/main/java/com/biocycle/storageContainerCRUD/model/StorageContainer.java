package com.biocycle.storageContainerCRUD.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@ToString(of = {"id","rowRef","shelfRef","levelRef","isAvailable"})
public class StorageContainer {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		@NotNull
		private int rowRef;
		@NotNull
		private String shelfRef;
		@NotNull
		private int levelRef;
		private Boolean isAvailable;
		
}
