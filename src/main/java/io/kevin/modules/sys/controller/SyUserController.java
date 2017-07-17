package io.kevin.modules.sys.controller;

import io.kevin.common.utils.Constant;
import io.kevin.common.utils.PageUtils;
import io.kevin.common.utils.Query;
import io.kevin.common.utils.Result;
import io.kevin.modules.sys.entity.SysUserEntity;
import io.kevin.modules.sys.service.SysUserRoleService;
import io.kevin.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 系统用户控制器层
 * @author ZGJ
 * @date 2017/7/16 14:24
 **/
@RestController
@RequestMapping("/sys/user")
public class SyUserController extends BaseController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @GetMapping("/list")
    public Result List(@RequestParam Map<String, Object> params) {
        //只有超级管理员，才能查看所有的管理员列表
        if(getUserId() != Constant.SUPER_ADMIN) {
            params.put("createUserId", getUserId());
        }
        //查询列表数据
        Query query = new Query(params);
        List<SysUserEntity> userList = sysUserService.queryList(query);
        int total = sysUserService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(userList, total, query.getLimit(), query.getPage());

        return Result.ok().put("page", pageUtil);
    }

    /**
     * 获取登录的用户信息
     */
    @GetMapping("/info")
    public Result info() {
        return Result.ok().put("user", getUser());
    }
}
