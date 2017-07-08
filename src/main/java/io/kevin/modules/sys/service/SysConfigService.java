package io.kevin.modules.sys.service;

import io.kevin.modules.sys.entity.SysConfigEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统配置信息服务层
 * @author ZGJ
 * @date 2017/7/8 21:29
 **/
public interface SysConfigService {
    void save(SysConfigEntity configEntity);

    void update(SysConfigEntity configEntity);

    /**
     * 根据key,更新value
     * @param key
     * @param value
     */
    void updateValueByKey(String key, String value);

    void deleteBatch(Long[] ids);

    List<SysConfigEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    SysConfigEntity queryObject(Long id);

    /**
     * 根据key，获取配置的value值
     * @param key
     * @param defaultValue 缺省值
     * @return
     */
    String getValue(String key, String defaultValue);

    /**
     * 根据key,获取value的object值
     * @param key
     * @param clazz object对象的class
     * @param <T>
     * @return
     */
    <T> T getConfigObject(String key, Class<T> clazz);
}
