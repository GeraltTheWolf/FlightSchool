package hr.rezek.flightschool.jobs;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {

    @Bean
    public JobDetail blogDeleteJobDetail() {
        return JobBuilder.newJob(BlogPostJob.class).withIdentity("blogDeletionJob")
                .storeDurably().build();
    }

    @Bean
    public SimpleTrigger blogDeleteTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5).repeatForever();
                //.withIntervalInHours(5).repeatForever();


        return TriggerBuilder.newTrigger().forJob(blogDeleteJobDetail())
                .withIdentity("blogDeleteTrigger").withSchedule(scheduleBuilder).build();
    }

}