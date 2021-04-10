package com.db.demo.trade.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class TradeDTO implements Serializable {

	private static final long serialVersionUID = -1453648819192215025L;

	private String tradeId;
	private Long version;
	private String counterPartyId;
	private String bookId;
	private LocalDate maturityDate;

	private TradeDTO() {

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

	public LocalDate getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(LocalDate maturityDate) {
		this.maturityDate = maturityDate;
	}

	@Override
	public String toString() {
		return "TradeDTO [tradeId=" + tradeId + ", version=" + version + ", counterPartyId=" + counterPartyId
				+ ", bookId=" + bookId + ", maturityDate=" + maturityDate + "]";
	}

	public static class Builder {

		private String tradeId;
		private Long version;
		private String counterPartyId;
		private String bookId;
		private LocalDate maturityDate;

		public Builder() {

		}

		public Builder setTradeId(String tradeId) {
			this.tradeId = tradeId;
			return this;
		}

		public Builder setVersion(Long version) {
			this.version = version;
			return this;
		}

		public Builder setCounterPartyId(String counterPartyId) {
			this.counterPartyId = counterPartyId;
			return this;
		}

		public Builder setBookId(String bookId) {
			this.bookId = bookId;
			return this;
		}

		public Builder setMaturityDate(LocalDate maturityDate) {
			this.maturityDate = maturityDate;
			return this;
		}

		public TradeDTO build() {
			TradeDTO tradeDTO = new TradeDTO();

			tradeDTO.setTradeId(tradeId);
			tradeDTO.setVersion(version);
			tradeDTO.setCounterPartyId(counterPartyId);
			tradeDTO.setBookId(bookId);
			tradeDTO.setMaturityDate(maturityDate);

			return tradeDTO;
		}
	}

}
