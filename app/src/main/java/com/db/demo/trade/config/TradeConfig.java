package com.db.demo.trade.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class TradeConfig {

	@Scheduled(cron = "0 0 0 * * *")
	public void scheduleFixedDelayTask() {

	}
}
