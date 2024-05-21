package cn.baltics.springboot.starter.web.enums;

import cn.baltics.springboot.starter.convention.errorcode.ErrorCode;

/**
 *@func 访问控制错误
 *
 *@author wbwyend
 *@date 2024/05/21 
 */
public enum AccessControlErrorCode implements ErrorCode {
    TOKEN_IS_MISS("401", "请登录"),
    TOKEN_IS_EXPIRED("401", "cookie已过期，请重新登录");

    private final String code;

    private final String message;

    AccessControlErrorCode(String code, String message) {
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
