package com.weverton.rest_with_SpringBoot.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("APIs RESTful from 0 with Java, Spring Boot, Kubernetes and Docker")
                        .version("v1")
                        .description("APIs RESTful from 0 with Java, Spring Boot, Kubernetes and Docker")
                        .termsOfService("https://www.linkedin.com/in/wevertoncarvalho09/")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.linkedin.com/in/wevertoncarvalho09/")));
    }
}
