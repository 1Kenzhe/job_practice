package com.kenzhe.job.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "kenzhe",
                        email = "esentayarnas10@gmail.com",
                        url = "http://localhost:8080"
                ),
                description = "Job API",
                title = "OpenAPI Specification",
                version = "1.0",
                license =  @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html"
                ),
                termsOfService = "Term of service"
        ),
        servers = {
                @Server(
                        description = "Local server",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "Production server",
                        url = "https://api.kenzhe.com"
                )
        }
)
public class OpenAPIConfig {
}
