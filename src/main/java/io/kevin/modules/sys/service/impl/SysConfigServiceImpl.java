package io.kevin.modules.sys.service.impl;

import com.google.gson.Gson;
import io.kevin.common.exception.GJException;
import io.kevin.modules.sys.dao.SysConfigDao;
import io.kevin.modules.sys.entity.SysConfigEntity;
import io.kevin.modules.sys.service.SysConfigService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author ZGJ
 * @date 2017/7/9 14:30
 **/
@Service("sysConfigService")
public class SysConfigServiceImpl implements SysConfigService{

    @Autowired
    private SysConfigDao sysConfigDao;

    @Override
    @Transactional
    public void save(SysConfigEntity configEntity) {
        sysConfigDao.save(configEntity);
    }

    @Override
    public void update(SysConfigEntity configEntity) {
        sysConfigDao.update(configEntity);
    }

    @Override
    public void updateValueByKey(String key, String value) {
        sysConfigDao.updateValueByKey(key, value);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        sysConfigDao.deleteBatch(ids);
    }

    @Override
    public List<SysConfigEntity> queryList(Map<String, Object> map) {
        return sysConfigDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysConfigDao.queryTotal(map);
    }

    @Override
    public SysConfigEntity queryObject(Long id) {
        return sysConfigDao.queryObject(id);
    }

    @Override
    public String getValue(String key, String defaultValue) {
        String value = sysConfigDao.queryByKey(key);
        if(StringUtils.isBlank(value)) {
            return defaultValue;
        }
        return value;
    }

    @Override
    public <T> T getConfigObject(String key, Class<T> clazz) {
        String value = getValue(key, null);
        if(StringUtils.isNotBlank(value)) {
            return new Gson().fromJson(value, clazz);
        }
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new GJException("获取参数失败");
        }
    }
}
