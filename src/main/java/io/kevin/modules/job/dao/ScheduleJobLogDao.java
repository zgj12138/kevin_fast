package io.kevin.modules.job.dao;

import io.kevin.modules.job.entity.ScheduleJobLogEntity;
import io.kevin.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 定时任务日志dao
 * @author ZGJ
 * @date 2017/8/8 0:15
 **/
@Mapper
public interface ScheduleJobLogDao extends BaseDao<ScheduleJobLogEntity> {

}
