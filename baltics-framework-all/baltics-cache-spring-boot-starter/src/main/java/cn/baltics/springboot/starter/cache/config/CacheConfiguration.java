package cn.baltics.springboot.starter.cache.config;

import cn.baltics.springboot.starter.cache.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 *@name CacheConfiguration
 *
 *@author wbwyend
 *@date 2024/05/18 
 */
@Configuration
public class CacheConfiguration {
    @Bean
    public CacheService cacheService() {
        return new CacheService();
    }
}
