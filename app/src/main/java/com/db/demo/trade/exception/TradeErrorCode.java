package com.db.demo.trade.exception;

import org.springframework.http.HttpStatus;

public enum TradeErrorCode {

	//@formatter:off
	EXCEPTION("Internal Server Error"),
	INVALID_VERSION(HttpStatus.BAD_REQUEST, "Invalid version"),
	INVALID_MATURITY_DATE(HttpStatus.BAD_REQUEST, "Invalid maturity date"),
	TRADE_NOT_FOUND(HttpStatus.NOT_FOUND, "Trade Not Found"),
	;
	//@formatter:on

	private final HttpStatus status;
	private final String message;

	private TradeErrorCode(String message) {
		this.status = HttpStatus.INTERNAL_SERVER_ERROR;
		this.message = message;
	}

	private TradeErrorCode(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

}
