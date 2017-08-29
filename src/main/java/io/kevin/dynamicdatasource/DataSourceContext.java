package io.kevin.dynamicdatasource;

/**
 * 增加多数据源，在此配置
 * @author ZGJ
 * @date 2017/8/29 23:08
 **/
public enum DataSourceContext {
    FIRST("first"),
    SECOND("second");

    private String name;

    DataSourceContext(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
