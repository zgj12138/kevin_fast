package io.kevin.modules.sys.service.impl;

import io.kevin.modules.sys.dao.SysRoleMenuDao;
import io.kevin.modules.sys.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色与菜单的对应关系
 * @author ZGJ
 * @date 2017/7/13 21:20
 **/
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl implements SysRoleMenuService{

    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;

    @Override
    @Transactional
    public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
        //先删除角色和菜单的关系
        sysRoleMenuDao.delete(roleId);

        if(menuIdList.size() == 0) {
            return;
        }
        //保存角色与菜单的关系
        Map<String, Object> map = new HashMap<>();
        map.put("roleId", roleId);
        map.put("menuIdList", menuIdList);
        sysRoleMenuDao.save(map);
    }

    @Override
    public List<Long> queryMenuIdList(Long roleId) {
        return sysRoleMenuDao.queryMenuIdList(roleId);
    }
}
