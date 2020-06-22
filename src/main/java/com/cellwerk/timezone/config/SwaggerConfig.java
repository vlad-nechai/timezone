package com.cellwerk.timezone.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Info apiInfo = new Info()
                               .title("Timezones API")
                               .description("Timezones REST API");

        return new OpenAPI()
                       .components(new Components())
                       .info(apiInfo);
    }
}