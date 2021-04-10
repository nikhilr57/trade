package com.db.demo.trade.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.db.demo.trade.dto.TradeRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class TradeApplicationIntegrationTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldReturnDefaultMessage() throws Exception {

		// this.mockMvc.perform(post("/trades")).andDo(print()).andExpect(status().isOk())
		// .andExpect(content().string(containsString("Hello World!")));

		ObjectMapper objectMapper = new ObjectMapper();

		TradeRequest tradeReq = new TradeRequest.Builder().setTradeId("T1").setBookId("B1").setCounterPartyId("CP1").setVersion(1L)
				.setMaturityDate(LocalDate.now()).build();

		this.mockMvc.perform(post("/trades").content(objectMapper.writeValueAsString(tradeReq))
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}
