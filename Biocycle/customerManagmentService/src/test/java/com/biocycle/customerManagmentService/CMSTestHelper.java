package com.biocycle.customerManagmentService;

public class CMSTestHelper {
	public static String getOrganisationBeanDtoPOSTJson() {
		return "{\r\n" + 
				"    \"organisationName\": \"Orga3\",\r\n" + 
				"    \"emailAddress\": \"orga3@orange.fr\",\r\n" + 
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
				"    \"isValidated\": true\r\n" + 
				"}";
	}
	
	public static String getOrganisationBeanDtoPasswordPOSTJson() {
		return "{\r\n" + 
				"    \"emailAddress\": \"orga3@orange.fr\",\r\n" + 
				"    \"password\": \"Test01\"\r\n" + 
				"}";
	}
}
