package com.mytaxi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger2 configuration
 *
 * @author jeraldfdo
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mytaxi.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .license("Apache 2.0")
                .version("Version 1.0 - mw")
                .termsOfServiceUrl("urn:tos")
                .title("mytaxi Server Applicant Test Service")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .contact(new Contact("mytaxi", null, "career@mytaxi.com"))
                .description("This service is to check the technology knowledge of a server applicant for mytaxi.")
                .build();
    }

}
