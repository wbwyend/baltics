package cn.baltics.springboot.starter.cache.core;

import java.util.concurrent.TimeUnit;

/**
 *@name Cache 缓存接口
 *
 *@author wbwyend
 *@date 2024/05/18 
 */
public interface Cache {
    /**
     * 放入缓存
     * @param key 键
     * @param value 值
     * @param timeout 过期时间
     * @param unit 时间单位
     */
    void put(String key, Object value, long timeout, TimeUnit unit);

    /**
     * 放入缓存
     * @param key 键
     * @param value 值
     */
    void put(String key, Object value);

    /**
     * 如果键不存在放入缓存并返回true，否则返回false
     * @param key 键
     */
    Boolean putIfAbsent(String key, Object value, long timeout, TimeUnit unit);

    /**
     * 删除缓存
     * @param key 键
     */
    void delete(String key);

    /**
     * 获取缓存
     * @param key 键
     */
    <T> T get(String key, Class<T> clazz);

    /**
     * 安全方式获取缓存
     * @param key 键
     * @param clazz 类
     * @param timeout 过期时间
     * @param unit 时间单位
     * @param loader 缓存加载器
     * @param executor 缓存缺失执行器
     */
    <T> T safeGet(String key, Class<T> clazz, long timeout, TimeUnit unit, CacheLoader<T> loader, CacheIfAbsentExecutor<T> executor);

    /**
     * 判断 key 是否存在
     * @param key 键
     */
    Boolean hasKey(String key);

}
