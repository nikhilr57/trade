package com.db.demo.trade.dao.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.db.demo.trade.dao.entity.TradeEntity;

@Repository
public interface TradeRepository extends JpaRepository<TradeEntity, Long> {

	Optional<TradeEntity> findByTradeId(String tradeId);

	@Query(value = "SELECT MAX(version) FROM TradeEntity where tradeId=:tradeId GROUP BY tradeId")
	Long getLatestVersion(String tradeId);

	Page<TradeEntity> findByTradeId(String tradeId, Pageable page);

	Optional<TradeEntity> findByTradeIdAndVersion(String tradeId, Long version);

	@Modifying(clearAutomatically = true)
	@Query("UPDATE TradeEntity SET expired= :expired where maturityDate < :maturityDate")
	void processMaturityDate(@Param("expired") Boolean expired, @Param("maturityDate") LocalDate maturityDate);

}
