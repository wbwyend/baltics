package cn.baltics.springboot.starter.cache.convention;

import cn.baltics.springboot.starter.convention.errorcode.ErrorCode;

/**
 *@name LockErrorCode
 *
 *@author wbwyend
 *@date 2024/05/19 
 */
public enum LockErrorCode implements ErrorCode {

    TOO_MANY_WAITING_THREAD_ERROR("", "阻塞的线程过多，执行拒绝请求策略");

    private final String code;
    private final String message;

    LockErrorCode(String code, String message) {
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
