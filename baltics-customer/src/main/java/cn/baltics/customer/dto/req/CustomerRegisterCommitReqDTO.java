package cn.baltics.customer.dto.req;

import lombok.Builder;
import lombok.Data;

/**
 *@name CustomerRegisterRequset
 *
 *@author wbwyend
 *@date 2024/05/19 
 */
@Data
@Builder
public class CustomerRegisterCommitReqDTO {
    /**
     * 邮箱
     */
    private String mail;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户名
     */
    private String username;
    /**
     * 验证码
     */
    private String code;
}
