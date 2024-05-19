package cn.baltics.springboot.starter.cache.lock;

import cn.baltics.springboot.starter.cache.convention.LockErrorCode;
import cn.baltics.springboot.starter.cache.convention.LockException;
import cn.baltics.springboot.starter.cache.core.Lock;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *@name DistributedLock
 *
 *@author wbwyend
 *@date 2024/05/18 
 */
public class DistributedLock implements Lock {
    private final String mask;
    private final String unique;
    private boolean statue;
    private final StringRedisTemplate stringRedisTemplate;
    private final DefaultRedisScript<Long> script;
    private static final long TIME_WAIT = 200;
    private static AtomicInteger count;
    private static final int MAX_THREADS = 100;
    private static final int DEFAULT_TIMEOUT = 10;

    static {
        count = new AtomicInteger(0);
    }

    public DistributedLock(String mask, StringRedisTemplate stringRedisTemplate, DefaultRedisScript<Long> script) {
        this.mask = mask;
        this.stringRedisTemplate = stringRedisTemplate;
        this.statue = false;
        this.unique = String.valueOf(UUID.randomUUID());
        this.script = script;
    }

    @Override
    public String getMask() {
        return this.mask;
    }

    @Override
    public boolean lock() throws Exception {
        if (!statue) {
            Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(getMask(), unique, DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            if (result != null && result) {
                statue = true;
            }
            else {
                try {
                    if (count.getAndIncrement() <= MAX_THREADS) this.wait(TIME_WAIT);
                    else throw new LockException(LockErrorCode.TOO_MANY_WAITING_THREAD_ERROR.message());
                } finally {
                    count.getAndDecrement();
                }
                return false;
            }
        }
        return true;
    }

    @Override
    public void unlock() {
        if (statue) {
            stringRedisTemplate.execute(script,
                    Collections.singletonList(getMask()),
                    unique);
            statue = false;
        }
    }
}
