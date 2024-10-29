package br.com.senac.api.scheduler.teste;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class TesteTriggerBuild {

    @Bean
    public JobDetail jobDetail(){
        return JobBuilder.newJob(TesteJob.class)
                .withIdentity("testeJob", "grupo1")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger trigger(){
        return TriggerBuilder.newTrigger()
                .withIdentity("testeTrigger", "grupo1")
                .forJob(this.jobDetail())
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 2 ? * * *"))
                .build();
    }
}
