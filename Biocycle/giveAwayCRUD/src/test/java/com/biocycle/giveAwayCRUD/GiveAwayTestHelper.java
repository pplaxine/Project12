package com.biocycle.giveAwayCRUD;

public class GiveAwayTestHelper {
	
	public static String getGiveAwayDtoPOSTJson() {
		return "{\r\n" + 
				"    \"organisationId\": 1,\r\n" + 
				"    \"availableToBeCollectedFrom\": \"2019-07-15T08:00:00\",\r\n" + 
				"    \"collectionDate\": null,\r\n" + 
				"    \"collectionSpotAddress\": {\r\n" + 
				"        \"spotName\": \"home office\",\r\n" + 
				"        \"streetNumber\": \"8-10\",\r\n" + 
				"        \"streetName\": \"Rue Saint Bernard\",\r\n" + 
				"        \"city\": \"Paris\",\r\n" + 
				"        \"postCode\": \"75008\"\r\n" + 
				"    },\r\n" + 
				"    \"containerList\": [\r\n" + 
				"        {\r\n" + 
				"            \"description\": \"apples\",\r\n" + 
				"            \"accepted\": true,\r\n" + 
				"            \"collectionRunId\": null,\r\n" + 
				"            \"isCollected\": false\r\n" + 
				"        }\r\n" + 
				"        \r\n" + 
				"    ],\r\n" + 
				"	\"isCollected\": false    \r\n" + 
				"}";
	}
	
	public static String getGiveAwayDtoPUTJson() {
		return "{\r\n" + 
				"    \"id\": 4,\r\n" + 
				"    \"organisationId\": 1,\r\n" + 
				"    \"collectionSpotAddress\": {\r\n" + 
				"        \"spotName\": \"home office\",\r\n" + 
				"        \"streetNumber\": \"8-10\",\r\n" + 
				"        \"streetName\": \"Rue Saint Bernard\",\r\n" + 
				"        \"city\": \"Paris\",\r\n" + 
				"        \"postCode\": \"75008\"\r\n" + 
				"    },\r\n" + 
				"    \"availableToBeCollectedFrom\": \"2019-07-15T08:00:00\",\r\n" + 
				"    \"collectionDate\": null,\r\n" + 
				"    \"containerList\": [\r\n" + 
				"        {\r\n" + 
				"            \"id\": 5,\r\n" + 
				"            \"description\": \"apples\",\r\n" + 
				"            \"accepted\": true,\r\n" + 
				"            \"collectionRunId\": null,\r\n" + 
				"            \"isCollected\": false\r\n" + 
				"        }\r\n" + 
				"    ],\r\n" + 
				"    \"isCollected\": true\r\n" + 
				"}";
	}
}
