package cn.baltics.springboot.starter.convention.exception;

import cn.baltics.springboot.starter.convention.errorcode.BaseErrorCode;
import cn.baltics.springboot.starter.convention.errorcode.ErrorCode;

/**
 *@name ClientException 客户端异常类
 *
 *@author wbwyend
 *@date 2024/05/19
 */
public class ClientException extends AbstractException {
    
    public ClientException(ErrorCode errorCode) {
        this(null, null, errorCode);
    }
    
    public ClientException(String message) {
        this(message, null, BaseErrorCode.CLIENT_ERROR);
    }
    
    public ClientException(String message, ErrorCode errorCode) {
        this(message, null, errorCode);
    }
    
    public ClientException(String message, Throwable throwable, ErrorCode errorCode) {
        super(message, throwable, errorCode);
    }
    
    @Override
    public String toString() {
        return "ClientException{" +
                "code='" + this.errorCode + "'," +
                "message='" + this.errorMessage + "'" +
                '}';
    }
}
