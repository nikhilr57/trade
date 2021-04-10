package com.db.demo.trade.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.db.demo.trade.dao.entity.TradeEntity;
import com.db.demo.trade.dao.repository.TradeRepository;
import com.db.demo.trade.dto.TradeRequest;
import com.db.demo.trade.dto.TradeResponse;
import com.db.demo.trade.exception.TradeErrorCode;
import com.db.demo.trade.exception.TradeException;
import com.db.demo.trade.service.TradeService;
import com.db.demo.trade.util.ConverterUtil;

@Service
public class TradeServiceImpl implements TradeService {

	private static final Logger LOG = LoggerFactory.getLogger(TradeServiceImpl.class);

	@Autowired
	private TradeRepository tradeRepository;

	@Override
	@Transactional
	public void saveTrade(TradeRequest tradeRequest) {

		String tradeId = tradeRequest.getTradeId();
		Long version = tradeRequest.getVersion();

		// Validate version
		Long lastVersion = tradeRepository.getLatestVersion(tradeId);
		if (lastVersion != null && lastVersion > version) {
			LOG.error("TradeRequest version is less that available version");
			throw new TradeException(TradeErrorCode.INVALID_VERSION);
		}
		if (lastVersion == version) {
			LOG.error("TradeRequest version equal to available");
			updateTrade(tradeRequest, lastVersion);
		} else {
			// Save entity
			TradeEntity trade = new TradeEntity(tradeId, version, tradeRequest.getCounterPartyId(),
					tradeRequest.getBookId(), tradeRequest.getMaturityDate());
			trade = tradeRepository.save(trade);
			tradeRepository.flush();
		}
	}

	@Override
	@Transactional
	public void updateTrade(TradeRequest tradeRequest, Long version) {
		Optional<TradeEntity> result = tradeRepository.findByTradeIdAndVersion(tradeRequest.getTradeId(), version);
		if (result.isEmpty()) {
			LOG.error("Record not found while updating the trade with id {} and version {}", tradeRequest.getTradeId(),
					tradeRequest.getVersion());
			throw new TradeException(TradeErrorCode.TRADE_NOT_FOUND);
		}
		TradeEntity trade = result.get();
		trade.setCounterPartyId(trade.getCounterPartyId());
		trade.setBookId(trade.getBookId());
		trade.setMaturityDate(trade.getMaturityDate());

		trade = tradeRepository.save(trade);
		tradeRepository.flush();
	}

	@Override
	public List<TradeResponse> listTrades(int pageNumber, int pageSize) {

		Pageable page = PageRequest.of(pageNumber, pageSize);
		Page<TradeEntity> result = tradeRepository.findAll(page);
		List<TradeResponse> trades = new ArrayList<>(result.getContent().size());

		// Convert to DTO and return
		result.getContent().forEach(trade -> trades.add(ConverterUtil.convert(trade)));
		return trades;
	}

	@Override
	public List<TradeResponse> listTrades(String tradeId, int pageNumber, int pageSize) {

		Pageable page = PageRequest.of(pageNumber, pageSize);
		Page<TradeEntity> result = tradeRepository.findByTradeId(tradeId, page);
		List<TradeResponse> trades = new ArrayList<>(result.getContent().size());

		// Convert to DTO and return
		result.getContent().forEach(trade -> trades.add(ConverterUtil.convert(trade)));
		return trades;
	}

	@Override
	public TradeResponse getTrade(String tradeId) {

		// Fetch latest version
		Long reqVersion = tradeRepository.getLatestVersion(tradeId);
		if (reqVersion == null) {
			throw new TradeException(TradeErrorCode.INVALID_VERSION);
		}

		return getTrade(tradeId, reqVersion);
	}

	@Override
	public TradeResponse getTrade(String tradeId, Long version) {

		if (version == null) { // Null input
			throw new TradeException(TradeErrorCode.INVALID_VERSION);
		}
		// Find in database
		Optional<TradeEntity> result = tradeRepository.findByTradeIdAndVersion(tradeId, version);
		if (result.isEmpty()) {
			throw new TradeException(TradeErrorCode.TRADE_NOT_FOUND);
		}
		TradeEntity trade = result.get();
		return ConverterUtil.convert(trade);
	}

	@Override
	@Transactional
	public void processMaturityDate() {
		LOG.warn("Starting Scheduler {} ", LocalDate.now());
		tradeRepository.processMaturityDate(true, LocalDate.now());

	}

}
