package com.db.demo.trade;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.db.demo.trade.dto.TradeDTO;
import com.db.demo.trade.service.TradeService;

@SpringBootApplication
@EnableJpaAuditing

public class TradeApplication {

	@Autowired
	private TradeService tradeService;

	public static void main(String[] args) {
		SpringApplication.run(TradeApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void init() {

		TradeDTO tr1 = new TradeDTO("T1", 1L, "TEST", "TEST", new Date());
		TradeDTO tr2 = new TradeDTO("T2", 2L, "TEST", "TEST", new Date());
		TradeDTO tr3 = new TradeDTO("T1", 2L, "TEST", "TEST", new Date());
		TradeDTO tr4 = new TradeDTO("T1", 1L, "TEST", "TEST", new Date());
		TradeDTO tr5 = new TradeDTO("T2", 1L, "TEST", "TEST", new Date());

		tradeService.createTrade(tr1);
		tradeService.createTrade(tr2);
		tradeService.createTrade(tr3);
		tradeService.createTrade(tr4);
		tradeService.createTrade(tr5);

	}

}
