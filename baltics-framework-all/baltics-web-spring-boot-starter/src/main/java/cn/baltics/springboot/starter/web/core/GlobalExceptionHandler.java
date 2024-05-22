package cn.baltics.springboot.starter.web.core;

import cn.baltics.springboot.starter.common.util.StringUtil;
import cn.baltics.springboot.starter.convention.exception.AbstractException;
import cn.baltics.springboot.starter.convention.exception.ClientException;
import cn.baltics.springboot.starter.convention.exception.ServiceException;
import cn.baltics.springboot.starter.convention.result.Results;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *@name GlobalExceptionHandler
 *
 *@author wbwyend
 *@date 2024/05/19 
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ClientException.class)
    public Results clientException(HttpServletResponse response, ClientException ex) {
        ex.printStackTrace();
        if (StringUtil.isNotNullAndBlank(ex.errorCode)) response.setStatus(Integer.parseInt(ex.errorCode));
        return Results.fail(Integer.parseInt(ex.errorCode), ex.errorMessage);
    }

    /**
     * 拦截应用内抛出的异常
     */
    @ExceptionHandler(value = ServiceException.class)
    public Results abstractException(HttpServletRequest request, AbstractException ex) {
        ex.printStackTrace();
        return Results.fail();
    }

    /**
     * 拦截未捕获异常
     */
    @ExceptionHandler(value = Throwable.class)
    public Results defaultErrorHandler(HttpServletRequest request, Throwable throwable) {
        throwable.printStackTrace();
        return Results.fail();
    }
}
