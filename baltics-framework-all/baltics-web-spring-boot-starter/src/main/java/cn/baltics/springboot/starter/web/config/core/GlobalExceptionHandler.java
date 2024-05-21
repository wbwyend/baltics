package cn.baltics.springboot.starter.web.config.core;

import cn.baltics.springboot.starter.convention.exception.AbstractException;
import cn.baltics.springboot.starter.convention.exception.ClientException;
import cn.baltics.springboot.starter.convention.exception.ServiceException;
import cn.baltics.springboot.starter.convention.result.Results;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 *@name GlobalExceptionHandler
 *
 *@author wbwyend
 *@date 2024/05/19 
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ClientException.class)
    public Results clientException(HttpServletRequest request, ClientException ex) {
        return Results.fail(ex.errorMessage);
    }

    /**
     * 拦截应用内抛出的异常
     */
    @ExceptionHandler(value = ServiceException.class)
    public Results abstractException(HttpServletRequest request, AbstractException ex) {
        return Results.fail();
    }

    /**
     * 拦截未捕获异常
     */
    @ExceptionHandler(value = Throwable.class)
    public Results defaultErrorHandler(HttpServletRequest request, Throwable throwable) {
        return Results.fail();
    }
}
