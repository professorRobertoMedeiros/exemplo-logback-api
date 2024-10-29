package br.com.senac.api.scheduler.produtos;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class ProdutosTriggerBuild {

    @Bean
    public JobDetail jobDetailProdutosListar() {
        return JobBuilder.newJob(ProdutosListarJob.class)
                .withIdentity("produtosListar", "grupo1")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger triggerProdutosListar() {
        return TriggerBuilder.newTrigger()
                .withIdentity("produtosListar", "grupo1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 */5 * ? * * *"))
                .forJob(this.jobDetailProdutosListar())
                .build();
    }
}
