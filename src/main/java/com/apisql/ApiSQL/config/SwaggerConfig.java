package com.apisql.ApiSQL.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API-SQL",
                version = "1.0",
                description = "API em Spring que consome um banco SQL"
        )
)
public class SwaggerConfig {
}
