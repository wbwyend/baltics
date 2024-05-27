package cn.baltics.shop.dto.req;

import cn.baltics.springboot.starter.designpattern.strategy.AbstractStrategy;
import lombok.Data;

import java.io.Serializable;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/23 
 */
@Data
public class ShopReviewGetReqDTO implements Serializable, AbstractStrategy {
    private long customerId;
    private long shopId;
    /**
     * 排序策略
     */
    private String sort;
    private int page;
    private int size;

    @Override
    public String buildMask() {
        return sort.toUpperCase();
    }
}
