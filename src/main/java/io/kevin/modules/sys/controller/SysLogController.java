package io.kevin.modules.sys.controller;

import io.kevin.common.utils.PageUtils;
import io.kevin.common.utils.Query;
import io.kevin.common.utils.Result;
import io.kevin.modules.sys.entity.SysLogEntity;
import io.kevin.modules.sys.service.SysLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 系统日志控制器层
 * @author ZGJ
 * @date 2017/7/22 0:31
 **/
@RestController
@RequestMapping("/sys/log")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    @GetMapping("/list")
    @RequiresPermissions("sys:log:list")
    public Result list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<SysLogEntity> sysLogEntityList = sysLogService.queryList(params);
        int total = sysLogService.queryTotal(query);

        PageUtils  pageUtils = new PageUtils(sysLogEntityList, total, query.getLimit(), query.getPage());
        return Result.ok().put("page", pageUtils);
    }
}
