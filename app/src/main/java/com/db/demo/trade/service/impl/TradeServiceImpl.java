package com.db.demo.trade.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.db.demo.trade.dao.entity.Trade;
import com.db.demo.trade.dao.repository.TradeRepository;
import com.db.demo.trade.dto.TradeDTO;
import com.db.demo.trade.exception.TradeErrorCode;
import com.db.demo.trade.exception.TradeException;
import com.db.demo.trade.service.TradeService;
import com.db.demo.trade.util.ConverterUtil;

@Service
public class TradeServiceImpl implements TradeService {

	@Autowired
	private TradeRepository tradeRepository;

	@Override
	@Transactional
	public void saveTrade(TradeDTO tradeRequest) {

		String tradeId = tradeRequest.getTradeId();
		Long version = tradeRequest.getVersion();

		// Validate version
		Long lastVersion = tradeRepository.getLatestVersion(tradeId);
		if (lastVersion != null && lastVersion > version) {
			throw new TradeException(TradeErrorCode.INVALID_VERSION);
		}

		// Save entity
		Trade trade = new Trade(tradeId, version, tradeRequest.getCounterPartyId(), tradeRequest.getBookId(),
				tradeRequest.getMaturityDate());
		trade = tradeRepository.save(trade);
		// TODO debug log with PK printed
		tradeRepository.flush();
	}

	@Override
	public List<TradeDTO> listTrades(int pageNumber, int pageSize) {

		Pageable page = PageRequest.of(pageNumber, pageSize);
		Page<Trade> result = tradeRepository.findAll(page);
		List<TradeDTO> trades = new ArrayList<>(result.getContent().size());

		// Convert to DTO and return
		result.getContent().forEach(trade -> trades.add(ConverterUtil.convert(trade)));
		return trades;
	}

	@Override
	public List<TradeDTO> listTrades(String tradeId, int pageNumber, int pageSize) {

		Pageable page = PageRequest.of(pageNumber, pageSize);
		Page<Trade> result = tradeRepository.findByTradeId(tradeId, page);
		List<TradeDTO> trades = new ArrayList<>(result.getContent().size());

		// Convert to DTO and return
		result.getContent().forEach(trade -> trades.add(ConverterUtil.convert(trade)));
		return trades;
	}

	@Override
	public TradeDTO getTrade(String tradeId) {

		// Fetch latest version
		Long reqVersion = tradeRepository.getLatestVersion(tradeId);
		if (reqVersion == null) {
			throw new TradeException(TradeErrorCode.INVALID_VERSION);
		}

		return getTrade(tradeId, reqVersion);
	}

	@Override
	public TradeDTO getTrade(String tradeId, Long version) {

		if (version == null) { // Null input
			throw new TradeException(TradeErrorCode.INVALID_VERSION);
		}
		// Find in database
		Optional<Trade> result = tradeRepository.findByTradeIdAndVersion(tradeId, version);
		if (result.isEmpty()) {
			throw new TradeException(TradeErrorCode.TRADE_NOT_FOUND);
		}
		Trade trade = result.get();
		return ConverterUtil.convert(trade);
	}

}
