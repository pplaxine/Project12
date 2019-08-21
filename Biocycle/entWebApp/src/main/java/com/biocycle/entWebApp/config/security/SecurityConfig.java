package com.biocycle.entWebApp.config.security;

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
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		
		//provider.setPasswordEncoder(new BCryptPasswordEncoder()); stand by for testing 
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		
		return provider;
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http
         .csrf().disable()
         .authorizeRequests()
         .antMatchers("/**").hasAuthority("ADMIN")	
         .antMatchers("/hr/**").hasAuthority("HR")	//ROLE add automatically ROLE_
         .antMatchers("/cme/**").hasAuthority("CME")
         .antMatchers("/pme/**").hasAuthority("PME")
         .antMatchers("/cse/**").hasAuthority("CSE")
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
	
	  @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	
}
