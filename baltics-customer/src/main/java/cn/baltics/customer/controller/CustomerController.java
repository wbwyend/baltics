package cn.baltics.customer.controller;

import cn.baltics.customer.dto.req.CustomerLoginReqDTO;
import cn.baltics.customer.dto.req.CustomerRegisterCommitReqDTO;
import cn.baltics.customer.dto.req.CustomerRegisterVerifyReqDTO;
import cn.baltics.customer.dto.resp.CustomerLoginRespDTO;
import cn.baltics.customer.service.CustomerService;
import cn.baltics.springboot.starter.convention.result.Results;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *@name CustomerController
 *
 *@author wbwyend
 *@date 2024/05/19 
 */
@RestController
@RequiredArgsConstructor
public class CustomerController {
    private CustomerService customerService;

    @PostMapping("/customer/register")
    public Results<Void> registerCommit(@RequestBody CustomerRegisterCommitReqDTO requestParam) {
        customerService.registerCommit(requestParam);
        return Results.success();
    }

    @PostMapping("/customer/register/verify")
    public Results<CustomerLoginRespDTO> registerVerify(@RequestBody CustomerRegisterVerifyReqDTO requestParam) {
        CustomerLoginRespDTO result = customerService.registerVerify(requestParam);
        return Results.success(result);
    }

    @PostMapping("/customer/login")
    public Results<CustomerLoginRespDTO> login(@RequestBody CustomerLoginReqDTO requestParam) {
        CustomerLoginRespDTO result = customerService.login(requestParam);
        return Results.success(result);
    }

}
