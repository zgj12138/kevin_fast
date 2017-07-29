package io.kevin.sys;

import io.kevin.KevinApplicationTests;
import io.kevin.modules.sys.entity.SysConfigEntity;
import io.kevin.modules.sys.service.SysConfigService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

/**
 * @author ZGJ
 * @date 2017/7/7 23:07
 **/
public class SysConfigServiceTest extends KevinApplicationTests{
    @Autowired
    private SysConfigService sysConfigService;

    @Test
    public void testList() {
        List<SysConfigEntity> list = sysConfigService.queryList(new HashMap<>());
        list.forEach(s -> System.out.println(s.getKey() + "---" + s.getValue()));
    }
    @Test
    public void testSave() {
        SysConfigEntity config = new SysConfigEntity();
        config.setKey("test");
        config.setValue("test-redis");
        sysConfigService.save(config);
    }
    @Test
    public void testGet() {
        String s = sysConfigService.getValue("test", "a");
        System.out.println(s);
    }
}
