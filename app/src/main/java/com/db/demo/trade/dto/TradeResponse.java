package com.db.demo.trade.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class TradeResponse implements Serializable {

	private static final long serialVersionUID = -1453648819192215025L;
	@NotBlank
	private String tradeId;
	@NotNull
	@Min(1)
	private Long version;
	private String counterPartyId;
	private String bookId;
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate maturityDate;
	private Boolean expired;
	private Date createdDate;
	private Date updatedDate;

	private TradeResponse() {

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

	public Boolean isExpired() {
		return expired;
	}

	public void setExpired(Boolean expired) {
		this.expired = expired;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public String toString() {
		return "TradeRequest [tradeId=" + tradeId + ", version=" + version + ", counterPartyId=" + counterPartyId
				+ ", bookId=" + bookId + ", maturityDate=" + maturityDate + ", expired=" + expired + "]";
	}

	public static class Builder {

		private String tradeId;
		private Long version;
		private String counterPartyId;
		private String bookId;
		private LocalDate maturityDate;
		private Boolean expired;
		private Date createdDate;
		private Date updatedDate;

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

		public Builder isExpired(Boolean expired) {
			this.expired = expired;
			return this;
		}

		public Builder setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
			return this;
		}

		public Builder setUpdatedDate(Date updatedDate) {
			this.updatedDate = updatedDate;
			return this;
		}

		public TradeResponse build() {
			TradeResponse tradeRes = new TradeResponse();

			tradeRes.setTradeId(tradeId);
			tradeRes.setVersion(version);
			tradeRes.setCounterPartyId(counterPartyId);
			tradeRes.setBookId(bookId);
			tradeRes.setMaturityDate(maturityDate);
			tradeRes.setExpired(expired);
			tradeRes.setCreatedDate(createdDate);
			tradeRes.setUpdatedDate(updatedDate);

			return tradeRes;
		}
	}

}
