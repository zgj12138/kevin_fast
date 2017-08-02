package io.kevin.modules.api.service.impl;

import io.kevin.modules.api.dao.TokenDao;
import io.kevin.modules.api.entity.TokenEntity;
import io.kevin.modules.api.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author ZGJ
 * @date 2017/8/1 21:01
 **/
@Service("tokenService")
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenDao tokenDao;
    //12小时后过期
    private final static int EXPIRE = 3600 * 12;
    @Override
    public TokenEntity queryByUserId(Long userId) {
        return tokenDao.queryByUserId(userId);
    }

    @Override
    public TokenEntity queryByToken(String token) {
        return tokenDao.queryByToken(token);
    }

    @Override
    public void save(TokenEntity token) {
        tokenDao.save(token);
    }

    @Override
    public void update(TokenEntity token) {
        tokenDao.update(token);
    }

    @Override
    public Map<String, Object> createToken(Long userId) {
        //生成一个token
        String token = UUID.randomUUID().toString();
        //当前时间
        Date now = new Date();

        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        //判断是否生成过token
        TokenEntity tokenEntity = queryByUserId(userId);
        if(tokenEntity == null) {
            tokenEntity = new TokenEntity();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //保存token
            save(tokenEntity);
        } else {
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //更新token
            update(tokenEntity);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("expire", EXPIRE);

        return map;
    }
}
