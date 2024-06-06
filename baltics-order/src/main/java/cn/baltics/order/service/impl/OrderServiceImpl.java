package cn.baltics.order.service.impl;

import cn.baltics.order.aggregation.Order;
import cn.baltics.order.dto.req.OrderSubmitReqDTO;
import cn.baltics.order.dto.resp.OrderSubmitRespDTO;
import cn.baltics.order.enums.OrderCacheEnum;
import cn.baltics.order.enums.OrderEnum;
import cn.baltics.order.enums.OrderErrorEnum;
import cn.baltics.order.repository.OrderRepository;
import cn.baltics.order.service.OrderService;
import cn.baltics.springboot.starter.cache.CacheService;
import cn.baltics.springboot.starter.convention.exception.ClientException;
import cn.baltics.springboot.starter.distributedid.SnowflakeIdUtil;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

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
    private OrderRepository orderRepository;
    private ConcurrentHashMap<String, AtomicInteger> permissionMap;
    private ThreadPoolExecutor orderRepositoryThreadPool;
    private static final DefaultRedisScript<Long> SUBMIT_SCRIPT;
    private static final DefaultRedisScript<Long> RECALL_SCRIPT;
    private static final int RETRY_TIMES = 5;

    static {
        SUBMIT_SCRIPT = new DefaultRedisScript<>();
        SUBMIT_SCRIPT.setLocation(new ClassPathResource("lua/submit.lua"));
        SUBMIT_SCRIPT.setResultType(Long.class);
        RECALL_SCRIPT = new DefaultRedisScript<>();
        RECALL_SCRIPT.setLocation(new ClassPathResource("lua/recall.lua"));
        RECALL_SCRIPT.setResultType(Long.class);
    }

    @Override
    public OrderSubmitRespDTO submit(OrderSubmitReqDTO requestParam) {
        // 1.解决同一个账号，一次性发出多个请求问题
        if (cacheService.hashIncrementAndGet(OrderCacheEnum.ORDER_SUBMIT_CUSTOMER_SUBMIT_TIMES_COUNT.value() + requestParam.getCouponId(),
                String.valueOf(requestParam.getCustomerId())) > 1) {
            throw new ClientException(OrderErrorEnum.REPEATED_SUBMISSION_ERROR);
        }

        // 2.解决多个账号，一次性发送多个请求问题
        //   验证码处理机器访问
        //   前端实现

        // 3.本地许可证发放
        //   许可证不足则返回库存不足
        //   许可证数量 = 库存*1.5 / 服务器数
        AtomicInteger remain = permissionMap.get(OrderEnum.ORDER_SUBMIT_PERMISSION.name() + requestParam.getCouponId());
        if (remain.addAndGet(-requestParam.getNumber()) < 0) {
            remain.addAndGet(requestParam.getNumber());
            throw new ClientException(OrderErrorEnum.UNDERSTOCK);
        }

        // 4.执行lua脚本预减库存
        long executeResult = cacheService.executeScript(SUBMIT_SCRIPT,
                String.valueOf(requestParam.getCouponId()),
                String.valueOf(requestParam.getNumber()));
        if (executeResult != 0) {
            remain.addAndGet(requestParam.getNumber());
            throw new ClientException(OrderErrorEnum.UNDERSTOCK);
        }

        // 5.创建订单
        Order order = Order.builder()
                .id(SnowflakeIdUtil.nextId())
                .customerId(requestParam.getCustomerId())
                .couponId(requestParam.getCouponId())
                .shopId(requestParam.getShopId())
                .number(requestParam.getNumber())
                .value(requestParam.getValue())
                .build();

        // 6.线程池异步更新数据库，失败重试，重试次数用尽后加回库存
        orderRepositoryThreadPool.submit(() -> {
            try {
                orderRepository.submit(order);
            } catch (Exception e) {
                e.printStackTrace();
                boolean success = false;
                for (int i = 0; i < RETRY_TIMES && !success; i++) {
                    try {
                        Thread.sleep(500);
                        success = true;
                        orderRepository.submit(order);
                    } catch (Exception ignored) {
                        success = false;
                    }
                }
                if (!success) {
                    cacheService.executeScript(RECALL_SCRIPT,
                            String.valueOf(requestParam.getCouponId()),
                            String.valueOf(requestParam.getNumber()));
                    remain.addAndGet(requestParam.getNumber());
                }
            }
        });

        return OrderSubmitRespDTO.builder()
                .orderId(order.getId())
                .build();
    }
}
