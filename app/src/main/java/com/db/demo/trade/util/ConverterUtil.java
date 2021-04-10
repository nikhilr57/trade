package com.db.demo.trade.util;

import com.db.demo.trade.dao.entity.Trade;
import com.db.demo.trade.dto.TradeDTO;

public final class ConverterUtil {

	private ConverterUtil() {
	}

	public static TradeDTO convert(Trade trade) {

		//@formatter:off
		return new TradeDTO.Builder()
				.setTradeId(trade.getTradeId())
				.setVersion(trade.getVersion())
				.setCounterPartyId(trade.getCounterPartyId())
				.setBookId(trade.getBookId())
				.setMaturityDate(trade.getMaturityDate())
				.build();
		//@formatter:on
	}

}
