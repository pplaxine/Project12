package com.biocycle.customerWebApp.bean.organisation;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter 
@Setter
@ToString(of = {"id","organisationName","emailAddress","password","organisationAddress","collectionAddressList","phoneNumber","isDonor","isValidated"})
public class OrganisationBean {

	private int id;
	private String organisationName;
	private String emailAddress;
	private String password;
	private Address organisationAddress;
	private Map<String,CollectionSpotAddress> collectionAddressList;
	private String phoneNumber;
	private Boolean isDonor;
	private Boolean isValidated;
	
}
