package com.db.demo.trade.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.db.demo.trade.dto.Trade;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Validated
@Tag(name = "trade")
public interface TradeApi {

	String TAG_TRADE = "trade";

	@Operation(summary = "Add a trade", tags = { TAG_TRADE }, description = "", responses = {
			@ApiResponse(description = "The trade", content = @Content(schema = @Schema(implementation = Trade.class))),
			@ApiResponse(responseCode = "400", description = "Bad request") })
	@PostMapping(value = "/trades", consumes = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<Void> saveTrade(@Valid @RequestBody Trade tradeRequest);

	@Operation(summary = "List trades by ID", tags = {
			TAG_TRADE }, description = "List trades by trade ID", responses = {
					@ApiResponse(responseCode = "200", description = "List of trades", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Trade.class))) })
	@GetMapping(value = "/trades/{tradeId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<List<Trade>> listTradeById(@PathVariable("tradeId") String tradeId,
			@RequestParam("pageSize") Integer pageSize, @RequestParam("pageNumber") Integer pageNumber);

	@Operation(summary = "Get a trade", tags = { TAG_TRADE }, description = "Get trade by ID and version", responses = {
			@ApiResponse(responseCode = "200", description = "A trade object", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Trade.class))) })
	@GetMapping(value = "/trades/{tradeId}/{version}", produces = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<Trade> getTradeByIdVersion(@PathVariable("tradeId") String tradeId,
			@PathVariable("version") Long version);

	@Operation(summary = "List all trades", tags = { TAG_TRADE })
	@GetMapping(value = "/trades", produces = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<List<Trade>> listAllTrades(@RequestParam("pageSize") Integer pageSize,
			@RequestParam("pageNumber") Integer pageNumber);

}