package cn.baltics.customer.controller;

import cn.baltics.customer.dto.req.CustomerLoginReqDTO;
import cn.baltics.customer.dto.req.CustomerRegisterCommitReqDTO;
import cn.baltics.customer.dto.req.CustomerRegisterVerifyReqDTO;
import cn.baltics.customer.dto.resp.CustomerLoginRespDTO;
import cn.baltics.customer.service.CustomerService;
import cn.baltics.springboot.starter.common.util.StringUtil;
import cn.baltics.springboot.starter.convention.result.Results;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

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
    private static final int COOLIE_MAX_AGE = 7200;

    @PostMapping("/customer/register")
    public Results<Void> registerCommit(@RequestBody CustomerRegisterCommitReqDTO requestParam) {
        customerService.registerCommit(requestParam);
        return Results.success();
    }

    @PostMapping("/customer/register/verify")
    public Results<CustomerLoginRespDTO> registerVerify(@RequestBody CustomerRegisterVerifyReqDTO requestParam, HttpServletResponse response) {
        CustomerLoginRespDTO result = customerService.registerVerify(requestParam);
        if (StringUtil.isNotNullAndBlank(result.getToken())) {
            Cookie cookie = new Cookie("token", result.getToken());
            cookie.setMaxAge(COOLIE_MAX_AGE);
            response.addCookie(cookie);
        }
        return Results.success(result);
    }

    @PostMapping("/customer/login")
    public Results<CustomerLoginRespDTO> login(@RequestBody CustomerLoginReqDTO requestParam, HttpServletResponse response) {
        CustomerLoginRespDTO result = customerService.login(requestParam);
        if (StringUtil.isNotNullAndBlank(result.getToken())) {
            Cookie cookie = new Cookie("token", result.getToken());
            cookie.setMaxAge(COOLIE_MAX_AGE);
            response.addCookie(cookie);
        }
        return Results.success(result);
    }

    @PostMapping("/customer/logout")
    public Results<CustomerLoginRespDTO> logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            Arrays.stream(cookies).filter(cookie -> cookie.getName().equals("token"))
                    .forEach(cookie -> {
                        cookie.setMaxAge(0);
                        cookie.setValue(null);
                        cookie.setPath("/");
                        response.addCookie(cookie);
                    });
        }
        return Results.success();
    }

    @GetMapping("/access")
    public Results<Void> accessLoginCheck() {
        return Results.success();
    }


}
