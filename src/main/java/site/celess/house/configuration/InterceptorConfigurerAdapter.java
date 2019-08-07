package site.celess.house.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import site.celess.house.configuration.interceptor.Authorization;

/**
 * @Author: 小海
 * @Date： 2019/08/07 00:33
 * @Description：
 */
@Configuration
public class InterceptorConfigurerAdapter implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new Authorization())
                // 排除login 和  logout 接口的拦截
                .excludePathPatterns("/login")
                .excludePathPatterns("/logout")
                .addPathPatterns("/**");
    }
}
