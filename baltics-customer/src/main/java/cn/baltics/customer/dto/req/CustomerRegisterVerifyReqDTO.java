package cn.baltics.customer.dto.req;

import lombok.Builder;
import lombok.Data;

/**
 *@name CustomerRegisterVerifyReqDTO
 *
 *@author wbwyend
 *@date 2024/05/19 
 */
@Data
@Builder
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
