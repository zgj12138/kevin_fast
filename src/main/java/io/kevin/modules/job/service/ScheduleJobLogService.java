package io.kevin.modules.job.service;

import io.kevin.modules.job.entity.ScheduleJobLogEntity;

import java.util.List;
import java.util.Map;

/**
 * 定时任务日志service
 * @author ZGJ
 * @date 2017/8/8 23:07
 **/
public interface ScheduleJobLogService {
    /**
     * 根据id,查询定时任务日志
     * @param jobId
     * @return
     */
    ScheduleJobLogEntity queryObject(Long jobId);

    /**
     * 查询定时任务日志列表
     * @param map
     * @return
     */
    List<ScheduleJobLogEntity> queryList(Map<String, Object> map);

    /**
     * 查询总数
     * @param map
     * @return
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存定时任务日志
     * @param scheduleJobLog
     */
    void save(ScheduleJobLogEntity scheduleJobLog);
}
