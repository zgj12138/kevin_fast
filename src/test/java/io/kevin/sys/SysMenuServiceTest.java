package io.kevin.sys;

import io.kevin.KevinApplicationTests;
import io.kevin.modules.sys.entity.SysMenuEntity;
import io.kevin.modules.sys.service.SysMenuService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author ZGJ
 * @date 2017/7/23 14:02
 **/
public class SysMenuServiceTest extends KevinApplicationTests {
    @Autowired
    private SysMenuService sysMenuService;

    @Test
    public void testQueryList() {
        List<SysMenuEntity> menuEntityList = sysMenuService.queryNotButtonList();
        System.out.println(menuEntityList);
    }
}
