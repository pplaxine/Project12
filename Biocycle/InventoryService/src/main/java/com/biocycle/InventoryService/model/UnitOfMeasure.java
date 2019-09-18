package com.biocycle.InventoryService.model;

/**
 * The Enum UnitOfMeasure.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
public enum UnitOfMeasure {

	/** The kg. */
	KG("kg"),
	
	/** The liter. */
	LITER("liter");
	
	/** The lower case. */
	private String lowerCase;
	
	/**
	 * Instantiates a new unit of measure.
	 *
	 * @param lowerCase the lower case
	 */
	private UnitOfMeasure(String lowerCase){
		this.lowerCase = lowerCase; 
	}
	
	/**
	 * To lower case.
	 *
	 * @return the string
	 */
	public String toLowerCase() {
		return this.lowerCase;
	}
}
