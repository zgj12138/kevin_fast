package io.kevin.modules.sys.service;

import java.util.List;

/**
 * 角色与菜单的对应关系服务层
 * @author ZGJ
 * @date 2017/7/9 10:00
 **/
public interface SysRoleMenuService {
    /**
     * 插入或者更新菜单
     * @param roleId
     * @param menuId
     */
    void saveOrUpdate(Long roleId, List<Long> menuIdList);

    /**
     * 根据角色ID,获取菜单ID列表
     * @param roleId
     * @return
     */
    List<Long> queryMenuIdList(Long roleId);
}
