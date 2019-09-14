package com.biocycle.productBatchCRUD;

public class ProductBatchTestHelper {

	public static String getProductBatchDtoPOSTJson() {
		return "{\r\n" + 
				"    \"name\": \"Tabasco\",\r\n" + 
				"    \"description\": \"Red Tabasco\",\r\n" + 
				"    \"donorId\": 2,\r\n" + 
				"    \"dateOfCollection\": \"2019-09-15\",\r\n" + 
				"    \"toBeUsedBy\": \"2019-10-15\",\r\n" + 
				"    \"quantity\": 0.350,\r\n" + 
				"    \"unitOfMeasure\": \"LITER\",\r\n" + 
				"    \"storageContainerId\": [\r\n" + 
				"        7,\r\n" + 
				"        8\r\n" + 
				"    ],\r\n" + 
				"    \"isAvailable\": true\r\n" + 
				"}";
	}
	
	public static String getProductBatchDtoPutJson() {
		return "{\r\n" + 
				"	\"id\":6,\r\n" + 
				"    \"name\": \"Tabasco\",\r\n" + 
				"    \"description\": \"Red Spicy Tabasco\",\r\n" + 
				"    \"donorId\": 2,\r\n" + 
				"	\"dateOfCollection\": \"2019-09-15\",\r\n" + 
				"    \"toBeUsedBy\": \"2019-10-15\",\r\n" + 
				"    \"quantity\": 0.350,\r\n" + 
				"    \"unitOfMeasure\": \"LITER\",\r\n" + 
				"    \"storageContainerId\": [\r\n" + 
				"        7,\r\n" + 
				"        8\r\n" + 
				"    ],\r\n" + 
				"    \"isAvailable\": true\r\n" + 
				"}";
	}
}
