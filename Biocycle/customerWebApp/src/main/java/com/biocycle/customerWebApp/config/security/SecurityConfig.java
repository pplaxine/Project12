package com.biocycle.customerWebApp.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * The Class SecurityConfig.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	/** The user details service. */
	@Autowired
	private UserDetailsService userDetailsService;
	
	/**
	 * Auth provider.
	 *
	 * @return the authentication provider
	 */
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		
		return provider;
	}


	/**
	 * Configure.
	 *
	 * @param http the http
	 * @throws Exception the exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http
         .csrf().disable()
         .authorizeRequests()
         .antMatchers("/user/**").hasAuthority("DONOR")	//ROLE add automatically ROLE_
         .antMatchers("/user2/**").hasAuthority("RECEIVER")
         .antMatchers("/**").permitAll()
         .anyRequest().authenticated()
         .and()
         .formLogin()
         .defaultSuccessUrl("/", true)
         //.failureUrl("/login.html?error=true")
         .and()
         .logout().invalidateHttpSession(true)
         .clearAuthentication(true)
         .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
         .logoutSuccessUrl("/");
         
        
	}
	
	  /**
  	 * Password encoder.
  	 *
  	 * @return the password encoder
  	 */
  	@Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	
}
