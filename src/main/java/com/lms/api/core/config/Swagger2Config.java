package com.lms.api.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 接口API swagger2配置类
 * 
 * swagger通过注解表明该接口会生成文档，包括接口名、请求方法、参数、返回信息的等等
 * @Api：修饰整个类，描述Controller的作用
 * @ApiOperation：描述一个类的一个方法，或者说一个接口
 * @ApiParam：单个参数描述
 * @ApiModel：用对象来接收参数
 * @ApiProperty：用对象接收参数时，描述对象的一个字段
 * @ApiResponse：HTTP响应其中1个描述
 * @ApiResponses：HTTP响应整体描述
 * @ApiIgnore：使用该注解忽略这个API
 * @ApiError ：发生错误返回的信息
 * @ApiParamImplicitL：一个请求参数
 * @ApiParamsImplicit: 多个请求参数
 *	
 * @url: http://localhost:8080/swagger-ui.html
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				// 包路径
				.apis(RequestHandlerSelectors.basePackage("com.lms.api.controller"))
				.paths(PathSelectors.any())
				.build();
	}
	
	/**
	 * 构建 api文档的详细信息函数
	 * @return ApiInfo
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				//页面标题
				.title("lms_api项目API文档")
				//描述
				.description("spring boot服务集成项目API")
				//服务条款网址
				.termsOfServiceUrl("https://swagger.io/")
				//版本号
				.version("1.0")
				//创建人
				.contact(new Contact("Ming", "url", "mzliang@xinkunin.com"))
				.build();
	}

}
