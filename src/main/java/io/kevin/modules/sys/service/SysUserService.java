package io.kevin.modules.sys.service;

import io.kevin.modules.sys.entity.SysUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 * @author ZGJ
 * @date 2017/7/9 10:13
 **/
public interface SysUserService {
    /**
     * 查询用户的所有权限
     * @param userId
     * @return
     */
    List<String> queryAllPerms(Long userId);

    /**
     * 根据用户ID，查询所有菜单ID
     * @param userId
     * @return
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    SysUserEntity queryByUserName(String username);

    SysUserEntity queryObject(Long userId);

    List<SysUserEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(SysUserEntity user);

    void update(SysUserEntity user);

    void deleteBatch(Long[] userId);

    int updatePassword(Long userId, String password, String newPassword);
}
