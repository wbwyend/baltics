package cn.baltics.springboot.starter.designpattern.config;

import cn.baltics.springboot.starter.base.config.ApplicationBaseConfiguration;
import cn.baltics.springboot.starter.designpattern.chain.AbstractChainContext;
import cn.baltics.springboot.starter.designpattern.strategy.AbstractStrategySelector;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *@name DesignPatternConfiguration 设计模式配置类
 *
 *@author wbwyend
 *@date 2024/05/17 
 */
@Configuration
public class DesignPatternConfiguration {
    /**
     * 注入抽象责任链上下文
     */
    @Bean
    public AbstractChainContext abstractChainContext() {
        return new AbstractChainContext();
    }

    @Bean
    public AbstractStrategySelector abstractStrategySelector() { return new AbstractStrategySelector(); }
}
