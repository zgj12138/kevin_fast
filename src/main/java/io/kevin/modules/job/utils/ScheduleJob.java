package io.kevin.modules.job.utils;

import com.google.gson.Gson;
import io.kevin.common.utils.SpringContextUtils;
import io.kevin.modules.job.entity.ScheduleJobEntity;
import io.kevin.modules.job.entity.ScheduleJobLogEntity;
import io.kevin.modules.job.service.ScheduleJobService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 定时任务
 * @author ZGJ
 * @date 2017/8/8 23:42
 **/
public class ScheduleJob extends QuartzJobBean {
    private static Logger logger = LoggerFactory.getLogger(ScheduleJob.class);
    private ExecutorService service = Executors.newSingleThreadExecutor();
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String jsonJob = jobExecutionContext.getMergedJobDataMap().getString(ScheduleJobEntity.JOB_PARAM_KEY);
        ScheduleJobEntity scheduleJob = new Gson().fromJson(jsonJob, ScheduleJobEntity.class);

        //获取spring bean
        ScheduleJobService scheduleJobService = (ScheduleJobService) SpringContextUtils.getBean("scheduleJobLogService");

        //数据库保存执行记录
        ScheduleJobLogEntity scheduleJobLog = new ScheduleJobLogEntity();
        scheduleJobLog.setJobId(scheduleJob.getJobId());
        scheduleJobLog.setBeanName(scheduleJob.getBeanName());
        scheduleJobLog.setMethodName(scheduleJob.getMethodName());
        scheduleJobLog.setParams(scheduleJob.getParams());
        scheduleJobLog.setCreateTime(new Date());
        //TODO 未完成


    }
}
