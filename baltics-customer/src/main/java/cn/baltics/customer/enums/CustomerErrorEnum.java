package cn.baltics.customer.enums;

import cn.baltics.springboot.starter.convention.errorcode.ErrorCode;

/**
 *@name CustomerErrorEnum
 *
 *@author wbwyend
 *@date 2024/05/19 
 */
public enum CustomerErrorEnum implements ErrorCode {
    CUSTOMER_REGISTER_DUPLICATION("", "邮箱或用户名已注册"),
    SEND_VERIFICATION_CODE_EMAIL_FAIL("", "验证码邮件发送失败"),
    VERIFICATION_CODE_ERROR("", "验证码错误"),
    USERNAME_OR_PASSWORD_ERROR("", "用户名或密码错误"),
    USERNAME_OR_MAIL_ERROR("", "用户名或邮箱错误");

    private final String code;

    private final String message;

    CustomerErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
