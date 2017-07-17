package io.kevin.modules.sys.service;

import io.kevin.modules.sys.entity.SysRoleEntity;

import java.util.List;
import java.util.Map;

/**
 * 角色服务层
 * @author ZGJ
 * @date 2017/7/9 10:03
 **/
public interface SyRoleService {

    SysRoleEntity queryObjedt(Long roleId);

    List<SysRoleEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(SysRoleEntity role);

    void update(SysRoleEntity role);

    /**
     * 查询用户创建的角色ID列表
     * @param createUserId
     * @return
     */
    List<Long> queryRoleIdList(Long createUserId);
}
