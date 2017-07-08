package io.kevin.modules.sys.dao;


import io.kevin.modules.sys.entity.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志
 * @author ZGJ
 * @date 2017/7/3 23:53
*/
@Mapper
public interface SysLogDao extends BaseDao<SysLogEntity> {
	
}
