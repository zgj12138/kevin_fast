package io.kevin.modules.oss.dao;

import io.kevin.modules.oss.entity.SysOssEntity;
import io.kevin.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文件上传dao层
 * @author ZGJ
 * @date 2017/8/3 21:41
 **/
@Mapper
public interface SysOssDao extends BaseDao<SysOssEntity> {
}
