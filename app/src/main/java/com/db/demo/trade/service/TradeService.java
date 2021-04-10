package com.db.demo.trade.service;

import java.util.List;

import com.db.demo.trade.dto.TradeDTO;

public interface TradeService {

	/**
	 * Saved a new trade record
	 * 
	 * @param tradeRequest
	 */
	void saveTrade(TradeDTO tradeRequest); // FIXME

	/**
	 * Returns all trade records
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	List<TradeDTO> listTrades(int pageNumber, int pageSize);

	/**
	 * Returns all trade records by ID
	 * 
	 * @param tradeId
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	List<TradeDTO> listTrades(String tradeId, int pageNumber, int pageSize);

	/**
	 * Returns latest version of a trade
	 * 
	 * @param tradeId
	 * @return
	 */
	TradeDTO getTrade(String tradeId);

	/**
	 * Returns a trade of specified version
	 * 
	 * @param tradeId
	 * @param version
	 * @return
	 */
	TradeDTO getTrade(String tradeId, Long version);

}
