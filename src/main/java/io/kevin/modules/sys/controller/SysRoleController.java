package io.kevin.modules.sys.controller;

import io.kevin.common.annotation.SysLog;
import io.kevin.common.utils.Constant;
import io.kevin.common.utils.PageUtils;
import io.kevin.common.utils.Query;
import io.kevin.common.utils.Result;
import io.kevin.common.validator.ValidatorUtils;
import io.kevin.modules.sys.entity.SysRoleEntity;
import io.kevin.modules.sys.service.SysRoleMenuService;
import io.kevin.modules.sys.service.SysRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色管理控制器层
 *
 * @author ZGJ
 * @date 2017/7/22 17:55
 **/
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends BaseController {
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 查询角色列表
     *
     * @param params
     * @return
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:role:list")
    public Result list(@RequestParam Map<String, Object> params) {
        //如果不是超级管理员，则只查询自己的角色列表
        if (getUserId() != Constant.SUPER_ADMIN) {
            params.put("createUserId", getUser());
        }

        //查询列表数据
        Query query = new Query(params);
        List<SysRoleEntity> roleList = sysRoleService.queryList(query);
        int total = sysRoleService.queryTotal(params);

        PageUtils pageUtil = new PageUtils(roleList, total, query.getLimit(), query.getPage());

        return Result.ok().put("page", pageUtil);
    }

    /**
     * 选择权限列表
     * @return
     */
    @GetMapping("/select")
    @RequiresPermissions("sys:role:select")
    public Result select() {
        Map<String, Object> map = new HashMap<>();

        //如果不是超级管理员，只能选择自己的权限列表
        if(getUserId() != Constant.SUPER_ADMIN) {
            map.put("createUserId", getUserId());
        }
        List<SysRoleEntity> list = sysRoleService.queryList(map);

        return Result.ok().put("list", list);
    }

    /**
     * 角色信息
     * @param roleId
     * @return
     */
    @GetMapping("/info/{roleId}")
    @RequiresPermissions("sys:role:info")
    public Result info(@PathVariable("roleId") Long roleId) {
        SysRoleEntity role = sysRoleService.queryObject(roleId);

        //查询角色对应的菜单
        List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
        role.setMenuIdList(menuIdList);

        return Result.ok().put("role", role);
    }

    /**
     * 保存角色
     * @param role
     * @return
     */
    @SysLog("保存角色")
    @PostMapping("/save")
    @RequiresPermissions("sys:role:save")
    public Result save(@RequestBody SysRoleEntity role){
        ValidatorUtils.validateEntity(role);

        role.setCreateUserId(getUserId());
        sysRoleService.save(role);

        return Result.ok();
    }
    /**
     * 修改角色
     * @param role
     * @return
     */
    @SysLog("修改角色")
    @PostMapping("/update")
    @RequiresPermissions("sys:role:update")
    public Result update(@RequestBody SysRoleEntity role){
        ValidatorUtils.validateEntity(role);

        role.setCreateUserId(getUserId());
        sysRoleService.update(role);

        return Result.ok();
    }

    /**
     * 删除角色
     * @param roleIds
     * @return
     */
    @SysLog("删除角色")
    @PostMapping("/delete")
    @RequiresPermissions("sys:role:delete")
    public Result delete(@RequestBody Long[] roleIds) {
        sysRoleService.deleteBatch(roleIds);

        return Result.ok();
    }
}
