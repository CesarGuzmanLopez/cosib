package org.openapitools

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.security.SecurityScheme

@Configuration
class SpringDocConfiguration {

    @Bean
    fun apiInfo(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("ine-verificator")
                    .description("API para verificar documentos oficiales como INE, CSF y documentos bancarios.")
                    .contact(
                        Contact()
                            .name("Soporte Técnico")
                            .url("https://www.ejemplo.com")
                            .email("soporte@ejemplo.com")
                    )
                    .license(
                        License()
                            .name("MIT")
                            .url("https://opensource.org/licenses/MIT")
                    )
                    .version("1.0")
            )
            .components(
                Components()
                    .addSecuritySchemes("Bearer", SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                    )
            )
    }
}
