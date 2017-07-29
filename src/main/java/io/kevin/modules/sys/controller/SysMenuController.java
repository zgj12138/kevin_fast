package io.kevin.modules.sys.controller;

import com.sun.org.apache.regexp.internal.RE;
import io.kevin.common.annotation.SysLog;
import io.kevin.common.exception.GJException;
import io.kevin.common.utils.Constant;
import io.kevin.common.utils.Result;
import io.kevin.modules.sys.entity.SysMenuEntity;
import io.kevin.modules.sys.service.ShiroService;
import io.kevin.modules.sys.service.SysMenuService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * 系统菜单控制器
 * @author ZGJ
 * @date 2017/7/22 12:15
 **/
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends BaseController{
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private ShiroService shiroService;

    /**
     * 所有菜单列表
     * @return
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:menu:list")
    public List<SysMenuEntity> list() {
        List<SysMenuEntity> menuEntityList = sysMenuService.queryList(new HashMap<>());

        return menuEntityList;
    }

    /**
     * 选择菜单（添加，修改菜单
     * @return
     */
    @GetMapping("/select")
    @RequiresPermissions("sys:menu:select")
    public Result select() {
        //查询列表数据
        List<SysMenuEntity> menuEntityList = sysMenuService.queryNotButtonList();

        //添加顶级菜单
        SysMenuEntity root = new SysMenuEntity();
        root.setMenuId(0L);
        root.setName("一级菜单");
        root.setParentId(-1L);
        root.setOpen(true);
        menuEntityList.add(root);
        return Result.ok().put("menuList", menuEntityList);
    }

    /**
     * 角色授权菜单
     * @return
     */
    @GetMapping("/perms")
    @RequiresPermissions("sys:menu:perms")
    public Result perms() {
        //查询列表数据
        List<SysMenuEntity> menuList = null;

        //只有超级管理员，才能查看所有管理员列表
        if(getUserId() == Constant.SUPER_ADMIN) {
            menuList = sysMenuService.queryList(new HashMap<>());
        } else {
            menuList = sysMenuService.queryUserList(getUserId());
        }
        return Result.ok().put("menuList", menuList);
    }

    /**
     * 菜单信息
     * @param menuId
     * @return
     */
    @GetMapping("/info/{menuId}")
    @RequiresPermissions("sys:menu:info")
    public Result info(@PathVariable("menuId") Long menuId) {
        SysMenuEntity menu = sysMenuService.queryObject(menuId);
        return Result.ok().put("menu", menu);
    }

    /**
     * 保存菜单
     * @param menu
     * @return
     */
    @GetMapping("/save")
    @RequiresPermissions("sys:menu:save")
    public Result save(@RequestParam SysMenuEntity menu) {
        //数据校验
        verifyForm(menu);

        sysMenuService.save(menu);
        return Result.ok();
    }

    /**
     * 修改菜单
     * @param menu
     * @return
     */
    @SysLog("保存菜单")
    @GetMapping("/update")
    @RequiresPermissions("sys:menu:update")
    public Result update(@RequestBody SysMenuEntity menu) {
        //数据校验
        verifyForm(menu);
        sysMenuService.update(menu);
        return Result.ok();
    }

    @SysLog("删除菜单")
    @GetMapping("/delete")
    @RequiresPermissions("sys:menu:delete")
    public Result delete(Long menuId) {
        if(menuId <= 30) {
            return Result.error("系统菜单，不能删除");
        }

        //判断是否有子菜单或者按钮
        List<SysMenuEntity> menuList = sysMenuService.queryListByParentId(menuId);
        if(menuList.size() > 0) {
            return Result.error("请先删除子菜单或者按钮");
        }

        sysMenuService.deleteBatch(new Long[]{menuId});

        return Result.ok();
    }

    /**
     * 用户菜单列表
     */
    @GetMapping("/user")
    public Result user(){
        List<SysMenuEntity> menuList = sysMenuService.getUserMenuList(getUserId());
        Set<String> permissions = shiroService.getUserPermissions(getUserId());
        return Result.ok().put("menuList", menuList).put("permissions", permissions);
    }
    /**
     * 验证参数是否正确
     * @param menu
     */
    private void verifyForm(SysMenuEntity menu) {
        if(StringUtils.isBlank(menu.getName())) {
            throw new GJException("菜单名称不能为空");
        }
        if(menu.getParentId() == null) {
            new GJException("上级菜单不能为空");
        }
        //菜单
        if(menu.getType() == Constant.MenuType.MENU.getValue()) {
            if(StringUtils.isBlank(menu.getUrl())) {
                throw new GJException("菜单URL不能为空");
            }
        }

        //上级菜单类型
        int parentType = Constant.MenuType.CATALOG.getValue();
        if(menu.getType() != 0) {
            SysMenuEntity parentMenu = sysMenuService.queryObject(menu.getParentId());
            parentType = parentMenu.getType();
        }

        //目录，菜单
        if(menu.getType() == Constant.MenuType.CATALOG.getValue() ||
                menu.getType() == Constant.MenuType.MENU.getValue()) {
            if(parentType != Constant.MenuType.CATALOG.getValue()) {
                throw new GJException("上级菜单只能为目录类型");
            }
            return;
        }

        //按钮
        if(menu.getType() == Constant.MenuType.BUTTON.getValue()) {
            if(parentType != Constant.MenuType.MENU.getValue()) {
                throw new GJException("上级菜单只能为菜单类型");
            }
        }
    }
}
