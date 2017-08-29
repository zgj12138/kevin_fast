package io.kevin.dynamicdatasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态切换数据源
 * @author ZGJ
 * @date 2017/8/29 23:09
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {
    private static final ThreadLocal<String> contextHoler = new ThreadLocal<>();

    public DynamicDataSource(DataSource defaultTargetDataSource, Map<String, DataSource> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(new HashMap<>(targetDataSources));
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return getDataSource();
    }

    public static void setDataSource(String dataSource) {
        contextHoler.set(dataSource);
    }

    private static String getDataSource() {
        return contextHoler.get();
    }

    public static void clearDataSource() {
        contextHoler.remove();
    }
}
