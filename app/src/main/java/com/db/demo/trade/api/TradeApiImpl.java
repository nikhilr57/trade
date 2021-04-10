package com.db.demo.trade.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.db.demo.trade.dto.TradeDTO;

@RestController
public class TradeApiImpl implements TradeApi {

	@Override
	public ResponseEntity<Void> createTrade(@Valid TradeDTO body) {
		// TODO Auto-generated method stub
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
