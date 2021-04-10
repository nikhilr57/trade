package com.db.demo.trade.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.db.demo.trade.service.TradeService;

@Configuration
@EnableScheduling
public class TradeScheduler {

	@Autowired
	private TradeService tradeService;

	@Scheduled(cron = "0 0 0 * * ?") // Every midnight
	// @Scheduled(cron = "0 0/2 * * * ?") // Every two minutes
	public void processMaturityDate() {
		tradeService.processMaturityDate();
	}
}
