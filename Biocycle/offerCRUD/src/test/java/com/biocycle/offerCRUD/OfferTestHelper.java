package com.biocycle.offerCRUD;

public class OfferTestHelper {
	
	public static String getOfferDtoPOSTJson() {
		return "{\r\n" + 
				"    \"productBatchIdList\": [\r\n" + 
				"        5,\r\n" + 
				"        6\r\n" + 
				"    ],\r\n" + 
				"    \"availableForCollection\": \"2019-10-20\",\r\n" + 
				"    \"offerEndingDate\": \"2019-10-24\",\r\n" + 
				"    \"isAccepted\": false\r\n" + 
				"}";
	}
	
	public static String getOfferDtoPUTJson() {
		return "{\r\n" + 
				"    \"id\": 4,\r\n" + 
				"    \"productBatchIdList\": [\r\n" + 
				"        5,\r\n" + 
				"        6\r\n" + 
				"    ],\r\n" + 
				"    \"availableForCollection\": \"2019-10-20\",\r\n" + 
				"    \"offerEndingDate\": \"2019-10-24\",\r\n" + 
				"    \"isAccepted\": true\r\n" + 
				"}";
	}
}
