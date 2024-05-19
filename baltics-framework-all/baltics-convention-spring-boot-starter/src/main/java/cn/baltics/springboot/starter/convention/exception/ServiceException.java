package cn.baltics.springboot.starter.convention.exception;

import cn.baltics.springboot.starter.convention.errorcode.BaseErrorCode;
import cn.baltics.springboot.starter.convention.errorcode.ErrorCode;

/**
 *@name ServiceException 服务端异常类
 *
 *@author wbwyend
 *@date 2024/05/19 
 */
public class ServiceException extends AbstractException {

    public ServiceException(String message) {
        this(message, null, BaseErrorCode.SERVICE_ERROR);
    }

    public ServiceException(ErrorCode errorCode) {
        this(null, errorCode);
    }

    public ServiceException(String message, ErrorCode errorCode) {
        this(message, null, errorCode);
    }

    public ServiceException(String message, Throwable throwable, ErrorCode errorCode) {
        super(message, throwable, errorCode);
    }

    @Override
    public String toString() {
        return "ServiceException{" +
                "code='" + this.errorCode + "'," +
                "message='" + this.errorMessage + "'" +
                '}';
    }
}
