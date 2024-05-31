package cn.baltics.order.service.impl;

import cn.baltics.order.service.OrderService;
import cn.baltics.springboot.starter.cache.CacheService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/31 
 */
@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private CacheService cacheService;
}
