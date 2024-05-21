package cn.baltics.springboot.starter.distributedid.config;

import cn.baltics.springboot.starter.distributedid.core.snowflake.SnowflakeInitializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *@name DistributedIdAutoConfiguration 分布式ID配置类
 *
 *@author wbwyend
 *@date 2024/05/20
 */
@Configuration
public class DistributedIdAutoConfiguration {

    @Value("${spring.snowflake.workId}")
    private Long workId;

    @Value("${spring.snowflake.dataCenterId}")
    private Long dataCenterId;

    @Value("${spring.snowflake.useSystemClock}")
    private Boolean useSystemClock;

    @Bean
    public SnowflakeInitializer snowflakeInitializer() {
        SnowflakeInitializer snowflakeInitializer = new SnowflakeInitializer(workId, dataCenterId, useSystemClock);
        snowflakeInitializer.init();
        return snowflakeInitializer;
    }

}

