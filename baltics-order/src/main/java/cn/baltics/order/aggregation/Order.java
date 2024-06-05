package cn.baltics.order.aggregation;

import cn.baltics.framework.core.domain.AggregateRoot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/06/05 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order implements AggregateRoot {
    private long id;
    private long customerId;
    private long shopId;
    private long couponId;
    private int number;
    private BigDecimal value;
}
