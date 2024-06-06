package cn.baltics.order.service.config;

import cn.baltics.order.enums.OrderCacheEnum;
import cn.baltics.order.enums.OrderEnum;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/06/06 
 */
@Component
@AllArgsConstructor
public class OrderServiceConfiguration {

    private StringRedisTemplate stringRedisTemplate;

    @Bean(name = "permissionMap")
    ConcurrentHashMap<String, AtomicInteger> permissionMap() {
        ConcurrentHashMap<String, AtomicInteger> map = new ConcurrentHashMap<>();
        map.put(OrderEnum.ORDER_SUBMIT_PERMISSION.name() + "1", new AtomicInteger(150));
        stringRedisTemplate.opsForValue().set("coupon:stock:" + String.valueOf(1L), String.valueOf(100));
        return map;
    }

    @Bean(name = "orderRepositoryThreadPool")
    ThreadPoolExecutor orderRepositoryThreadPool() {
        int coreSize = Runtime.getRuntime().availableProcessors() + 1;
        return new ThreadPoolExecutor(coreSize,
                coreSize,
                0,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(256),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
