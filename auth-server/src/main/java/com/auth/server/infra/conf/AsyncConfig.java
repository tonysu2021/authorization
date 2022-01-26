package com.auth.server.infra.conf;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

	@Bean("CustomAsyncExecutor")
	public Executor customThreadPoolTaskExecutor() {
		return createExecutor(5, 5, 0, "Async_Thread_");
	}

	private ThreadPoolTaskExecutor createExecutor(int pool, int maxPool, int queue, String name) {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		// 線程池建立時候初始化的線程數
		executor.setCorePoolSize(pool);
		// 線程池最大的線程數，只有在緩衝隊列滿了之後才會申請超過核心線程數的線程
		executor.setMaxPoolSize(maxPool);
		// 用來緩衝執行任務的隊列
		executor.setQueueCapacity(queue);
		// 當超過了核心線程出之外的線程在空閒時間到達之後會被銷毀
		executor.setKeepAliveSeconds(60);
		// 線程池對拒絕任務的處理策略
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		executor.setWaitForTasksToCompleteOnShutdown(true);
		executor.setAwaitTerminationSeconds(5);
		executor.setThreadNamePrefix(name);
		executor.initialize();
		return executor;
	}
}
