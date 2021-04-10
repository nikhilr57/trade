package com.db.demo.trade.service;

import java.util.List;

import com.db.demo.trade.dto.TradeDTO;

public interface TradeService {

	/**
	 * Creates a Trade entity in the database
	 * 
	 * @param tradeRequest
	 */
	void createTrade(TradeDTO tradeRequest);

	/**
	 * Returns all records for trade
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	List<TradeDTO> listTrade(int pageNumber, int pageSize);

}
