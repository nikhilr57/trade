package com.db.demo.trade.util;

import com.db.demo.trade.dao.entity.TradeEntity;
import com.db.demo.trade.dto.TradeResponse;

public final class ConverterUtil {

	private ConverterUtil() {
	}

	public static TradeResponse convert(TradeEntity trade) {

		//@formatter:off
		return new TradeResponse.Builder()
				.setTradeId(trade.getTradeId())
				.setVersion(trade.getVersion())
				.setCounterPartyId(trade.getCounterPartyId())
				.setBookId(trade.getBookId())
				.setMaturityDate(trade.getMaturityDate())
				.isExpired(trade.getExpired())
				.setCreatedDate(trade.getCreatedDate())
				.setUpdatedDate(trade.getUpdatedDate())
				.build();
		//@formatter:on
	}

}
