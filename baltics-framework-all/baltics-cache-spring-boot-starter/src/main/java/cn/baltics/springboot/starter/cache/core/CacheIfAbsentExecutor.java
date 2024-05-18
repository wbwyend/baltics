package cn.baltics.springboot.starter.cache.core;
/**
 *@name CacheIfAbsentExecutor 缓存缺失处理器接口
 *
 *@author wbwyend
 *@date 2024/05/18 
 */
public interface CacheIfAbsentExecutor<T> {
    /**
     * 处理缓存缺少情况
     */
    void execute();
}
