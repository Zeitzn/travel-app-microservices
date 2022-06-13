package com.movi.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
//@OpenAPIDefinition(info=@Info(title = "Sito API's", version = "1.0.0", description = "APIs del servicio"))
//@Import(BeanValidatorPluginsConfiguration.class)
public class OpenApiConfig {
//	@Bean
//	public Docket apiDocket() {
//		return new Docket(DocumentationType.OAS_30).select().apis(RequestHandlerSelectors.any())
//				.paths(PathSelectors.any()).build().apiInfo(getApiInfo());
//	}
//
//	private ApiInfo getApiInfo() {
//		return new ApiInfoBuilder().title("Sito API's").description("APIs del servicio").version("1.0").build();
//	}
	
	@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Sito API's").description("APIs del servicio").version("1.0.0"));
    }
}
