package com.db.demo.trade.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.db.demo.trade.dto.TradeDTO;
import com.db.demo.trade.entity.Trade;
import com.db.demo.trade.repository.TradeRepository;
import com.db.demo.trade.service.TradeService;

@Service
@Transactional
public class TradeServiceImpl implements TradeService {

	@Autowired
	private TradeRepository tradeRepository;

	@Override
	@Transactional
	public void createTrade(TradeDTO tradeRequest) {

		String tradeId = tradeRequest.getTradeId();
		Long version = tradeRequest.getVersion();

		Long lastVersion = tradeRepository.getMaxVersionTrade("T1");
		if (lastVersion != null && lastVersion > version) {
			return;
		}
		// TODO Auto-generated method stub

		Trade trade = new Trade(tradeId, version, tradeRequest.getCounterPartyId(), tradeRequest.getBookId(),
				tradeRequest.getMaturityDate());
		tradeRepository.save(trade);
	}

	@Override
	public List<TradeDTO> listTrade(int pageNumber, int pageSize) {

		Pageable page = PageRequest.of(pageNumber, pageSize);
		Page<Trade> result = tradeRepository.findAll(page);
		List<TradeDTO> trades = new ArrayList<>(result.getContent().size());
		result.getContent().forEach(trade -> trades.add(new TradeDTO(trade.getTradeId(), trade.getVersion(),
				trade.getCounterPartyId(), trade.getBookId(), trade.getMaturityDate())));

		return trades;
	}

}
