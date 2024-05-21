package cn.baltics.springboot.starter.convention.exception;

import cn.baltics.springboot.starter.convention.errorcode.ErrorCode;

import java.util.Optional;

/**
 *@name AbstractException 抽象异常类
 *
 *@author wbwyend
 *@date 2024/05/19 
 */
public abstract class AbstractException extends RuntimeException {
    public final String errorCode;
    public final String errorMessage;

    public AbstractException(String message, Throwable throwable, ErrorCode errorCode) {
        super(message, throwable);
        this.errorCode = errorCode.code();
        this.errorMessage = Optional.ofNullable(message).orElse(errorCode.message());
    }

}
