package cn.baltics.order.repository.impl;

import cn.baltics.order.aggregation.Order;
import cn.baltics.order.repository.OrderRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/06/05 
 */
@Repository
public class OrderRepositoryImpl implements OrderRepository {
    @Override
    @Transactional
    public void submit(Order order) {
        System.out.println("save");
        if (new Random().nextInt(10) <= 3) throw new RuntimeException();
        else System.out.println("success");
    }
}
