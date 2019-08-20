package com.biocycle.customerWebApp.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.biocycle.customerWebApp.dto.OrganisationBeanDto;
import com.biocycle.customerWebApp.proxy.OrganisationCRUDMSProxy;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private OrganisationCRUDMSProxy organisationCRUDMSProxy;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		
		
		try {
			String organisationType;
			OrganisationBeanDto organisationBeanDto = organisationCRUDMSProxy.findOrganisationByEmail(email).getBody();
			if(organisationBeanDto.getIsDonor()) {
				organisationType = "DONOR";
			}else if(!organisationBeanDto.getIsDonor()) {
				organisationType = "RECEIVER";
			}else {
				organisationType = "USER";
			}
			
			return new User(organisationBeanDto.getEmailAddress(), organisationBeanDto.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(organisationType));
		} catch (ResponseStatusException e) {
			throw new UsernameNotFoundException("Organisation not found. 404 ...");
		}
	}
}
