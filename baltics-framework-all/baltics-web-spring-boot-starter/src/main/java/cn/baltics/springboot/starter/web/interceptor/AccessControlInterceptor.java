package cn.baltics.springboot.starter.web.interceptor;

import cn.baltics.springboot.starter.common.util.TokenUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/21 
 */
public class AccessControlInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Cookie[] cookies = request.getCookies();
        List<Cookie> cookieList = Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals("token"))
                .collect(Collectors.toList());
        if (cookieList.isEmpty()) {
            return false;
        }
        String token = cookieList.get(0).getValue();
//        TokenUtil.parseToken(token, );

        return true;
    }
}
