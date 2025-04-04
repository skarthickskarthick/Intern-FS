package com.example.Quartz;

import org.quartz.*;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

@Service
class QuartzJobService {
    private final Scheduler scheduler;

    public QuartzJobService(SchedulerFactoryBean schedulerFactoryBean) throws SchedulerException {
        this.scheduler = schedulerFactoryBean.getScheduler();
        scheduler.start();
    }

    public String scheduleJob(String jobName, int interval) throws SchedulerException {
        JobDetail job = JobBuilder.newJob(SampleJob.class)
                .withIdentity(jobName, "group1")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(jobName + "Trigger", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(interval)
                        .repeatForever())
                .build();

        scheduler.scheduleJob(job, trigger);
        return "Job Scheduled Successfully!";
    }

    public String triggerJob(String jobName) throws SchedulerException {
        scheduler.triggerJob(JobKey.jobKey(jobName, "group1"));
        return "Job Triggered!";
    }

    public String pauseJob(String jobName) throws SchedulerException {
        scheduler.pauseJob(JobKey.jobKey(jobName, "group1"));
        return "Job Paused!";
    }

    public String resumeJob(String jobName) throws SchedulerException {
        scheduler.resumeJob(JobKey.jobKey(jobName, "group1"));
        return "Job Resumed!";
    }

    public String deleteJob(String jobName) throws SchedulerException {
        scheduler.deleteJob(JobKey.jobKey(jobName, "group1"));
        return "Job Deleted!";
    }
}