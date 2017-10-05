package io.kevin.modules.job.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 定时任务配置
 * @author ZGJ
 * @date 2017/8/10 23:35
 **/
@Configuration
public class ScheduleConfig {

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource) {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setDataSource(dataSource);

        //quartz参数
        Properties properties = new Properties();
        properties.put("org.quartz.scheduler.instanceName", "KevinScheduler");
        properties.put("org.quartz.scheduler.instanceId", "AUTO");

        //线程池配置
        properties.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        properties.put("org.quartz.threadPool.threadCount", "20");
        properties.put("org.quartz.threadPool.threadPriority", "5");

        //JobStore配置
        properties.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");

        //集群配置
      properties.put("org.quartz.jobStore.isClustered", "true");
      properties.put("org.quartz.jobStore.clusterCheckinInterval", "15000");
      properties.put("org.quartz.jobStore.maxMisfiresToHandleAtATime", "1");

        properties.put("org.quartz.jobStore.misfireThreshold", "12000");
        properties.put("org.quartz.jobStore.tablePrefix", "QRTZ_");
        factory.setQuartzProperties(properties);

        factory.setSchedulerName("KevinScheduler");
        //延时启动
        factory.setStartupDelay(30);
        factory.setApplicationContextSchedulerContextKey("applicationContextKey");
        //可选，QuartzScheduler 启动时更新已存在的Job,这样就不用每次修改targetObject后删除qrtz_job_details表对应记录了
        factory.setOverwriteExistingJobs(true);
        //设置自动启动，默认为true
        factory.setAutoStartup(true);

        return factory;
    }
}
