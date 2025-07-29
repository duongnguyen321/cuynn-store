package com.zmen.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ZMEN E-commerce Backend API")
                        .version("1.0.0")
                        .description("Backend API cho hệ thống thương mại điện tử ZMEN")
                        .contact(new Contact()
                                .name("ZMEN Team")
                                .email("support@zmen.com")
                                .url("https://zmen.com")));
    }
}

