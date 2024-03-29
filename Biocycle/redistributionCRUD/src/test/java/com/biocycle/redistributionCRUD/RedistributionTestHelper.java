package com.biocycle.redistributionCRUD;

public class RedistributionTestHelper {
	
	public static String getRedistributionDtoPOSTJson() {
		return "{\r\n" + 
				"    \"organisationId\": 2,\r\n" + 
				"    \"productRequestId\": [\r\n" + 
				"        5\r\n" + 
				"    ],\r\n" + 
				"    \"isCompleted\": false\r\n" + 
				"}";
	}
	
	public static String getRedistributionDtoPUTJson() {
		return "{\r\n" + 
				"    \"id\": 4,\r\n" + 
				"    \"organisationId\": 2,\r\n" + 
				"    \"productRequestId\": [\r\n" + 
				"        \r\n" + 
				"    ],\r\n" + 
				"    \"isCompleted\": false\r\n" + 
				"}";
	}
}
