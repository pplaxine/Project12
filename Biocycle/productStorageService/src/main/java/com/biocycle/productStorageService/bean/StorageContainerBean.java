package com.biocycle.productStorageService.bean;

import javax.validation.constraints.NotNull;

public class StorageContainerBean implements Comparable<StorageContainerBean> {
	
	private int id;
	private int rowRef;
	private String shelfRef;
	private int levelRef;
	private Boolean isAvailable;
	
	public StorageContainerBean() {
	}
	
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

	@Override
	public int compareTo(StorageContainerBean o) {
		return shelfRef.compareTo(o.shelfRef)< 0 ? 1 : 0;
	}

	
	
	
}
