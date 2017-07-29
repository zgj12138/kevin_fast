package io.kevin.modules.sys.controller;

import com.sun.org.apache.regexp.internal.RE;
import io.kevin.common.annotation.SysLog;
import io.kevin.common.utils.PageUtils;
import io.kevin.common.utils.Query;
import io.kevin.common.utils.Result;
import io.kevin.common.validator.ValidatorUtils;
import io.kevin.modules.sys.entity.SysConfigEntity;
import io.kevin.modules.sys.service.SysConfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 系统配置信息控制器层
 * @author ZGJ
 * @date 2017/7/18 0:11
 **/
@RestController
@RequestMapping("/sys/config")
public class SysConfigController extends BaseController {
    @Autowired
    private SysConfigService sysConfigService;

    /**
     * 配置列表
     * @param params
     * @return
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:config:list")
    public Result list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<SysConfigEntity> configEntityList = sysConfigService.queryList(query);
        int total = sysConfigService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(configEntityList, total, query.getLimit(), query.getPage());
        return Result.ok().put("page", pageUtil);
    }

    /**
     * 获取配置信息
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:config:info")
    public Result info(@PathVariable("id") Long id) {
        SysConfigEntity config = sysConfigService.queryObject(id);
        return Result.ok().put("config", config);
    }

    /**
     * 保存配置
     */
    @SysLog("保存配置")
    @PostMapping("/save")
    @RequiresPermissions("sys:config:save")
    public Result save(@RequestBody SysConfigEntity sysConfigEntity) {
        ValidatorUtils.validateEntity(sysConfigEntity);

        sysConfigService.save(sysConfigEntity);
        return Result.ok();
    }

    /**
     * 修改配置
     */
    @SysLog("修改配置")
    @PostMapping("/update")
    @RequiresPermissions("sys:config:update")
    public Result update(@RequestBody SysConfigEntity config){
        ValidatorUtils.validateEntity(config);

        sysConfigService.update(config);

        return Result.ok();
    }

    /**
     * 删除配置
     */
    @SysLog("删除配置")
    @PostMapping("/delete")
    @RequiresPermissions("sys:config:delete")
    public Result delete(@RequestBody Long[] ids){
        sysConfigService.deleteBatch(ids);

        return Result.ok();
    }
}
