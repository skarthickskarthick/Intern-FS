package com.example.Quartz;


import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/jobs")
    class QuartzJobController {
        private final QuartzJobService quartzJobService;

        public QuartzJobController(QuartzJobService quartzJobService) {
            this.quartzJobService = quartzJobService;
        }

        @PostMapping("/schedule/{jobName}/{interval}")
        public String scheduleJob(@PathVariable String jobName, @PathVariable int interval) throws SchedulerException {
            return quartzJobService.scheduleJob(jobName, interval);
        }

        @PostMapping("/trigger/{jobName}")
        public String triggerJob(@PathVariable String jobName) throws SchedulerException {
            return quartzJobService.triggerJob(jobName);
        }

        @PostMapping("/pause/{jobName}")
        public String pauseJob(@PathVariable String jobName) throws SchedulerException {
            return quartzJobService.pauseJob(jobName);
        }

        @PostMapping("/resume/{jobName}")
        public String resumeJob(@PathVariable String jobName) throws SchedulerException {
            return quartzJobService.resumeJob(jobName);
        }

        @DeleteMapping("/delete/{jobName}")
        public String deleteJob(@PathVariable String jobName) throws SchedulerException {
            return quartzJobService.deleteJob(jobName);
        }
    }


