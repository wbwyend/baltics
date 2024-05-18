package cn.baltics.springboot.starter.cache.lock;

import cn.baltics.springboot.starter.cache.core.Lock;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
    public boolean lock() {
        if (!statue) {
            Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(getMask(), unique, 10, TimeUnit.SECONDS);
            if (result != null && result) statue = true;
            else return false;
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
