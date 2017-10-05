package io.kevin.modules.job.utils;

import com.google.gson.Gson;
import io.kevin.common.utils.SpringContextUtils;
import io.kevin.modules.job.entity.ScheduleJobEntity;
import io.kevin.modules.job.entity.ScheduleJobLogEntity;
import io.kevin.modules.job.service.ScheduleJobLogService;
import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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

        //获取scheduleJobLogService
        ScheduleJobLogService scheduleJobLogService = (ScheduleJobLogService) SpringContextUtils.getBean("scheduleJobLogService");

        //数据库保存执行记录
        ScheduleJobLogEntity scheduleJobLog = new ScheduleJobLogEntity();
        scheduleJobLog.setJobId(scheduleJob.getJobId());
        scheduleJobLog.setBeanName(scheduleJob.getBeanName());
        scheduleJobLog.setMethodName(scheduleJob.getMethodName());
        scheduleJobLog.setParams(scheduleJob.getParams());
        scheduleJobLog.setCreateTime(new Date());

        //任务开始时间
        long startTime = System.currentTimeMillis();

        try {
            //执行任务
            logger.info("任务准备执行，任务ID: " + scheduleJob.getJobId());
            ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBeanName(), scheduleJob.getMethodName(), scheduleJob.getParams());
            Future<?> future = service.submit(task);

            future.get();

            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            scheduleJobLog.setTimes((int)times);
            //任务状态 0： 成功 1：失败
            scheduleJobLog.setStatus(0);

            logger.info("任务执行完毕，任务ID: " + scheduleJob.getJobId() + " 总共耗时： " + times + "毫秒");
        } catch (Exception e) {
            logger.error("任务执行失败，任务ID: " + scheduleJob.getJobId());

            //任务执行总时长
            long times = System.currentTimeMillis() - startTime;
            scheduleJobLog.setTimes((int) times);

            //任务状态 0:正常 1：失败
            scheduleJobLog.setStatus(1);
            scheduleJobLog.setError(StringUtils.substring(e.toString(), 0, 2000));
        } finally {
            scheduleJobLogService.save(scheduleJobLog);
        }

    }
}
