package com.biocycle.productDispatchService.bean;

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
