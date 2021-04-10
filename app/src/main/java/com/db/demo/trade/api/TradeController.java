package com.db.demo.trade.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.db.demo.trade.dto.TradeDTO;
import com.db.demo.trade.service.TradeService;

@RestController
public class TradeController implements TradeApi {

	@Autowired
	private TradeService tradeService;

	@Override
	public ResponseEntity<Void> saveTrade(@Valid TradeDTO tradeRequest) {
		
		tradeService.saveTrade(tradeRequest);
		return null;
	}

	@Override
	public ResponseEntity<List<TradeDTO>> getTradeById(Long tradeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<TradeDTO>> getTrades() {
		// TODO Auto-generated method stub
		return null;
	}

}
