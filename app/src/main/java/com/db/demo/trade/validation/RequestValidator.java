package com.db.demo.trade.validation;

import java.time.LocalDate;

import com.db.demo.trade.dto.Trade;
import com.db.demo.trade.exception.TradeErrorCode;
import com.db.demo.trade.exception.TradeException;

public class RequestValidator {

	public static void validateRequest(Trade req) {
		if (req.getMaturityDate().isBefore(LocalDate.now())) {
			throw new TradeException(TradeErrorCode.INVALID_MATURITY_DATE);
		}
	}
}
