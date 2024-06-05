package cn.baltics.order.enums;

import cn.baltics.springboot.starter.convention.errorcode.ErrorCode;

/**
 *@func 
 *
 *@author wbwyend
 *@date 2024/06/05 
 */
public enum OrderErrorEnum implements ErrorCode {
    REPEATED_SUBMISSION_ERROR("200", "您已提交了订单，请勿重复提交"),
    UNDERSTOCK("200", "库存不足");


    private final String code;
    private final String message;

    OrderErrorEnum(String code, String message) {
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
