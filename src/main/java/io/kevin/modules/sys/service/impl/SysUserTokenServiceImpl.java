package io.kevin.modules.sys.service.impl;

import io.kevin.common.utils.Result;
import io.kevin.modules.sys.dao.SysUserTokenDao;
import io.kevin.modules.sys.entity.SysUserTokenEntity;
import io.kevin.modules.sys.service.SysUserTokenService;
import io.kevin.modules.sys.oauth2.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author ZGJ
 * @date 2017/7/13 23:15
 **/
@Service("sysUserTokenServiceImpl")
public class SysUserTokenServiceImpl implements SysUserTokenService{
    @Autowired
    private SysUserTokenDao sysUserTokenDao;

    //12小时后过期
    private final static int EXPIRE = 3600 * 12;
    @Override
    public SysUserTokenEntity queryByUserId(Long userId) {
        return sysUserTokenDao.queryByUserId(userId);
    }

    @Override
    public SysUserTokenEntity queryByToken(String token) {
        return sysUserTokenDao.queryByToken(token);
    }

    @Override
    public void save(SysUserTokenEntity token) {
        sysUserTokenDao.save(token);
    }

    @Override
    public void update(SysUserTokenEntity token) {
        sysUserTokenDao.update(token);
    }

    @Override
    public Result createToken(Long userId) {
        //生成一个Token
        String token = TokenGenerator.generateValue();

        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        //判断是否生成过token
        SysUserTokenEntity tokenEntity = queryByUserId(userId);
        if(tokenEntity == null) {
            tokenEntity = new SysUserTokenEntity();
            tokenEntity.setUserId(userId);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //保存tooken
            sysUserTokenDao.save(tokenEntity);
        } else {
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //更新token
            update(tokenEntity);
        }
        Result result = Result.ok().put("token", token)
                .put("expire", EXPIRE);
        return  result;
    }
}
