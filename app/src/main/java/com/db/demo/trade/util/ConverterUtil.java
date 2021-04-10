package com.db.demo.trade.util;

import com.db.demo.trade.dao.entity.TradeEntity;
import com.db.demo.trade.dto.Trade;

public final class ConverterUtil {

	private ConverterUtil() {
	}

	public static Trade convert(TradeEntity trade) {

		//@formatter:off
		return new Trade.Builder()
				.setTradeId(trade.getTradeId())
				.setVersion(trade.getVersion())
				.setCounterPartyId(trade.getCounterPartyId())
				.setBookId(trade.getBookId())
				.setMaturityDate(trade.getMaturityDate())
				.build();
		//@formatter:on
	}

}
