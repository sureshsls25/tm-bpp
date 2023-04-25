package com.ms.bpp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {
        HashSet<String> protocols = new HashSet<String>();
        protocols.add("http");
        protocols.add("https");
        return new Docket(DocumentationType.SWAGGER_2)
                .protocols(protocols)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ms.bpp.controller"))
                .paths(PathSelectors.any())
                .build();
    }

}
