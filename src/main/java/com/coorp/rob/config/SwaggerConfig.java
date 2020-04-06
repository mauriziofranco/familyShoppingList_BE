package com.coorp.rob.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig 
{        
	private static Logger log = LoggerFactory.getLogger(SwaggerConfig.class);
	
	@Value("${base.package.path}")
	private String BASE_PACKAGE_PATH;
    
	@Bean
    public Docket api() 
    { 
		log.info("method api() - START");
		log.info("method api() - DEBUG: " + this.BASE_PACKAGE_PATH);
		log.info("method api() - END");
    	return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(this.BASE_PACKAGE_PATH))
                .paths(PathSelectors.any())
                .build();
    }
    
    private ApiInfo apiInfo() 
    {
		log.info("method apiInfo()  - START");
		log.info("method api() - DEBUG: " + this.BASE_PACKAGE_PATH);
		log.info("method apiInfo()  - END");
        ApiInfo apiInfo = new ApiInfo(
                "ShopList REST API",
                "ShopList REST API Documentation",
                "1.0",
                "Terms of service",
                "xxxx@yyyy.it",
                "GPL v2 Licence",
                "http://www.gnu.org/licenses/old-licenses/gpl-2.0.html");
        return apiInfo;
    }
}