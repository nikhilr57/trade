package com.db.demo.trade.validation;

import java.time.LocalDate;

import com.db.demo.trade.dto.TradeDTO;
import com.db.demo.trade.exception.TradeErrorCode;
import com.db.demo.trade.exception.TradeException;

public class TradeRequest {

	private static void validateRequest(TradeDTO req) {
		if (req.getMaturityDate().isBefore(LocalDate.now())) {
			throw new TradeException(TradeErrorCode.INVALID_MATURITY_DATE);
		}
	}
}
