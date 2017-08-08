package io.kevin.modules.job.service.impl;

import io.kevin.modules.job.dao.ScheduleJobDao;
import io.kevin.modules.job.entity.ScheduleJobEntity;
import io.kevin.modules.job.service.ScheduleJobService;
import io.kevin.modules.job.utils.ScheduleJob;
import io.kevin.modules.job.utils.ScheduleUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZGJ
 * @date 2017/8/8 23:24
 **/
@Service("scheduleJobService")
public class ScheduleJobServiceImpl implements ScheduleJobService {
    @Autowired
    private Scheduler scheduler;
    @Autowired
    private ScheduleJobDao scheduleJobDao;

    /**
     * 项目启动时，初始化定时器
     */
    @PostConstruct
    public void init() {
        List<ScheduleJobEntity> scheduleJobEntityList = scheduleJobDao.queryList(new HashMap<>());
        for(ScheduleJobEntity scheduleJob : scheduleJobEntityList) {
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
            //TODO 未完成
        }
    }
    @Override
    public ScheduleJobEntity queryObject(Long jobId) {
        return null;
    }

    @Override
    public List<ScheduleJobEntity> queryList(Map<String, Object> map) {
        return null;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return 0;
    }

    @Override
    public void save(ScheduleJobEntity scheduleJob) {

    }

    @Override
    public void update(ScheduleJobEntity scheduleJob) {

    }

    @Override
    public void deleteBatch(Long[] jobIds) {

    }

    @Override
    public int updateBatch(Long[] jobIds, int status) {
        return 0;
    }

    @Override
    public void run(Long[] jobIds) {

    }

    @Override
    public void pause(Long[] jobIds) {

    }

    @Override
    public void resume(Long[] jobIds) {

    }
}
