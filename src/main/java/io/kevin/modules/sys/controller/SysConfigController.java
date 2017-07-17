package io.kevin.modules.sys.controller;

import io.kevin.common.utils.PageUtils;
import io.kevin.common.utils.Query;
import io.kevin.common.utils.Result;
import io.kevin.modules.sys.entity.SysConfigEntity;
import io.kevin.modules.sys.service.SysConfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 系统配置信息控制器层
 * @author ZGJ
 * @date 2017/7/18 0:11
 **/
@RestController
public class SysConfigController extends BaseController {
    @Autowired
    private SysConfigService sysConfigService;

    @RequestMapping("/list")
    @RequiresPermissions("sys:config:list")
    public Result list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<SysConfigEntity> configEntityList = sysConfigService.queryList(query);
        int total = sysConfigService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(configEntityList, total, query.getLimit(), query.getPage());
        return Result.ok().put("page", pageUtil);
    }
}
