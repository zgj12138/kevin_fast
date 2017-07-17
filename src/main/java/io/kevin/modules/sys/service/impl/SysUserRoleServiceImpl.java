package io.kevin.modules.sys.service.impl;

import io.kevin.modules.sys.dao.SysUserRoleDao;
import io.kevin.modules.sys.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户与角色对应关系
 * @author ZGJ
 * @date 2017/7/13 21:37
 **/
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl implements SysUserRoleService{

    @Autowired
    private SysUserRoleDao sysUserRoleDao;
    @Override
    public void saveOrUpdate(Long userId, List<Long> roleIdList) {
        if(roleIdList.size() == 0) {
            return;
        }

        //先删除用用户与角色的关系
        sysUserRoleDao.delete(userId);

        //保存用户与角色的关系
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("reoleIdList", roleIdList);
        sysUserRoleDao.save(map);
    }

    @Override
    public List<Long> queryRoleIdList(Long userId) {
        return sysUserRoleDao.queryRoleIdList(userId);
    }

    @Override
    public void delete(Long userId) {
        sysUserRoleDao.delete(userId);
    }
}
