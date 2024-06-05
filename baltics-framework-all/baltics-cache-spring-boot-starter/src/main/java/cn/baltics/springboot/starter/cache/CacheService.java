package cn.baltics.springboot.starter.cache;

import cn.baltics.springboot.starter.cache.convention.LockErrorCode;
import cn.baltics.springboot.starter.cache.convention.LockException;
import cn.baltics.springboot.starter.cache.core.Cache;
import cn.baltics.springboot.starter.cache.core.CacheIfAbsentExecutor;
import cn.baltics.springboot.starter.cache.core.CacheLoader;
import cn.baltics.springboot.starter.cache.lock.DistributedLock;
import cn.baltics.springboot.starter.cache.lock.DistributedLockFactory;
import cn.baltics.springboot.starter.convention.exception.ServiceException;
import com.alibaba.fastjson2.JSON;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Collections;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *@name CacheService 缓存服务
 *
 *@author wbwyend
 *@date 2024/05/18 
 */
public class CacheService implements Cache  {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String SAFE_GET_DISTRIBUTED_LOCK_KEY_PREFIX = "safe_get_distributed_lock_get:";

    @Override
    public void put(String key, Object value, long timeout, TimeUnit unit) {
        String actual = value instanceof String ? (String) value : JSON.toJSONString(value);
        stringRedisTemplate.opsForValue().set(key, actual, timeout, unit);
    }

    public void put(String key, Object value, long timeout) {
        put(key, value, timeout, TimeUnit.SECONDS);
    }

    @Override
    public void put(String key, Object value) {
        String actual = value instanceof String ? (String) value : JSON.toJSONString(value);
        stringRedisTemplate.opsForValue().set(key, actual);
    }

    @Override
    public Boolean putIfAbsent(String key, Object value, long timeout, TimeUnit unit) {
        if (hasKey(key)) return false;
        put(key, value, timeout, unit);
        return true;
    }

    @Override
    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    @Override
    public <T> T get(String key, Class<T> clazz) {
        String value = stringRedisTemplate.opsForValue().get(key);
        if (String.class.isAssignableFrom(clazz)) {
            return (T) value;
        }
        return JSON.parseObject(value, clazz);
    }

    @Override
    public <T> T safeGet(String key, Class<T> clazz, long timeout, TimeUnit unit, CacheLoader<T> loader, CacheIfAbsentExecutor<T> executor) {
        T result = get(key, clazz);
        if (!isNullOrBlank(result)) return result;
        DistributedLock lock = DistributedLockFactory.getLock(SAFE_GET_DISTRIBUTED_LOCK_KEY_PREFIX + key, stringRedisTemplate);
        try {
            boolean isLocked = lock.lock();
            while (!isLocked) {
                if(!isNullOrBlank(result = get(key, clazz))) return result;
                isLocked = lock.lock();
            }
        } catch (LockException e) {
            throw new ServiceException(LockErrorCode.TOO_MANY_WAITING_THREAD_ERROR.message());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            if (isNullOrBlank(result = get(key, clazz))) {
                if (isNullOrBlank(result = loadAndSet(key, timeout, unit, loader))) {
                    executor.execute();
                }
            }
        } finally {
            lock.unlock();
        }
        return result;
    }

    private <T> T loadAndSet(String key, long timeout, TimeUnit unit, CacheLoader<T> cacheLoader) {
        T result = cacheLoader.load();
        if (isNullOrBlank(result)) {
            return result;
        }
        put(key, result, timeout, unit);
        return result;
    }

    private boolean isNullOrBlank(Object value) {
        return value == null || value instanceof String && (Strings.isBlank((String) value) || Strings.isEmpty((String) value));
    }

    @Override
    public Boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    @Override
    public Boolean setPutIfAbsent(String key, Object value) {
        String actual = value instanceof String ? (String) value : JSON.toJSONString(value);
        if (!Boolean.TRUE.equals(stringRedisTemplate.opsForSet().isMember(key, actual))) {
            stringRedisTemplate.opsForSet().add(key, actual);
            return true;
        }
        return false;
    }

    public long hashIncrementAndGet(String key, String hashKey) {
        return stringRedisTemplate.opsForHash().increment(key, hashKey, 1);
    }

    public <T> T executeScript(DefaultRedisScript<T> script, Object... args) {
        return stringRedisTemplate.execute(script,
                Collections.emptyList(),
                args);
    }


}
