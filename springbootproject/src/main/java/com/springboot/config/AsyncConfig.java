package com.springboot.config;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configurable
@EnableAsync
public class AsyncConfig {
	
	@Bean
	public Executor taskExecutor() {
	ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	executor.setCorePoolSize(2);
	executor.setMaxPoolSize(2);
	executor.setQueueCapacity(10);
	executor.setThreadNamePrefix("EmployeeThread ---");
	executor.initialize();
		return executor;
		
	}

}
