package com.biocycle.entWebApp.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.biocycle.entWebApp.dto.StaffBeanDto;
import com.biocycle.entWebApp.proxy.StaffCRUDMSProxy;

/**
 * The Class CustomUserDetailsService.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Service
public class CustomUserDetailsService implements UserDetailsService{

	/** The staff CRUDMS proxy. */
	@Autowired
	private StaffCRUDMSProxy staffCRUDMSProxy;
	
	/**
	 * Load user by username.
	 *
	 * @param userName the user name
	 * @return the user details
	 * @throws UsernameNotFoundException the username not found exception
	 */
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		
		
		try {
			
			StaffBeanDto staffBeanDto = staffCRUDMSProxy.findStaffByUserName(userName).getBody();
			String role; 
			String authorisationNumber = staffBeanDto.getAccess();
			if(authorisationNumber.equals("99")) {
				role ="ADMIN";
			}else if(authorisationNumber.equals("26")) {
				role="HR";
			}else if(authorisationNumber.equals("27")) {
				role="CME";
			}else if(authorisationNumber.equals("28")) {
				role="PME";
			}else if(authorisationNumber.equals("30")) {
				role="CSE";
			}else {
				role="";
			}
			
			return new User(staffBeanDto.getUserName(), staffBeanDto.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(role));
			
		} catch (ResponseStatusException e) {
			throw new UsernameNotFoundException("Status: " + e.getStatus() + " User not found.");
		}
	}
}
