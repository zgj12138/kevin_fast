package io.kevin.modules.sys.service.impl;

import io.kevin.common.utils.Constant;
import io.kevin.modules.sys.dao.SysMenuDao;
import io.kevin.modules.sys.dao.SysUserDao;
import io.kevin.modules.sys.dao.SysUserTokenDao;
import io.kevin.modules.sys.entity.SysMenuEntity;
import io.kevin.modules.sys.entity.SysUserEntity;
import io.kevin.modules.sys.entity.SysUserTokenEntity;
import io.kevin.modules.sys.service.ShiroService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author ZGJ
 * @date 2017/7/9 10:36
 **/
@Service
public class ShiroServiceImpl implements ShiroService {

    @Autowired
    private SysMenuDao sysMenuDao;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysUserTokenDao sysUserTokenDao;

    @Override
    public Set<String> getUserPermissions(Long userId) {
        List<String> permsList;
        //系统管理员，拥有最高权限
        if(userId == Constant.SUPER_ADMIN) {
            List<SysMenuEntity> menuList = sysMenuDao.queryList(new HashMap<>());
            permsList = new ArrayList<>(menuList.size());
            for(SysMenuEntity menu : menuList) {
                permsList.add(menu.getPerms());
            }
        } else {
            permsList = sysUserDao.queryAllPerms(userId);
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for(String perms : permsList) {
            if(StringUtils.isBlank(perms)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }

    @Override
    public SysUserTokenEntity queryByToken(String token) {
        return sysUserTokenDao.queryByToken(token);
    }

    @Override
    public SysUserEntity queryUser(Long userId) {
        return sysUserDao.queryObject(userId);
    }
}
