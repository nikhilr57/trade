package com.db.demo.trade.dto;

import java.io.Serializable;
import java.util.Date;

public class TradeDTO implements Serializable {

	private static final long serialVersionUID = -1453648819192215025L;
	private String tradeId;
	private Long version;
	private String counterPartyId;
	private String bookId;
	private Date maturityDate;

	public TradeDTO() {

	}

	public TradeDTO(String tradeId, Long version, String counterPartyId, String bookId, Date maturityDate) {
		this.tradeId = tradeId;
		this.version = version;
		this.counterPartyId = counterPartyId;
		this.bookId = bookId;
		this.maturityDate = maturityDate;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getCounterPartyId() {
		return counterPartyId;
	}

	public void setCounterPartyId(String counterPartyId) {
		this.counterPartyId = counterPartyId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public Date getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}

	@Override
	public String toString() {
		return "TradeDTO [tradeId=" + tradeId + ", version=" + version + ", counterPartyId=" + counterPartyId
				+ ", bookId=" + bookId + ", maturityDate=" + maturityDate + "]";
	}

}
