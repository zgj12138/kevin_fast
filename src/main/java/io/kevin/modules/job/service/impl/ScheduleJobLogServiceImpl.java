package io.kevin.modules.job.service.impl;

import io.kevin.modules.job.dao.ScheduleJobLogDao;
import io.kevin.modules.job.entity.ScheduleJobLogEntity;
import io.kevin.modules.job.service.ScheduleJobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 任务日志service
 * @author ZGJ
 * @date 2017/8/17 0:00
 **/
@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl implements ScheduleJobLogService {

    @Autowired
    private ScheduleJobLogDao scheduleJobLogDao;

    @Override
    public ScheduleJobLogEntity queryObject(Long jobId) {
        return scheduleJobLogDao.queryObject(jobId);
    }

    @Override
    public List<ScheduleJobLogEntity> queryList(Map<String, Object> map) {
        return scheduleJobLogDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return scheduleJobLogDao.queryTotal(map);
    }

    @Override
    public void save(ScheduleJobLogEntity scheduleJobLog) {
        scheduleJobLogDao.save(scheduleJobLog);
    }
}
