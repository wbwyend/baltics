package cn.baltics.springboot.starter.common.enums;

import cn.baltics.springboot.starter.convention.errorcode.ErrorCode;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/05/21 
 */
public enum TokenErrorCode implements ErrorCode {

    TOKEN_ERROR("401", "Token解析错误"),
    TOKEN_EXPIRED_ERROR("401", "Token已过期");

    private final String code;

    private final String message;

    TokenErrorCode(String code, String message) {
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
