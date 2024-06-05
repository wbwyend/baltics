package cn.baltics.order.repository;

import cn.baltics.order.aggregation.Order;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/06/05 
 */
public interface OrderRepository {
    void submit(Order order);
}
