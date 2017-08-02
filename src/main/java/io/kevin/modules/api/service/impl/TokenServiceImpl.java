package io.kevin.modules.api.service.impl;

import io.kevin.modules.api.dao.TokenDao;
import io.kevin.modules.api.entity.TokenEntity;
import io.kevin.modules.api.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author ZGJ
 * @date 2017/8/1 21:01
 **/
@Service("tokenService")
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenDao tokenDao;
    //12小时后过期
    private final static int REXPIRE = 3600 * 12;
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
        
    }
}
