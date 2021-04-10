package com.db.demo.trade.service;

import java.util.List;

import com.db.demo.trade.dto.Trade;

public interface TradeService {

	/**
	 * Saved a new trade record
	 * 
	 * @param tradeRequest
	 */
	void saveTrade(Trade tradeRequest);

	/**
	 * Update the trade record
	 * 
	 * @param tradeRequest
	 */
	void updateTrade(Trade tradeRequest, Long version);

	/**
	 * Returns all trade records
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	List<Trade> listTrades(int pageNumber, int pageSize);

	/**
	 * Returns all trade records by ID
	 * 
	 * @param tradeId
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	List<Trade> listTrades(String tradeId, int pageNumber, int pageSize);

	/**
	 * Returns latest version of a trade
	 * 
	 * @param tradeId
	 * @return
	 */
	Trade getTrade(String tradeId);

	/**
	 * Returns a trade of specified version
	 * 
	 * @param tradeId
	 * @param version
	 * @return
	 */
	Trade getTrade(String tradeId, Long version);

	/**
	 * Updates the trade expiry by maturity date
	 */
	void processMaturityDate();

}
