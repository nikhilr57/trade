package com.db.demo.trade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.db.demo.trade.entity.Trade;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {

	@Query(value = "SELECT MAX(version) FROM Trade where tradeId=:tradeId GROUP BY tradeId")
	Long getMaxVersionTrade(String tradeId);

}
