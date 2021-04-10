package com.db.demo.trade.dao.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "TRADE", indexes = { @Index(name = "tradeIndex", columnList = "tradeId,version") }, uniqueConstraints = {
		@UniqueConstraint(columnNames = { "tradeId", "version" }) })
public class Trade implements Serializable {

	private static final long serialVersionUID = 7932470298137869962L;

	@Id
	@GeneratedValue
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "tradeId", updatable = false, nullable = false)
	private String tradeId;

	@Column(name = "version", updatable = false, nullable = false)
	private Long version;

	@Column(name = "counterPartyId", updatable = false, nullable = false)
	private String counterPartyId;

	@Column(name = "bookId", updatable = false, nullable = false)
	private String bookId;

	@Column(name = "maturityDate", updatable = false, nullable = false)
	private LocalDate maturityDate;

	@CreatedDate
	@Temporal(TemporalType.DATE)
	@Column(name = "createdDate", updatable = false, nullable = false)
	private Date createdDate;

	@LastModifiedDate
	@Temporal(TemporalType.DATE)
	@Column(name = "updatedDate", updatable = true, nullable = false)
	private Date updatedDate;

	@Column(name = "expired", updatable = true, nullable = false)
	private Boolean expired = Boolean.FALSE;

	public Trade() {

	}

	public Trade(String tradeId, Long version, String counterPartyId, String bookId, LocalDate maturityDate) {
		this.tradeId = tradeId;
		this.version = version;
		this.counterPartyId = counterPartyId;
		this.bookId = bookId;
		this.maturityDate = maturityDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public void setMaturityDate(LocalDate maturtiyDate) {
		this.maturityDate = maturtiyDate;
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

	public Boolean getExpired() {
		return expired;
	}

	public void setExpired(Boolean exipred) {
		this.expired = exipred;
	}

	@Override
	public String toString() {
		return "Trade [id=" + id + ", tradeId=" + tradeId + ", version=" + version + ", counterPartyId="
				+ counterPartyId + ", bookId=" + bookId + ", maturityDate=" + maturityDate + ", createdDate="
				+ createdDate + ", updatedDate=" + updatedDate + ", expired=" + expired + "]";
	}

}
