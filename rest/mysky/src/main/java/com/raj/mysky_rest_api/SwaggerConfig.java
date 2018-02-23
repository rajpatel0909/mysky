package com.raj.mysky_rest_api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.pathMapping("/api/*")
//				.useDefaultResponseMessages(false)
//				.select()
//				.apis(RequestHandlerSelectors.any())
//				.paths(PathSelectors.any())
//				.build()
//				.apiInfo(apiInfo());	
//	}
//	
//	private ApiInfo apiInfo() {
//		Contact contact = new Contact("admin", "https://rpd.com", "raj9009patel@gmail.com");
//		ApiInfo info = new ApiInfo("Spring-REST API", "A simple REST API", "1.0.0", "Tnc", contact, "MIT", "https://opensource.org/license/MIT");
//		return info;
//	}
}
