package com.planttech;

import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;


//SwaggerConfig.java
@OpenAPIDefinition(
        info = @Info(title = "플랜테크 API 명세서",
                description = "플랜테크:나만의 식물 그로잉 플랫폼 API 명세서",
                version = "v0.1"))
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {
// 
//    @Bean
//    public GroupedOpenApi planttechApi() {
//        String[] paths = {"/**"};
// 
//        return GroupedOpenApi.builder()
//                .group("나만의 식물 그로잉 플랫폼 API v0.1")
//                .pathsToMatch(paths)
//                .build();
//    }
}

//@Configuration
//public class SwaggerConfig {

//    private ApiInfo swaggerInfo() {
//        return new ApiInfoBuilder().title("IoT API")
//                .description("IoT API Docs").build();
//    }
//
//    @Bean
//    public Docket swaggerApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .consumes(getConsumeContentTypes())
//                .produces(getProduceContentTypes())
//                .apiInfo(swaggerInfo()).select()
//                .apis(RequestHandlerSelectors.basePackage("com.planttech.controller"))
//                .paths(PathSelectors.any())
//                .build()
//                .useDefaultResponseMessages(false);
//    }
//
//    private Set<String> getConsumeContentTypes() {
//        Set<String> consumes = new HashSet<>();
//        consumes.add("application/json;charset=UTF-8");
//        consumes.add("application/x-www-form-urlencoded");
//        return consumes;
//    }
//
//    private Set<String> getProduceContentTypes() {
//        Set<String> produces = new HashSet<>();
//        produces.add("application/json;charset=UTF-8");
//        return produces;
//    }
	
//	   @Bean
//	    public Docket api() {
//	        return new Docket(DocumentationType.OAS_30)
//	                .useDefaultResponseMessages(false)
//	                .select()
//	                .apis(RequestHandlerSelectors.basePackage("com.planttech.controller"))
//	                .paths(PathSelectors.any())
//	                .build()
//	                .apiInfo(apiInfo());
//	    }
//
//	    private ApiInfo apiInfo() {
//	        return new ApiInfoBuilder()
//	                .title("PlanTech API")
//	                .description("Planttech API Doc")
//	                .version("0.0.1")
//	                .build();
//	    }
//	    
//	    /**
//	     * Swagger2 버전은 http://localhost:8080/swagger-ui.html
//	     * spring-security와 연결할 때 이 부분을 작성하지 않으면 404에러가 뜬다.
//	     * 
//	     * 3.x 버전 부터는 swagger-ui 경로가 다르다고 합니다. 
//	     * http://localhost:8080/swagger-ui/index.html 로 접근해보세요 
//	     */
//	    @Override
//	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//	        registry.addResourceHandler("/swagger-ui.html")
//	                .addResourceLocations("classpath:/META-INF/resources/");
//	    
//	        registry.addResourceHandler("/webjars/**")
//	                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//	        
//	        // -- Static resources
//	        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//	        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css");
//	        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
//	        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
//	    }
	    
	
	
//}