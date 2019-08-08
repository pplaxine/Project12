package com.biocycle.organisationCRUD.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.organisationCRUD.dto.AddressDto;
import com.biocycle.organisationCRUD.dto.CollectionSpotAddressDto;
import com.biocycle.organisationCRUD.dto.OrganisationDto;
import com.biocycle.organisationCRUD.model.Address;
import com.biocycle.organisationCRUD.model.CollectionSpotAddress;
import com.biocycle.organisationCRUD.model.Organisation;

@Mapper(componentModel = "spring")
public interface OrganisationDtoMapper {
	OrganisationDto organisationToOrganisationDto(Organisation organisation);
	Organisation organisationDtoToOrganisation(OrganisationDto organisation);
	
	AddressDto addressToAddressDto(Address address);
	Address AddressDtoToAddress(AddressDto addressDto);
	
	CollectionSpotAddressDto collectionSpotAddressToCollectionSpotAddressDto(CollectionSpotAddress collectionSpotAddress);
	CollectionSpotAddress collectionSpotAddressDtoToCollectionSpotAddress(CollectionSpotAddressDto collectionSpotAddressDto);
	
}
