package com.db.demo.trade.exception;

import org.springframework.http.HttpStatus;
import org.zalando.problem.Status;

public enum TradeErrorCode {

	//@formatter:off
	EXCEPTION("Internal Server Error"),
	VALIDATION_EXCEPTION(Status.BAD_REQUEST, "Invalid request"),
	INVALID_VERSION(Status.BAD_REQUEST, "Invalid version"),
	INVALID_MATURITY_DATE(Status.BAD_REQUEST, "Invalid maturity date"),
	TRADE_NOT_FOUND(Status.NOT_FOUND, "TradeEntity Not Found"),
	;
	//@formatter:on

	private final Status status;
	private final String message;

	private TradeErrorCode(String message) {
		this.status = Status.INTERNAL_SERVER_ERROR;
		this.message = message;
	}

	private TradeErrorCode(Status status, String message) {
		this.status = status;
		this.message = message;
	}

	public Status getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

}
