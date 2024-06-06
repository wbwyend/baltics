package cn.baltics.order.dto.resp;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/06/05 
 */
@Data
@Builder
public class OrderSubmitRespDTO implements Serializable {
    private long orderId;
}
