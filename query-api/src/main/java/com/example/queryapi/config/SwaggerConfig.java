package com.example.queryapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.Timestamp;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Autowired
    private Environment environment;

    @Bean
    public Docket api() {
        String swaggerUrlPrefix = environment.getRequiredProperty("swagger.config.url_prefix");

        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .securitySchemes(Collections.singletonList(basicAuth()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.queryapi"))
                .build()
                .pathMapping(swaggerUrlPrefix)
                .apiInfo(apiInfo())
                .directModelSubstitute(Timestamp.class, Long.class);
    }

    @Bean
    SecurityScheme basicAuth() {
        return new BasicAuth("Client-Auth");
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("CQRS Pattern Query API")
                .contact(new Contact("Arif Rahman", "", "arranusa@gmail.com"))
                .build();
    }
}
