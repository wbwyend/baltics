package cn.baltics.springboot.starter.cache.convention;

import cn.baltics.springboot.starter.convention.exception.ServiceException;

/**
 *@name LockException
 *
 *@author wbwyend
 *@date 2024/05/19 
 */
public class LockException extends ServiceException {

    public LockException(String message) {
        super(message);
    }
}
