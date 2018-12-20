package com.wsi.range.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import com.wsi.range.controller.RangeController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan(basePackageClasses = RangeController.class)
@PropertySource("classpath:swagger.properties")
public class SwaggerConfig
{
	private static final String VERSION = "1.0";
	private static final String LICENSE = "License";
	private static final String TITLE = "SWI REST API";
	private static final String DESCRIPTION = "RESTful API demo";

	@Bean
	public Docket rangeApi()
	{
		return new Docket(DocumentationType.SWAGGER_2)
		.apiInfo(apiInfo())
		.pathMapping("/")
		.select()
		.paths(PathSelectors.regex("/ranges.*"))
		.build();
	}
	
	private ApiInfo apiInfo()
	{
		return new ApiInfoBuilder()
				.title(TITLE)
				.description(DESCRIPTION)
				.version(VERSION)
				.license(LICENSE)
				.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
				.contact(new Contact("Yuri Zharchuk", "www.yuri.zharchuk.com", "yuri@zharchuk.com"))
				.build();
	}

}
