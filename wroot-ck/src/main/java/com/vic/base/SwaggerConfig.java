package com.vic.base;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


@Configuration    // 配置注解，自动在本类上下文加载一些环境变量信息
@EnableSwagger2   // 使swagger2生效
@EnableWebMvc
@ComponentScan(basePackages = {"com.vic.ck.api"})  //需要扫描的包路径
public class SwaggerConfig extends WebMvcConfigurationSupport {


//	 @ApiOperation(value="查询用户上级与粉丝数", notes = "获取用户上级信息",produces = "application/josn")

	@Bean
    public Docket task() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("接口")
                .select()   // 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.basePackage("com.vic.ck.api"))
//                .paths(paths())
//                .apis(RequestHandlerSelectors.any())  // 对所有api进行监控
                .paths(PathSelectors.any())   // 对所有路径进行监控
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }
	
	@Bean
    public Docket system() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("系统配置")
                .select()   // 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.basePackage("com.vic.ck.api.system"))
                .paths(PathSelectors.any())   // 对所有路径进行监控
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }
	
	@Bean
    public Docket personal() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("用户中心")
                .select()   // 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.basePackage("com.vic.ck.api.personal"))
                .paths(PathSelectors.any())   // 对所有路径进行监控
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    @Bean
    public Docket rider() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("骑手中心")
                .select()   // 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.basePackage("com.vic.ck.api.rider"))
                .paths(PathSelectors.any())   // 对所有路径进行监控
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    @Bean
    public Docket merchant() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("商家端")
                .select()   // 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.basePackage("com.vic.ck.api.merchant"))
                .paths(PathSelectors.any())   // 对所有路径进行监控
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

//	@Bean
//    public Docket sMvcPlugin() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .groupName("api2")
//                .select()   // 选择那些路径和api会生成document
//                .apis(RequestHandlerSelectors.basePackage("com.vic.ck.api"))
//                .paths(PathSelectors.regex("/api/user/task.*"))
////                .apis(RequestHandlerSelectors.any())  // 对所有api进行监控
////                .paths(PathSelectors.any())   // 对所有路径进行监控
//                .build()
//                .securitySchemes(securitySchemes())
//                .securityContexts(securityContexts());
//    }

//    private Predicate<String> paths() {
//    	
//        return PathSelectors.regex("/api/task");
//    }

    private List<ApiKey> securitySchemes() {
    	List<ApiKey> apikeys = new ArrayList<>();
//    	
//    	apikeys.add(new ApiKey("clientId", "客户端ID", "header"));
//    	apikeys.add(new ApiKey("clientSecret", "客户端秘钥", "header"));
		apikeys.add(new ApiKey("token", "客户端访问标识", "header"));
    	
    	return apikeys;
    }

    private List<SecurityContext> securityContexts() {
    	List<SecurityContext> securityContexts = new ArrayList<>();
    	securityContexts.add(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("/*.*"))
                        .build()
        );
        
        return securityContexts;
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("token", authorizationScopes));
        
        return securityReferences;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("RESTful API")
//                .termsOfServiceUrl("http://blog.csdn.net/yangshijin1988")
                .description("赏惠多  API提供接口调用")
                .license("License Version 2.0")
//                .licenseUrl("http://blog.csdn.net/yangshijin1988")
                .version("1.5").build();
    }
}
