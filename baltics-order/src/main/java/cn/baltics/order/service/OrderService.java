package cn.baltics.order.service;

import cn.baltics.order.dto.req.OrderSubmitReqDTO;
import cn.baltics.order.dto.resp.OrderSubmitRespDTO;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/31 
 */
public interface OrderService {
    /**
     * 提交订单
     * @param requestParam 请求参数
     * @return 提交结果
     */
    OrderSubmitRespDTO submit(OrderSubmitReqDTO requestParam);
}
