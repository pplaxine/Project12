package com.biocycle.organisationCRUD;

public class OrganisationTestHelper {

	
	public static String getOrganisationDtoPOSTJson() {
		return "{\r\n" + 
				"    \"organisationName\": \"Orga3\",\r\n" + 
				"    \"emailAddress\": \"orga3@orange.fr\",\r\n" + 
				"    \"password\": \"test03\",\r\n" + 
				"    \"organisationAddress\": {\r\n" + 
				"        \"streetNumber\": \"14\",\r\n" + 
				"        \"streetName\": \"Rue François 1er\",\r\n" + 
				"        \"city\": \"Paris\",\r\n" + 
				"        \"postCode\": \"75008\"\r\n" + 
				"    },\r\n" + 
				"    \"collectionAddressList\": {\r\n" + 
				"        \"Dépôt Paris\": {\r\n" + 
				"            \"spotName\": \"Dépôt Paris\",\r\n" + 
				"            \"streetNumber\": \"14\",\r\n" + 
				"            \"streetName\": \"Rue François 1er\",\r\n" + 
				"            \"city\": \"Paris\",\r\n" + 
				"            \"postCode\": \"75008\"\r\n" + 
				"        }\r\n" + 
				"    },\r\n" + 
				"    \"phoneNumber\": \"+033145232302\",\r\n" + 
				"    \"isDonor\": true,\r\n" + 
				"    \"isValidated\": false\r\n" + 
				"}";
	}
	
	public static String getOrganisationDtoPUTJson() {
		return "{\r\n" + 
				"	\"id\":3,\r\n" + 
				"    \"organisationName\": \"Orga3\",\r\n" + 
				"    \"emailAddress\": \"orga3@orange.fr\",\r\n" + 
				"    \"password\": \"test03\",\r\n" + 
				"    \"organisationAddress\": {\r\n" + 
				"        \"streetNumber\": \"14\",\r\n" + 
				"        \"streetName\": \"Rue François 1er\",\r\n" + 
				"        \"city\": \"Paris\",\r\n" + 
				"        \"postCode\": \"75008\"\r\n" + 
				"    },\r\n" + 
				"    \"collectionAddressList\": {\r\n" + 
				"        \"Dépôt Paris\": {\r\n" + 
				"            \"spotName\": \"Dépôt Paris\",\r\n" + 
				"            \"streetNumber\": \"14\",\r\n" + 
				"            \"streetName\": \"Rue François 1er\",\r\n" + 
				"            \"city\": \"Paris\",\r\n" + 
				"            \"postCode\": \"75008\"\r\n" + 
				"        }\r\n" + 
				"    },\r\n" + 
				"    \"phoneNumber\": \"+033145232302\",\r\n" + 
				"    \"isDonor\": false,\r\n" + 
				"    \"isValidated\": false\r\n" + 
				"}";
	}
}
