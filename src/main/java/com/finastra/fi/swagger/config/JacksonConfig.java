package com.finastra.fi.swagger.config;

import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        return new Jackson2ObjectMapperBuilder().failOnUnknownProperties(false);
    }
}

