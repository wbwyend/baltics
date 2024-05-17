package cn.baltics.springboot.starter.base.config;

import cn.baltics.springboot.starter.base.ApplicationContextHolder;
import cn.baltics.springboot.starter.base.init.ApplicationInitializationPostProcessor;
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
    /**
     * 注入spring上下文容器
     */
    @Bean
    public ApplicationContextHolder applicationContextHolder() {
        return new ApplicationContextHolder();
    }

    /**
     * 注入应用初始化后置处理器
     */
    @Bean
    public ApplicationInitializationPostProcessor applicationInitializationPostProcessor() {
        return new ApplicationInitializationPostProcessor();
    }
}
