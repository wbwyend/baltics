package cn.baltics.springboot.starter.cache.core;
/**
 *@name CacheLoader 缓存加载器接口
 *
 *@author wbwyend
 *@date 2024/05/18 
 */
public interface CacheLoader<T> {
    /**
     * 加载缓存
     */
    T load();
}
