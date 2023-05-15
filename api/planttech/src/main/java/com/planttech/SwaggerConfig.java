package com.planttech;

import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;


@OpenAPIDefinition(
        info = @Info(title = "플랜팅 API",
                description = "스마트 플랜트 플랫폼 : 플랜팅 API 명세서",
                version = "v0.2"))
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {
        
}
