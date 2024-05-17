package cn.baltics.springboot.starter.designpattern.config;

import cn.baltics.springboot.starter.designpattern.chain.AbstractChainContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *@name DesignPatternConfiguration
 *
 *@author wbwyend
 *@date 2024/05/17 
 */
@Configuration
public class DesignPatternConfiguration {
    @Bean
    public AbstractChainContext abstractChainContext() {
        return new AbstractChainContext();
    }
}
