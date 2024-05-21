package cn.baltics.customer.controller;

import cn.baltics.customer.dto.req.CustomerLoginReqDTO;
import cn.baltics.customer.dto.req.CustomerRegisterCommitReqDTO;
import cn.baltics.customer.dto.req.CustomerRegisterVerifyReqDTO;
import cn.baltics.customer.dto.resp.CustomerLoginRespDTO;
import cn.baltics.customer.service.CustomerService;
import cn.baltics.springboot.starter.common.util.StringUtil;
import cn.baltics.springboot.starter.convention.result.Results;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 *@name CustomerController
 *
 *@author wbwyend
 *@date 2024/05/19 
 */
@RestController
@AllArgsConstructor
public class CustomerController {
    private CustomerService customerService;

    @PostMapping("/customer/register")
    public Results<Void> registerCommit(@RequestBody CustomerRegisterCommitReqDTO requestParam) {
        customerService.registerCommit(requestParam);
        return Results.success();
    }

    @PostMapping("/customer/register/verify")
    public Results<CustomerLoginRespDTO> registerVerify(@RequestBody CustomerRegisterVerifyReqDTO requestParam, HttpServletResponse response) {
        CustomerLoginRespDTO result = customerService.registerVerify(requestParam);
        if (StringUtil.isNotNullAndBlank(result.getToken())) {
            response.addCookie(new Cookie("token", result.getToken()));
        }
        return Results.success(result);
    }

    @PostMapping("/customer/login")
    public Results<CustomerLoginRespDTO> login(@RequestBody CustomerLoginReqDTO requestParam, HttpServletResponse response) {
        CustomerLoginRespDTO result = customerService.login(requestParam);
        if (StringUtil.isNotNullAndBlank(result.getToken())) {
            response.addCookie(new Cookie("token", result.getToken()));
        }
        return Results.success(result);
    }

    @GetMapping("/access")
    public Results<Void> accessLoginCheck() {
        return Results.success();
    }


}
