package io.kevin.modules.job.service;

import io.kevin.modules.job.entity.ScheduleJobEntity;

import java.util.List;
import java.util.Map;

/**
 * 定时任务service
 * @author ZGJ
 * @date 2017/8/8 23:06
 **/
public interface ScheduleJobService {

    /**
     * 根据id，查询定时任务
     * @param jobId
     * @return
     */
    ScheduleJobEntity queryObject(Long jobId);

    /**
     * 查询定时任务列表
     * @param map
     * @return
     */
    List<ScheduleJobEntity> queryList(Map<String, Object> map);

    /**
     * 查询总数
     * @param map
     * @return
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存定时任务
     * @param scheduleJob
     */
    void save(ScheduleJobEntity scheduleJob);

    /**
     * 更新定时任务
     * @param scheduleJob
     */
    void update(ScheduleJobEntity scheduleJob);

    /**
     * 批量删除定时任务
     * @param jobIds
     */
    void deleteBatch(Long[] jobIds);

    /**
     * 批量更新定时任务
     * @param jobIds
     */
    int updateBatch(Long[] jobIds, int status);

    /**
     * 立即执行
     * @param jobIds
     */
    void run(Long[] jobIds);

    /**
     * 暂停运行
     * @param jobIds
     */
    void pause(Long[] jobIds);

    /**
     * 恢复运行
     * @param jobIds
     */
    void resume(Long[] jobIds);
}
