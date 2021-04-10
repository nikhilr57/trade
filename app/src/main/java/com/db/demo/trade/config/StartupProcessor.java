package com.db.demo.trade.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.db.demo.trade.service.TradeService;

@Component
public class StartupProcessor {

	@Autowired
	private TradeService tradeService;

	/**
	 * Handle processing of maturity dates at startup
	 */
	@EventListener(ApplicationReadyEvent.class)
	public void init() {
		tradeService.processMaturityDate();
	}

}
