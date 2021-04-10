package com.db.demo.trade.service;

import java.util.List;

import com.db.demo.trade.dto.TradeRequest;
import com.db.demo.trade.dto.TradeResponse;

public interface TradeService {

	/**
	 * Saved a new trade record
	 * 
	 * @param tradeRequest
	 */
	void saveTrade(TradeRequest tradeRequest);

	/**
	 * Update the trade record
	 * 
	 * @param tradeRequest
	 */
	void updateTrade(TradeRequest tradeRequest, Long version);

	/**
	 * Returns all trade records
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	List<TradeResponse> listTrades(int pageNumber, int pageSize);

	/**
	 * Returns all trade records by ID
	 * 
	 * @param tradeId
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	List<TradeResponse> listTrades(String tradeId, int pageNumber, int pageSize);

	/**
	 * Returns latest version of a trade
	 * 
	 * @param tradeId
	 * @return
	 */
	TradeResponse getTrade(String tradeId);

	/**
	 * Returns a trade of specified version
	 * 
	 * @param tradeId
	 * @param version
	 * @return
	 */
	TradeResponse getTrade(String tradeId, Long version);

	/**
	 * Updates the trade expiry by maturity date
	 */
	void processMaturityDate();

}
