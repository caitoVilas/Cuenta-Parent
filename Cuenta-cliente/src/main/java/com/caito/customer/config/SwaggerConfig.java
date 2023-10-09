package com.caito.customer.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author claudio.vilas
 * date 09/2023
 */

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi api(){
        return GroupedOpenApi.builder()
                .group("customer")
                .packagesToScan("com.caito.customer")
                .build();
    }

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info().title("Gestion de Clientes")
                        .description("Modulo de gestion de Clientes")
                        .contact(new Contact().name("caito").email("caitocd@gmail.com"))
                        .version("1.00"));
    }
}
