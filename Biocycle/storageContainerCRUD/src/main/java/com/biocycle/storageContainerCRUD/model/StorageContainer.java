package com.biocycle.storageContainerCRUD.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
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
		
		//CONSTRUCTORS
		public StorageContainer(int id, @NotNull int rowRef, @NotNull String shelfRef, @NotNull int levelRef,
				Boolean isAvailable) {
			super();
			this.id = id;
			this.rowRef = rowRef;
			this.shelfRef = shelfRef;
			this.levelRef = levelRef;
			this.isAvailable = isAvailable;
		} 
		
		public StorageContainer() {
		}
		
		
		//G&S
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getRowRef() {
			return rowRef;
		}
		public void setRowRef(int rowRef) {
			this.rowRef = rowRef;
		}
		public String getShelfRef() {
			return shelfRef;
		}
		public void setShelfRef(String shelfRef) {
			this.shelfRef = shelfRef;
		}
		public int getLevelRef() {
			return levelRef;
		}
		public void setLevelRef(int levelRef) {
			this.levelRef = levelRef;
		}
		public Boolean getIsAvailable() {
			return isAvailable;
		}
		public void setIsAvailable(Boolean isAvailable) {
			this.isAvailable = isAvailable;
		}

		
		//toString
		@Override
		public String toString() {
			return "StorageContainer [id=" + id + ", row=" + rowRef + ", shelf=" + shelfRef + ", level=" + levelRef
					+ ", isAvailable=" + isAvailable + "]";
		}
		
		
		
		
		
}
