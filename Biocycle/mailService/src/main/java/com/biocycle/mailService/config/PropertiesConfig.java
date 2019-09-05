package com.biocycle.mailService.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:mailConfig.properties")
public class PropertiesConfig {

}
