package br.com.rjs.rest_spring.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Rest API'S Restfull with Spring Boot, Java, Docker and Kubernates")
                        .version("1.0")
                        .description("Api of People")
                        .termsOfService("www.rjs.com.br")
                        .license(new License()
                                .name("apache 2.0")
                                .url("www.rjs.com.br")
                        )
                );
    }
}
