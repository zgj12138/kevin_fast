package io.kevin.modules.oss.service;

import io.kevin.modules.oss.entity.SysOssEntity;

import java.util.List;
import java.util.Map;

/**
 * 文件上传
 * @author ZGJ
 * @date 2017/8/3 21:42
 **/
public interface SysOssService {

    SysOssEntity queryObject(Long id);

    List<SysOssEntity> queryList (Map<String, Object> map);

    void save(SysOssEntity sysOss);

    void update(SysOssEntity sysOss);

    void delete(Long id);

    void deleteBatch(Long[] ids);
}
