package io.kevin.modules.job.task;

import io.kevin.modules.sys.entity.SysUserEntity;
import io.kevin.modules.sys.service.SysUserService;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 测试定时任务
 *
 * @author ZGJ
 * @date 2017/10/6 0:39
 **/
@Component("testTask")
public class TestTask {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysUserService sysUserService;

    /**
     * 定时任务只能接受一个参数，如果有多个参数，就传json
     *
     * @param params
     */
    public void test1(String params) {
        logger.info("我是带参数的test1方法，正在被执行，参数为： " + params);
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SysUserEntity userEntity = sysUserService.queryObject(1L);
        System.out.println(ToStringBuilder.reflectionToString(userEntity));
    }

    public void test2() {
        logger.info("我是不带参数的test2方法，正在被执行");
    }

}
