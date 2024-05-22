package cn.baltics.springboot.starter.web.config;

import cn.baltics.springboot.starter.web.interceptor.AccessControlInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AccessControlInterceptor accessControlInterceptor;
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessControlInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/customer/register/**")
                .excludePathPatterns("/customer/login/**")
                .excludePathPatterns("/customer/logout/**");
        super.addInterceptors(registry);
    }
}
