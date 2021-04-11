package com.db.demo.trade.util;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.db.demo.trade.dao.entity.TradeEntity;
import com.db.demo.trade.dto.TradeResponse;

@ExtendWith(MockitoExtension.class)
public class ConverterUtilTest {

	@Test
	@DisplayName("Conversion from Entity to response")
	public void testConversion() {

		TradeEntity tE = new TradeEntity("T1", 1L, "CP", "B", null);
		TradeResponse tR = ConverterUtil.convert(tE);
		assertAll("TradeResponse", () -> assertEquals(tE.getBookId(), tR.getBookId()),
				() -> assertEquals(tE.getCounterPartyId(), tR.getCounterPartyId()),
				() -> assertEquals(tE.getTradeId(), tR.getTradeId()),
				() -> assertEquals(tE.getVersion(), tR.getVersion()));
	}
}
