package io.kevin;

import io.kevin.dynamicdatasource.DataSourceContext;
import io.kevin.dynamicdatasource.DynamicDataSource;
import io.kevin.modules.api.entity.UserEntity;
import io.kevin.modules.api.service.UserService;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ZGJ
 * @date 2017/8/30 0:00
 **/
public class DynamicDataSourceTest extends KevinApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    public void test() {
        UserEntity user1 = userService.queryObject(1L);
        System.out.println(ToStringBuilder.reflectionToString(user1));

        //切换数据源
        DynamicDataSource.setDataSource(DataSourceContext.SECOND.getName());

        UserEntity user2 = userService.queryObject(1L);
        System.out.println(ToStringBuilder.reflectionToString(user2));

    }
}
