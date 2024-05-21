package cn.baltics.springboot.starter.web.config;

import cn.baltics.springboot.starter.web.interceptor.AccessControlInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 *@func 添加拦截器配置
 *
 *@author wbwyend
 *@date 2024/05/21 
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AccessControlInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/customer/register/**")
                .excludePathPatterns("/customer/login/**");
        super.addInterceptors(registry);
    }
}
