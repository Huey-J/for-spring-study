package com.spring.forspringstudy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
    //HelloController에 메소드 설명 적용 예시 있음

    @Bean
    public Docket api() {       //Swagger 설정 (Necessary)
        return new Docket(DocumentationType.SWAGGER_2)
                //문서 설정 적용
                .apiInfo(apiInfo())
                .select()
                //swagger에 표시할 API 선택 : 패키지 기준 api 선택 { any() | basePackage() }
                .apis(RequestHandlerSelectors.basePackage("com.spring.forspringstudy"))
                //swagger에 표시할 API 선택 : url 기준 api 선택 { any() | ant() }
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {     //문서 설정 (Optional)
        return new ApiInfoBuilder()
                .title("제목 작성")
                .version("버전 작성")
                .description("설명 작성")
                .license("라이센스 작성")
                .licenseUrl("라이센스 URL 작성")
                .build();
    }
}
