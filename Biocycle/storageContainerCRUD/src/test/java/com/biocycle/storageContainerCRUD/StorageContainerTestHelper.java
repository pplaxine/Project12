package com.biocycle.storageContainerCRUD;

public class StorageContainerTestHelper {
	
	public static String getStorageContainerDtoPOSTJson() {
		return "{\r\n" + 
				"    \"rowRef\": 2,\r\n" + 
				"    \"shelfRef\": \"A\",\r\n" + 
				"    \"levelRef\": 1,\r\n" + 
				"    \"isAvailable\": true\r\n" + 
				"}";
	}
	
	public static String getStorageContainerDtoPUTJSon() {
		return "{\r\n" + 
				"	\"id\": 31,\r\n" + 
				"    \"rowRef\": 2,\r\n" + 
				"    \"shelfRef\": \"A\",\r\n" + 
				"    \"levelRef\": 1,\r\n" + 
				"    \"isAvailable\": false\r\n" + 
				"}";
	}
	
	public static String getStorageContainerDtoListPUTJson() {
		return "[\r\n" + 
				"    {\r\n" + 
				"        \"id\": 2,\r\n" + 
				"        \"rowRef\": 1,\r\n" + 
				"        \"shelfRef\": \"B\",\r\n" + 
				"        \"levelRef\": 1,\r\n" + 
				"        \"isAvailable\": true\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"        \"id\": 4,\r\n" + 
				"        \"rowRef\": 1,\r\n" + 
				"        \"shelfRef\": \"D\",\r\n" + 
				"        \"levelRef\": 1,\r\n" + 
				"        \"isAvailable\": true\r\n" + 
				"    }\r\n" + 
				"]";
	}
}
