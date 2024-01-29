package com.company.botadminpanel.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.stereotype.Component;

@SecuritySchemes(value = {
        @SecurityScheme(
                name = "bearerAuth",
                description = "Bearer Tokkenni kiriting",
                type = SecuritySchemeType.HTTP,
                bearerFormat = "JWT",
                scheme = "bearer",
                in = SecuritySchemeIn.HEADER
        )
})
@OpenAPIDefinition(
        info = @Info(title = "Restaran-app",
                description = "Nodirbek ✔️",
                contact = @Contact(
                        url = "https://jozibali.uz",
                        email = "no0404ir@gmail.com",
                        extensions = {@Extension(name = "Admin-Panel Project",
                                properties = {@ExtensionProperty(name = "FirstExtension",
                                        value = "Value extension")})}
                )),
        servers = {
                @Server(url = "https://bot-admin-panel.onrender.com/", description = "bot-server"),
                @Server(url = "http://localhost:8080/", description = "localhost")
        },
        security = {
                @SecurityRequirement(name = "bearerAuth"),
        }
)
@Component
public class SwaggerConfig {
}
