package com.student_catalog_service.student_catalog_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CatalogConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
