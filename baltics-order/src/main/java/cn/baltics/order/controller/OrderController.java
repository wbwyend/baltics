package cn.baltics.order.controller;

import cn.baltics.order.dto.req.OrderSubmitReqDTO;
import cn.baltics.springboot.starter.convention.result.Results;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/31 
 */
@RestController
@AllArgsConstructor
public class OrderController {

    @PostMapping("/order/submit")
    public Results<Void> submitOrder(@RequestBody OrderSubmitReqDTO requestParam) {
        return Results.success();
    }
}
