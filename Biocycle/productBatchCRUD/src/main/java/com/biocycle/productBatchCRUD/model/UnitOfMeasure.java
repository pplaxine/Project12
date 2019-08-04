package com.biocycle.productBatchCRUD.model;

public enum UnitOfMeasure {

	KG("kg"),
	LITER("liter");
	
	private String lowerCase;
	
	private UnitOfMeasure(String lowerCase){
		this.lowerCase = lowerCase; 
	}
	
	public String toLowerCase() {
		return this.lowerCase;
	}
}
