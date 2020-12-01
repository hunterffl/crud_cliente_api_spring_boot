package com.felipe.cliente_crud.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
    public Docket clienteApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build()
                .apiInfo(metaInfo());
    }
	
    private ApiInfo metaInfo() {
        return new ApiInfo("Clientes API REST",
        		"API REST CRUD de Clientes",
        	    "1.0",
        	    "Terms of Service",
        	    new Contact("Felipe Lemos", "https://github.com/hunterffl", "felipelemos1987@homail.com"),
        	    "Apache 2.0",
        	    "http://www.apache.org/licenses/LICENSE-2.0",
        	    new ArrayList<>());
    }
}
