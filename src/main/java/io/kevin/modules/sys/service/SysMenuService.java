package io.kevin.modules.sys.service;

import io.kevin.modules.sys.entity.SysMenuEntity;

import java.util.List;
import java.util.Map;

/**
 * 菜单管理服务层
 * @author ZGJ
 * @date 2017/7/8 21:38
 **/
public interface SysMenuService {
    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     * @param menuIdList 用户菜单ID
     * @return
     */
    List<SysMenuEntity> queryListByParentId(Long parentId, List<Long> menuIdList);

    /**
     * 根据父菜单，查询子菜单
     * @param parentId
     * @return
     */
    List<SysMenuEntity> queryListByParentId(Long parentId);

    /**
     * 获取不包含按钮的菜单列表
     * @return
     */
    List<SysMenuEntity> queryNotButtonList();

    /**
     * 获取用户菜单列表
     * @param userId
     * @return
     */
    List<SysMenuEntity> getUserMenuList(Long userId);

    SysMenuEntity queryObject(Long menuId);

    List<SysMenuEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(SysMenuEntity menu);

    void update(SysMenuEntity menu);

    void deleteBatch(Long[] menuIds);

    /**
     * 查询用户的权限列表
     * @param userId
     * @return
     */
    List<SysMenuEntity> queryUserList(Long userId);
}
