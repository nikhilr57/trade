package com.db.demo.trade.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.db.demo.trade.dao.entity.TradeEntity;
import com.db.demo.trade.dao.repository.TradeRepository;
import com.db.demo.trade.dto.TradeRequest;
import com.db.demo.trade.dto.TradeResponse;
import com.db.demo.trade.exception.TradeException;

@ExtendWith(MockitoExtension.class)
public class TradeServiceImplTest {

	private static final String T1 = "T1";

	@Mock
	private TradeRepository tradeRepository;

	@Mock
	private Page<TradeEntity> page;

	@InjectMocks
	private TradeServiceImpl tradeService;

	@Test
	@DisplayName("Creating trade with older version")
	public void testSaveTradeWithOlderVersion() {

		TradeRequest tradeRequest = new TradeRequest.Builder().setTradeId(T1).setVersion(9L).build();
		Mockito.when(tradeRepository.getLatestVersion(T1)).thenReturn(10L);
		Exception exception = assertThrows(TradeException.class, () -> tradeService.saveTrade(tradeRequest));
		assertEquals("Invalid version", exception.getMessage());
	}

	@Test
	@DisplayName("Create trade with valid input")
	public void testSaveTradeWithValidInput() {
		TradeRequest tradeRequest = new TradeRequest.Builder().setTradeId(T1).setVersion(9L).build();
		Mockito.when(tradeRepository.getLatestVersion(T1)).thenReturn(1L);
		tradeService.saveTrade(tradeRequest);

		Mockito.verify(tradeRepository, Mockito.times(1)).save(Mockito.any(TradeEntity.class));
		Mockito.verify(tradeRepository, Mockito.times(1)).flush();
	}

	@Test
	@DisplayName("Create trade with same last version")
	public void testSaveTradeWithExistingLatestVersion() {
		TradeRequest tradeRequest = new TradeRequest.Builder().setTradeId(T1).setVersion(5L).build();
		Mockito.when(tradeRepository.getLatestVersion(T1)).thenReturn(5L);

		TradeEntity tEntity = new TradeEntity();
		Mockito.when(tradeRepository.findByTradeIdAndVersion(Mockito.eq(T1), Mockito.eq(5L)))
				.thenReturn(Optional.of(tEntity));

		tradeService.saveTrade(tradeRequest);

		Mockito.verify(tradeRepository, Mockito.times(1)).save(Mockito.any(TradeEntity.class));
		Mockito.verify(tradeRepository, Mockito.times(1)).flush();
	}

	@Test
	@DisplayName("Update trade with valid input")
	public void testUpdateWithValidInput() {
		TradeRequest tradeRequest = new TradeRequest.Builder().setTradeId(T1).setVersion(5L).build();
		TradeEntity tEntity = new TradeEntity();
		Mockito.when(tradeRepository.findByTradeIdAndVersion(Mockito.eq(T1), Mockito.eq(5L)))
				.thenReturn(Optional.of(tEntity));

		tradeService.updateTrade(tradeRequest, 5L);

		Mockito.verify(tradeRepository, Mockito.times(1)).save(Mockito.any(TradeEntity.class));
		Mockito.verify(tradeRepository, Mockito.times(1)).flush();
	}

	@Test
	@DisplayName("List trades with valid tradeId")
	public void testListTrade() {

		Mockito.when(tradeRepository.findByTradeId(Mockito.eq(T1), Mockito.any(Pageable.class))).thenReturn(page);
		TradeEntity tradeEntity = new TradeEntity(T1, 1L, T1, T1, LocalDate.now());
		List<TradeEntity> tradeList = new ArrayList<TradeEntity>();
		tradeList.add(tradeEntity);
		Mockito.when(page.getContent()).thenReturn(tradeList);
		List<TradeResponse> response = tradeService.listTrades(T1, 0, 5);
		assertEquals(response.size(), 1);
		Mockito.verify(tradeRepository, Mockito.times(1)).findByTradeId(Mockito.eq(T1), Mockito.any(Pageable.class));

	}

}
