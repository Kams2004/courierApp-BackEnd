package com.courier.courierApp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI courierAppOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Courier Management API")
                        .description("API pour la gestion du courrier dans une entreprise")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Équipe de développement")
                                .email("dev@entreprise.com")))
                .addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement()
                        .addList("Bearer Authentication"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()));
    }
    
    private io.swagger.v3.oas.models.security.SecurityScheme createAPIKeyScheme() {
        return new io.swagger.v3.oas.models.security.SecurityScheme().type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}