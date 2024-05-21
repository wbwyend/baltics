package cn.baltics.customer.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *@func 用户注册验证DTO
 *
 *@author wbwyend
 *@date 2024/05/19 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRegisterVerifyReqDTO {
    /**
     * 邮箱
     */
    private String mail;
    /**
     * 验证码
     */
    private String verificationCode;
}
