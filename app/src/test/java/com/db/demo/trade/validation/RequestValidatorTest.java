package com.db.demo.trade.validation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.db.demo.trade.dto.TradeRequest;
import com.db.demo.trade.exception.TradeException;

@ExtendWith(MockitoExtension.class)
public class RequestValidatorTest {

	@Test
	@DisplayName("Trade Request validation Invalid Maturty Date")
	public void testTradeRequestValidation() {
		TradeRequest tR = new TradeRequest.Builder().setTradeId("T1").setCounterPartyId("C1").setBookId("B1")
				.setMaturityDate(LocalDate.of(2020, 01, 1)).build();

		Exception exception = assertThrows(TradeException.class, () -> RequestValidator.validateRequest(tR));
		assertEquals("Invalid maturity date", exception.getMessage());
	}

	@Test
	@DisplayName("Trade Request validation valid Maturty Date")
	public void testTradeRequestValidationValidInput() {

		TradeRequest tR = new TradeRequest.Builder().setTradeId("T1").setCounterPartyId("C1").setBookId("B1")
				.setMaturityDate(LocalDate.now().plusDays(1)).build();
		assertDoesNotThrow(() -> RequestValidator.validateRequest(tR));

	}
}
