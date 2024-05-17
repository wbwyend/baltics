package cn.baltics.springboot.starter.base.config;

import cn.baltics.springboot.starter.base.ApplicationContextHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *@name ApplicationContextHolderConfig spring上下文容器配置类
 *
 *@author wbwyend
 *@date 2024/05/17 
 */
@Configuration
public class ApplicationBaseConfiguration {
    @Bean
    public ApplicationContextHolder applicationContextHolder() {
        return new ApplicationContextHolder();
    }
}
