package com.biocycle.InventoryService;

public class ISTestHelper {
	
	public static String getProductBatchDtoPOSTJson() {
		return "{\r\n" + 
				"    \"name\": \"Apples\",\r\n" + 
				"    \"description\": \"Juicy apples\",\r\n" + 
				"    \"donorId\": 1,\r\n" + 
				"    \"dateOfCollection\": \"2019-09-15\",\r\n" + 
				"    \"toBeUsedBy\": \"2019-10-15\",\r\n" + 
				"    \"quantity\": 7,\r\n" + 
				"    \"unitOfMeasure\": \"KG\",\r\n" + 
				"    \"isAvailable\": true,\r\n" + 
				"    \"isAwaitingForCollection\": false\r\n" + 
				"}";
	}
}
