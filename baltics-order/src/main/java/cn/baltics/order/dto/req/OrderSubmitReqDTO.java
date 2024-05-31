package cn.baltics.order.dto.req;

import lombok.Data;

import java.math.BigDecimal;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/31 
 */
@Data
public class OrderSubmitReqDTO {
    private long customerId;
    private long shopId;
    private long couponId;
    private int number;
    private BigDecimal value;
}
