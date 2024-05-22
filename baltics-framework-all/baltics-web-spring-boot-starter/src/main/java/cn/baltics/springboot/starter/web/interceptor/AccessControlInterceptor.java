package cn.baltics.springboot.starter.web.interceptor;

import cn.baltics.customer.aggregation.Customer;
import cn.baltics.customer.repository.CustomerRepository;
import cn.baltics.springboot.starter.common.enums.TokenErrorCode;
import cn.baltics.springboot.starter.common.util.StringUtil;
import cn.baltics.springboot.starter.common.util.TokenUtil;
import cn.baltics.springboot.starter.convention.exception.ClientException;
import cn.baltics.springboot.starter.web.enums.AccessControlErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/21 
 */
@Component
public class AccessControlInterceptor implements HandlerInterceptor {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            throw new ClientException(AccessControlErrorCode.TOKEN_IS_MISS);
        }
        List<Cookie> cookieList = Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals("token"))
                .collect(Collectors.toList());
        if (cookieList.isEmpty()) {
            throw new ClientException(AccessControlErrorCode.TOKEN_IS_MISS);
        }
        String token = cookieList.get(0).getValue();
        long id = Long.parseLong(TokenUtil.getAudience(token));
        Customer customer = customerRepository.getCustomerById(id);
        if (customer == null || StringUtil.isNullOrBlank(customer.getPassword())) {
            throw new ClientException(AccessControlErrorCode.TOKEN_IS_MISS);
        }
        String sign = customer.getPassword();
        try {
            TokenUtil.parseToken(token, sign);
        } catch (ClientException e) {
            if (Objects.equals(e.errorMessage, TokenErrorCode.TOKEN_EXPIRED_ERROR.message())) throw new ClientException(AccessControlErrorCode.TOKEN_IS_EXPIRED);
            else throw new ClientException(AccessControlErrorCode.TOKEN_IS_MISS);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
