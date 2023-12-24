package com.config;

import com.job.EmployeeFileReader;
import com.job.EmployeeWriter;
import com.model.Employee;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public BatchConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Step step1(EmployeeFileReader reader, EmployeeWriter writer) {
        return stepBuilderFactory.get("step1")
                .<Employee, Employee>chunk(10)
                .reader(reader)
                .writer(writer)
                .taskExecutor(jobTaskExecutor())
                .build();
    }

    @Bean
    public TaskExecutor jobTaskExecutor(){

        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor("JOB_TASKEXECUTOR");
        asyncTaskExecutor.setConcurrencyLimit(5);
        return asyncTaskExecutor;

    }

    @Bean
    @StepScope
    public EmployeeFileReader getEmployeeFileReader(){

        return new EmployeeFileReader("input.txt","src//main//resources//","jobXYZ");
    }

    @Bean
    public Job importUserJob(Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .start(step1)
                .build();
    }
}
