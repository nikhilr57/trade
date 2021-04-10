package com.db.demo.trade.exception;

public class TradeException extends RuntimeException {

	private static final long serialVersionUID = -4303455414482011715L;

	private final TradeErrorCode errorCode;

	public TradeException() {
		this.errorCode = TradeErrorCode.EXCEPTION;
		this.fillInStackTrace();
	}

	public TradeException(TradeErrorCode errorCode) {
		this.errorCode = errorCode;
		this.fillInStackTrace();
	}

	public TradeException(TradeErrorCode errorCode, Throwable e) {
		super(e);
		this.errorCode = errorCode;
		this.fillInStackTrace();
	}

	public TradeErrorCode getErrorCode() {
		return errorCode;
	}

	@Override
	public String getMessage() {
		return this.errorCode.getMessage();
	}

}
