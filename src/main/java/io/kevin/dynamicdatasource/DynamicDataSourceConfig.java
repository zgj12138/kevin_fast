package io.kevin.dynamicdatasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置多数据源
 * @author ZGJ
 * @date 2017/8/29 23:18
 **/
@Configuration
public class DynamicDataSourceConfig {
    @Bean
    @ConfigurationProperties("spring.datasource.druid.first")
    public DataSource firstDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.second")
    public DataSource secondDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource(DataSource firstDataSource) {
        Map<String, DataSource> targetDataSource = new HashMap<>();
        targetDataSource.put(DataSourceContext.FIRST.getName(), firstDataSource);
        targetDataSource.put(DataSourceContext.SECOND.getName(), secondDataSource());
        return new DynamicDataSource(firstDataSource, targetDataSource);
    }
}
