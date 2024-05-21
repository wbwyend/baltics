package cn.baltics.customer.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *@func 用户注册请求DTO
 *
 *@author wbwyend
 *@date 2024/05/19 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}
