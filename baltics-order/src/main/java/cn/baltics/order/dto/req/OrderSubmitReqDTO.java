package cn.baltics.order.dto.req;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/31 
 */
@Data
public class OrderSubmitReqDTO implements Serializable {
    private long customerId;
    private long shopId;
    private long couponId;
    private int number;
    private BigDecimal value;
}
