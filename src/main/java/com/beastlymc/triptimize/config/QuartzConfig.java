package com.beastlymc.triptimize.config;

import com.beastlymc.triptimize.jobs.VerificationJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail verificationJobDetail() {
        return JobBuilder.newJob()
            .ofType(VerificationJob.class)
            .storeDurably()
            .withIdentity("verificationJob")
            .withDescription("Verifies accounts")
            .build();
    }

    @Bean
    public Trigger verificationTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
            .withIntervalInMinutes(1)
            .repeatForever();

        return TriggerBuilder.newTrigger()
            .forJob(verificationJobDetail())
            .withIdentity("verificationTrigger")
            .withSchedule(scheduleBuilder)
            .build();
    }
}
