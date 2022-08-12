package com.example.eduwithbe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan(basePackages = {
        "com.example.eduwithbe.controller"
})
public class SwaggerConfig {

    /** swagger */
    @Bean
    public Docket ShopApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("eduwith-api")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.eduwithbe.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.ShopApiInfo())
                ;

    }

    private ApiInfo ShopApiInfo() {
        return new ApiInfoBuilder()
                .title("eduwith")
                .description("eduwith")
                .termsOfServiceUrl("http://www.eduwith-api.com")
                .version("1.0")
                .build();
    }
}

//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//    @Bean
//    public Docket restAPI() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.example.eduwithbe"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("eduwith")
//                .version("1.0.0")
//                .description("eduwith의 swagger api")
//                .build();
//    }
//}