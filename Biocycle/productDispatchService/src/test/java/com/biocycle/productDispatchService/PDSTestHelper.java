package com.biocycle.productDispatchService;

public class PDSTestHelper {
	
	public static String getProductRequestBeanDtoListPOSTJson() {
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
	
	public static String getOfferBeanDtoPostJson() {
		return "{\r\n" + 
				"    \"id\": 3,\r\n" + 
				"    \"productBatchIdList\": [\r\n" + 
				"        5\r\n" + 
				"    ],\r\n" + 
				"    \"availableForCollection\": \"2019-10-22\",\r\n" + 
				"    \"offerEndingDate\": \"2019-10-26\",\r\n" + 
				"    \"isAccepted\": false\r\n" + 
				"}";
	}
	
	public static String getOfferBeanDtoPostJsonWithoutId() {
		return "{\r\n" + 
				"    \"productBatchIdList\": [\r\n" + 
				"        5\r\n" + 
				"    ],\r\n" + 
				"    \"availableForCollection\": \"2019-10-22\",\r\n" + 
				"    \"offerEndingDate\": \"2019-10-26\",\r\n" + 
				"    \"isAccepted\": false\r\n" + 
				"}";
	}
}
