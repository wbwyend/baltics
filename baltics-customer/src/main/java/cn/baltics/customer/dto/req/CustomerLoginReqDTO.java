package cn.baltics.customer.dto.req;

import cn.baltics.springboot.starter.designpattern.strategy.AbstractStrategy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *@func 用户登录请求DTO
 *
 *@author wbwyend
 *@date 2024/05/20 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerLoginReqDTO implements AbstractStrategy {
    /**
     * 登录方法
     */
    private String method;

    private String username;

    private String password;

    private String mail;

    private String verificationCode;

    @Override
    public String buildMask() {
        return method.toUpperCase();
    }
}
