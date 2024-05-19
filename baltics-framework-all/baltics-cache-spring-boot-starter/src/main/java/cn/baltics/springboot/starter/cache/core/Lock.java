package cn.baltics.springboot.starter.cache.core;
/**
 *@name Lock
 *
 *@author wbwyend
 *@date 2024/05/18 
 */
public interface Lock {

    String getMask();

    boolean lock() throws Exception;

    void unlock();
}
