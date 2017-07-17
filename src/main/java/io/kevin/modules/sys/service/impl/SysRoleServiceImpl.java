package io.kevin.modules.sys.service.impl;

import io.kevin.common.exception.GJException;
import io.kevin.common.utils.Constant;
import io.kevin.modules.sys.dao.SysRoleDao;
import io.kevin.modules.sys.entity.SysRoleEntity;
import io.kevin.modules.sys.service.SysRoleMenuService;
import io.kevin.modules.sys.service.SysRoleService;
import io.kevin.modules.sys.service.SysUserRoleService;
import io.kevin.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 系统角色服务层
 * @author ZGJ
 * @date 2017/7/13 21:34
 **/
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService{

    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysUserService sysUserService;

    @Override
    public SysRoleEntity queryObject(Long roleId) {
        return sysRoleDao.queryObject(roleId);
    }

    @Override
    public List<SysRoleEntity> queryList(Map<String, Object> map) {
        return sysRoleDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return queryTotal(map);
    }

    @Override
    @Transactional
    public void save(SysRoleEntity role) {
        role.setCreateTime(new Date());
        sysRoleDao.save(role);

        //检查是否越权
        checkPerms(role);

        //保存用角色与菜单的关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());

    }

    @Override
    @Transactional
    public void update(SysRoleEntity role) {
        sysRoleDao.update(role);

        //检查权限是否越权
        checkPerms(role);

        //更新角色与菜单的关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional
    public void deleteBatch(Long[] roleIds) {
        sysRoleDao.deleteBatch(roleIds);
    }

    @Override
    public List<Long> queryRoleIdList(Long createUserId) {
        return sysRoleDao.queryRoleIdList(createUserId);
    }

    /**
     * 检查权限是否越权，是否超过创建者的权限
     * @param role
     */
    private void checkPerms(SysRoleEntity role) {
        //如果不是超级管理员，则需要判断角色的权限是否超过自己的权限
        if(role.getCreateUserId() != Constant.SUPER_ADMIN) {
            return;
        }
        //查询用户所拥有的菜单列表
        List<Long> menuIdList = sysUserService.queryAllMenuId(role.getCreateUserId());

        //判断是否越权
        if(!menuIdList.containsAll(role.getMenuIdList())) {
            throw new GJException("新增角色的权限，已超出你的权限范围");
        }
    }
}
