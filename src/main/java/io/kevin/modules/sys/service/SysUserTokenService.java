package io.kevin.modules.sys.service;

import io.kevin.common.utils.Result;
import io.kevin.modules.sys.entity.SysUserTokenEntity;

/**
 * 用户Token服务层
 * @author ZGJ
 * @date 2017/7/9 10:28
 **/
public interface SysUserTokenService {
    /**
     * 根据用户ID查询Token
     * @param userId
     * @return
     */
    SysUserTokenEntity queryByUserId(Long userId);

    /**
     * 根据token字符串查询Token
     * @param token
     * @return
     */
    SysUserTokenEntity queryByToken(String token);

    void save(SysUserTokenEntity token);

    void update(SysUserTokenEntity token);

    /**
     * 生成Token
     * @param userId
     * @return
     */
    Result createToken(Long userId);
}
