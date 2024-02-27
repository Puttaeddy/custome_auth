package com.finastra.users.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwagggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title(" User Management API ")
                        .description("Finasta Managing The Finacial Institute.")
                        .version("1.0").contact(new Contact().name("GlobalLogic")
                                .email( "www.cricinfo.com").url("prakash@gmail.com"))
                        .license(new License().name("License of API")
                                .url("API license URL")));
    }

}
