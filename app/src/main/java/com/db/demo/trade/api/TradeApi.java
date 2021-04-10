package com.db.demo.trade.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.db.demo.trade.dto.TradeDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Validated
public interface TradeApi {

	@Operation(summary = "Add a trade", tags = { "trade" }, description = "", responses = {
			@ApiResponse(description = "The trade", content = @Content(schema = @Schema(implementation = TradeDTO.class))),
			@ApiResponse(responseCode = "404", description = "Pet not found") })

	@PostMapping(value = "/trades", consumes = { "application/json" })
	ResponseEntity<Void> createTrade(@Valid @RequestBody TradeDTO tradeDTO);

	@Operation(summary = "Get trade", tags = { "trade" }, description = "Get trade by ID", responses = {
			@ApiResponse(responseCode = "200", description = "List of trades", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TradeDTO.class))) })
	@GetMapping(value = "/trades/{tradeId}", produces = { "application/json" })
	ResponseEntity<List<TradeDTO>> getTradeById(@PathVariable("tradeId") Long tradeId);

	/**
	 * GET /trade : List trades Returns a list of trades
	 *
	 * @return successful operation (status code 200)
	 */
	@Operation(summary = "List trades", tags = { "trade", })
	@GetMapping(value = "/trades", produces = { "application/json" })
	ResponseEntity<List<TradeDTO>> getTrades();

}