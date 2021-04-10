package com.db.demo.trade.api;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.db.demo.trade.dto.Trade;
import com.db.demo.trade.service.TradeService;
import com.db.demo.trade.validation.RequestValidator;

@RestController
public class TradeController implements TradeApi {

	private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(TradeController.class);

	@Autowired
	private TradeService tradeService;

	@Override
	public ResponseEntity<Void> saveTrade(@Valid Trade tradeRequest) {
		LOG.debug("Save Trade {}", tradeRequest);
		RequestValidator.validateRequest(tradeRequest);
		tradeService.saveTrade(tradeRequest);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<List<Trade>> listTradeById(String tradeId, Integer pageSize, Integer pageNumber) {
		return ResponseEntity.ok(tradeService.listTrades(tradeId, pageNumber, pageSize));
	}

	@Override
	public ResponseEntity<Trade> getTradeByIdVersion(String tradeId, Long version) {
		return ResponseEntity.ok(tradeService.getTrade(tradeId, version));
	}

	@Override
	public ResponseEntity<List<Trade>> listAllTrades(Integer pageSize, Integer pageNumber) {
		return ResponseEntity.ok(tradeService.listTrades(pageNumber, pageSize));
	}

}
