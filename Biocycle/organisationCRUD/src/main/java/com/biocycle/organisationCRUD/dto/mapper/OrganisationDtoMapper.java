package com.biocycle.organisationCRUD.dto.mapper;

import org.mapstruct.Mapper;

import com.biocycle.organisationCRUD.dto.AddressDto;
import com.biocycle.organisationCRUD.dto.CollectionSpotAddressDto;
import com.biocycle.organisationCRUD.dto.OrganisationDto;
import com.biocycle.organisationCRUD.model.Address;
import com.biocycle.organisationCRUD.model.CollectionSpotAddress;
import com.biocycle.organisationCRUD.model.Organisation;

// TODO: Auto-generated Javadoc
/**
 * The Interface OrganisationDtoMapper.
 */
@Mapper(componentModel = "spring")
public interface OrganisationDtoMapper {
	
	/**
	 * Organisation to organisation dto.
	 *
	 * @param organisation the organisation
	 * @return the organisation dto
	 */
	OrganisationDto organisationToOrganisationDto(Organisation organisation);
	
	/**
	 * Organisation dto to organisation.
	 *
	 * @param organisation the organisation
	 * @return the organisation
	 */
	Organisation organisationDtoToOrganisation(OrganisationDto organisation);
	
	/**
	 * Address to address dto.
	 *
	 * @param address the address
	 * @return the address dto
	 */
	AddressDto addressToAddressDto(Address address);
	
	/**
	 * Address dto to address.
	 *
	 * @param addressDto the address dto
	 * @return the address
	 */
	Address AddressDtoToAddress(AddressDto addressDto);
	
	/**
	 * Collection spot address to collection spot address dto.
	 *
	 * @param collectionSpotAddress the collection spot address
	 * @return the collection spot address dto
	 */
	CollectionSpotAddressDto collectionSpotAddressToCollectionSpotAddressDto(CollectionSpotAddress collectionSpotAddress);
	
	/**
	 * Collection spot address dto to collection spot address.
	 *
	 * @param collectionSpotAddressDto the collection spot address dto
	 * @return the collection spot address
	 */
	CollectionSpotAddress collectionSpotAddressDtoToCollectionSpotAddress(CollectionSpotAddressDto collectionSpotAddressDto);
	
}
