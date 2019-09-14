package com.biocycle.productRequestCRUD;

public class ProductRequestTestHelper {
	
	public static String getProductRequestDtoPOSTJson() {
		return "{\r\n" + 
				"    \"productRequested\": \"Tomatoes\",\r\n" + 
				"    \"quantity\": 13.75,\r\n" + 
				"    \"unitOfMeasure\": \"KG\",\r\n" + 
				"    \"isAccepted\": false\r\n" + 
				"}";
	}
	
	public static String getProductRequestDtoListPostJson() {
		return "[\r\n" + 
				"	{\r\n" + 
				"	    \"productRequested\": \"Tomatoes\",\r\n" + 
				"	    \"quantity\": 13.75,\r\n" + 
				"	    \"unitOfMeasure\": \"KG\",\r\n" + 
				"	    \"isAccepted\": false\r\n" + 
				"	},\r\n" + 
				"	{\r\n" + 
				"	    \"productRequested\": \"Strawberries\",\r\n" + 
				"	    \"quantity\": 17.75,\r\n" + 
				"	    \"unitOfMeasure\": \"KG\",\r\n" + 
				"	    \"isAccepted\": false\r\n" + 
				"	}\r\n" + 
				"]";
	}
	
	public static String getProductRequestDtoPUTJSon() {
		return "{\r\n" + 
				"	\"id\": 4, \r\n" + 
				"    \"productRequested\": \"Tomatoes\",\r\n" + 
				"    \"quantity\": 17.00,\r\n" + 
				"    \"unitOfMeasure\": \"KG\",\r\n" + 
				"    \"isAccepted\": false\r\n" + 
				"}";
	}
}
