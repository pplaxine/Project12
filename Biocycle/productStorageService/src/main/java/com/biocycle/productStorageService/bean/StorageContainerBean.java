package com.biocycle.productStorageService.bean;

/**
 * The Class StorageContainerBean.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
public class StorageContainerBean implements Comparable<StorageContainerBean> {
	
	/** The id. */
	private int id;
	
	/** The row ref. */
	private int rowRef;
	
	/** The shelf ref. */
	private String shelfRef;
	
	/** The level ref. */
	private int levelRef;
	
	/** The is available. */
	private Boolean isAvailable;
	
	/**
	 * Instantiates a new storage container bean.
	 */
	public StorageContainerBean() {
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Gets the row ref.
	 *
	 * @return the row ref
	 */
	public int getRowRef() {
		return rowRef;
	}
	
	/**
	 * Sets the row ref.
	 *
	 * @param rowRef the new row ref
	 */
	public void setRowRef(int rowRef) {
		this.rowRef = rowRef;
	}
	
	/**
	 * Gets the shelf ref.
	 *
	 * @return the shelf ref
	 */
	public String getShelfRef() {
		return shelfRef;
	}
	
	/**
	 * Sets the shelf ref.
	 *
	 * @param shelfRef the new shelf ref
	 */
	public void setShelfRef(String shelfRef) {
		this.shelfRef = shelfRef;
	}
	
	/**
	 * Gets the level ref.
	 *
	 * @return the level ref
	 */
	public int getLevelRef() {
		return levelRef;
	}
	
	/**
	 * Sets the level ref.
	 *
	 * @param levelRef the new level ref
	 */
	public void setLevelRef(int levelRef) {
		this.levelRef = levelRef;
	}
	
	/**
	 * Gets the checks if is available.
	 *
	 * @return the checks if is available
	 */
	public Boolean getIsAvailable() {
		return isAvailable;
	}
	
	/**
	 * Sets the checks if is available.
	 *
	 * @param isAvailable the new checks if is available
	 */
	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	/**
	 * Compare to.
	 *
	 * @param o the o
	 * @return the int
	 */
	@Override
	public int compareTo(StorageContainerBean o) {
		return shelfRef.compareTo(o.shelfRef)< 0 ? 1 : 0;
	}

	
	
	
}
