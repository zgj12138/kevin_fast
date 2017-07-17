package io.kevin.modules.sys.service.impl;

import io.kevin.common.utils.Constant;
import io.kevin.modules.sys.dao.SysMenuDao;
import io.kevin.common.utils.Constant.MenuType;
import io.kevin.modules.sys.entity.SysMenuEntity;
import io.kevin.modules.sys.service.SysMenuService;
import io.kevin.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ZGJ
 * @date 2017/7/9 14:49
 **/
@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService{
    @Autowired
    private SysMenuDao sysMenuDao;
    @Autowired
    private SysUserService sysUserService;


    @Override
    public List<SysMenuEntity> queryListByParentId(Long parentId, List<Long> menuIdList) {
        List<SysMenuEntity> menuList = queryListByParentId(parentId);
        if(menuIdList == null) {
            return menuList;
        }

        List<SysMenuEntity> userMenuList = new ArrayList<>();
        for(SysMenuEntity menu : menuList) {
            if(menuIdList.contains(menu.getMenuId())) {
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    @Override
    public List<SysMenuEntity> queryListByParentId(Long parentId) {
        return sysMenuDao.queryListByParentId(parentId);
    }

    @Override
    public List<SysMenuEntity> queryNotButtonList() {
        return sysMenuDao.queryNotButtonList();
    }

    @Override
    public List<SysMenuEntity> getUserMenuList(Long userId) {
        //系统管理员，拥有最高权限
        if(userId == Constant.SUPER_ADMIN) {
            return getUserMenuList(null);
        }

        //用户菜单列表
        List<Long> meuIdList = sysUserService.queryAllMenuId(userId);
        return  getAllMenuList(null);

    }

    @Override
    public SysMenuEntity queryObject(Long menuId) {
        return sysMenuDao.queryObject(menuId);
    }

    @Override
    public List<SysMenuEntity> queryList(Map<String, Object> map) {
        return sysMenuDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysMenuDao.queryTotal(map);
    }

    @Override
    public void save(SysMenuEntity menu) {
        sysMenuDao.save(menu);
    }

    @Override
    public void update(SysMenuEntity menu) {
        sysMenuDao.update(menu);
    }

    @Override
    public void deleteBatch(Long[] menuIds) {
        sysMenuDao.deleteBatch(menuIds);
    }

    @Override
    public List<SysMenuEntity> queryUserList(Long userId) {
        return sysMenuDao.queryUserList(userId);
    }

    /**
     * 获取所有菜单列表
     */
    private List<SysMenuEntity> getAllMenuList(List<Long> menuIdList) {
        //查询系统跟菜单列表
        List<SysMenuEntity> menuList = queryListByParentId(0L, menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);
        return  menuList;
    }

    /**
     * 递归
     * @param menuList
     * @param menuIdList
     */
    private List<SysMenuEntity> getMenuTreeList(List<SysMenuEntity> menuList, List<Long> menuIdList) {
        List<SysMenuEntity> subMenuList = new ArrayList<>();
        for (SysMenuEntity menu : menuList) {
            if(menu.getType() == MenuType.CATALOG.getValue()){//目录
                menu.setList(getMenuTreeList(queryListByParentId(menu.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(menu);
        }
        return subMenuList;
    }
}
