package cn.baltics.springboot.starter.cache.lock;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

/**
 *@name DistributedLockUtil
 *
 *@author wbwyend
 *@date 2024/05/18 
 */
public class DistributedLockFactory {

    private static final DefaultRedisScript<Long> UNLOCK_SCRIPT;

    static {
        UNLOCK_SCRIPT = new DefaultRedisScript<>();
        UNLOCK_SCRIPT.setLocation(new ClassPathResource("lua/unlock.lua"));
        UNLOCK_SCRIPT.setResultType(Long.class);
    }

    public static DistributedLock getLock(String key, StringRedisTemplate stringRedisTemplate) {
        return new DistributedLock(key, stringRedisTemplate, UNLOCK_SCRIPT);
    }
}
