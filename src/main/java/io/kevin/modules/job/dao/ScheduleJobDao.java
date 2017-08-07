package io.kevin.modules.job.dao;

import io.kevin.modules.job.entity.ScheduleJobEntity;
import io.kevin.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * 定时任务dao层
 * @author ZGJ
 * @date 2017/8/8 0:12
 **/
@Mapper
public interface ScheduleJobDao extends BaseDao<ScheduleJobEntity> {
    /**
     * 批量更新状态
     * @param map
     * @return
     */
    int updateBatch(Map<String, Object> map);
}
