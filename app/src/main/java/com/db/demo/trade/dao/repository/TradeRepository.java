package com.db.demo.trade.dao.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.db.demo.trade.dao.entity.Trade;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {

	@Query(value = "SELECT MAX(version) FROM Trade where tradeId=:tradeId GROUP BY tradeId")
	Long getLatestVersion(String tradeId);

	Page<Trade> findByTradeId(String tradeId, Pageable page);

	Optional<Trade> findByTradeIdAndVersion(String tradeId, Long version);

}
