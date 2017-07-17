package io.kevin.modules.sys.service;

import java.util.List;

/**
 * @author ZGJ
 * @date 2017/7/9 10:08
 **/
public interface SysUserRoleService {
    /**
     * 更新或删除
     * @param userId
     * @param roleIdList
     */
    void saveOrUpdate(Long userId, List<Long> roleIdList);

    /**
     * 根据用户ID，查询角色ID列表
     * @param userId
     * @return
     */
    List<Long> queryRoleIdList(Long userId);

    /**
     * 根据用户iD，获取角色ID列表
     */
    void delete(Long userId);
}
