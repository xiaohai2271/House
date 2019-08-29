package site.celess.house.configuration;

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
 * @author : xiaohai
 * @date : 2019/08/29 10:49
 * @Description：
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket restApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo("House project's APIs", "1.0"))
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("site.celess.house.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(String title, String version) {
        return new ApiInfoBuilder()
                .title(title)
                .description("更多请关注: https://www.celess.cn")
                .contact(new Contact("小海博客", "https://www.celess.cn", "a@celess.cn"))
                .version(version)
                .build();
    }
}
