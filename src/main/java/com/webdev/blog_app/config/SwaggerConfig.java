package com.webdev.blog_app.config;

 

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

/**
 * This config enables JWT token auth in Swagger UI.
 * You will see a lock icon to authorize using Bearer token.
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Blog App API", version = "1.0", description = "API for Blog App"),
        security = @SecurityRequirement(name = "bearerAuth")  // applies token to all endpoints
)
@SecurityScheme(
        name = "bearerAuth", // must match SecurityRequirement name
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class SwaggerConfig {
    // No extra beans are needed if using springdoc-openapi 1.6+ or 2.x
}


