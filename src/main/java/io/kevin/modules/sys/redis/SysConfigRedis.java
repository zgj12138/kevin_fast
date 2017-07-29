package io.kevin.modules.sys.redis;

import io.kevin.common.utils.RedisKeys;
import io.kevin.common.utils.RedisUtils;
import io.kevin.modules.sys.entity.SysConfigEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 系统配置Redis
 * @author ZGJ
 * @date 2017/7/29 14:37
 **/
@Component
public class SysConfigRedis {
    @Autowired
    private RedisUtils redisUtils;

    public void saveOrUpdate(SysConfigEntity configEntity) {
        if(configEntity == null) {
            return;
        }

        String key = RedisKeys.getSysConfigKey(configEntity.getKey());
        redisUtils.set(key, configEntity);
    }

    public void delete(String configKey) {
        String key = RedisKeys.getSysConfigKey(configKey);
        redisUtils.delete(key);
    }
    public SysConfigEntity get(String configKey){
        String key = RedisKeys.getSysConfigKey(configKey);
        return redisUtils.get(key, SysConfigEntity.class);
    }
}
