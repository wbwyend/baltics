package cn.baltics.customer.aggregation;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 *@name Customer
 *
 *@author wbwyend
 *@date 2024/05/19 
 */
@Data
@Builder
public class Customer implements Serializable {
    private Long id;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 头像
     */
    private String avatar;

    private String verificationCode;
}
