package io.kevin.modules.sys.service;

import io.kevin.modules.sys.entity.SysUserEntity;
import io.kevin.modules.sys.entity.SysUserTokenEntity;

import java.util.Set;

/**
 * Shiro服务层相关接口
 * @author ZGJ
 * @date 2017/7/9 10:33
 **/
public interface ShiroService {
    /**
     * 获取用户权限列表
     * @param userId
     * @return
     */
    Set<String> getUserPermission(Long userId);

    /**
     * 根据token字符串查询Token
     * @param token
     * @return
     */
    SysUserTokenEntity queryByToken(String token);

    /**
     * 根据用户ID查询Token
     * @param userId
     * @return
     */
    SysUserEntity queryUser(Long userId);
}
