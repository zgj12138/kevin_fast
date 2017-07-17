package io.kevin.modules.sys.controller;

import io.kevin.common.utils.ShiroUtils;
import io.kevin.modules.sys.entity.SysUserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Controller公共组件
 * @author ZGJ
 * @date 2017/7/16 14:22
 **/
public class BaseController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected SysUserEntity getUser() {
        return (SysUserEntity) ShiroUtils.getUserEntity();
    }
    protected long getUserId() {
        return getUser().getUserId();
    }
}
