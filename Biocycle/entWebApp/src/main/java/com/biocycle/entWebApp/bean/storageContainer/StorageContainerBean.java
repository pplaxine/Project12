package com.biocycle.entWebApp.bean.storageContainer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of = {"id","rowRef","shelfRef","levelRef","isAvailable"})
public class StorageContainerBean implements Comparable<StorageContainerBean> {
	
	private int id;
	private int rowRef;
	private String shelfRef;
	private int levelRef;
	private Boolean isAvailable;



	@Override
	public int compareTo(StorageContainerBean o) {
		return shelfRef.compareTo(o.shelfRef)< 0 ? 1 : 0;
	}

	
	
	
}
