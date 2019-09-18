package com.biocycle.mailService.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * The Class PropertiesConfig.
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Configuration
@PropertySource("classpath:mailConfig.properties")
public class PropertiesConfig {

}
