package com.biocycle.staffCRUD;

public class StaffTestHelper {
	
	public static String getStaffDtoPOSTJson() {
		return "{\r\n" + 
				"    \"userName\": \"bruceL\",\r\n" + 
				"    \"password\": \"test09\",\r\n" + 
				"    \"name\": \"Bruce\",\r\n" + 
				"    \"surname\": \"Lee\",\r\n" + 
				"    \"role\": \"HR\",\r\n" + 
				"    \"access\": \"59\"\r\n" + 
				"}";
	}
	
	public static String getStaffDtoPUTJson() {
		return "{\r\n" + 
				"    \"id\": 9,\r\n" + 
				"    \"userName\": \"bruceL\",\r\n" + 
				"    \"password\": \"test09\",\r\n" + 
				"    \"name\": \"Bruce\",\r\n" + 
				"    \"surname\": \"Lee\",\r\n" + 
				"    \"role\": \"Killer\",\r\n" + 
				"    \"access\": \"59\"\r\n" + 
				"}";
	}
}
