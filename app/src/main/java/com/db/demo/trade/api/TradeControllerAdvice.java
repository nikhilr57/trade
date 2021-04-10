package com.db.demo.trade.api;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.ThrowableProblem;
import org.zalando.problem.spring.web.advice.ProblemHandling;
import org.zalando.problem.spring.web.advice.http.UnsupportedMediaTypeAdviceTrait;
import org.zalando.problem.spring.web.advice.io.MessageNotReadableAdviceTrait;
import org.zalando.problem.spring.web.advice.validation.ConstraintViolationAdviceTrait;

import com.db.demo.trade.exception.TradeErrorCode;
import com.db.demo.trade.exception.TradeException;

@ControllerAdvice
public class TradeControllerAdvice implements ProblemHandling, ConstraintViolationAdviceTrait,
		MessageNotReadableAdviceTrait, UnsupportedMediaTypeAdviceTrait {

	private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(TradeControllerAdvice.class);

	@Override
	public boolean isCausalChainsEnabled() {
		return false;
	}

	

	@ExceptionHandler(TradeException.class)
	public ResponseEntity<Problem> handleTradeException(final TradeException exception,
			final NativeWebRequest request) {
		LOG.error("[TradeException] {}", exception.getMessage());
		return handleException(exception.getErrorCode(), request);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Problem> handleException(final Exception exception, final NativeWebRequest request) {
		LOG.error("[TradeException] {}", exception.getMessage());
		return handleException(TradeErrorCode.EXCEPTION, request);
	}

	private ResponseEntity<Problem> handleException(final TradeErrorCode errorCode, final NativeWebRequest request) {
		String details = errorCode.getMessage();

		//@formatter:off
	    ThrowableProblem problem = Problem.builder()
	        .withType(Problem.DEFAULT_TYPE)
	        .withTitle(errorCode.name())
	        .withDetail(details)
	        .withInstance(URI.create(request.getNativeRequest(HttpServletRequest.class).getRequestURL().toString()))
	        .withStatus(errorCode.getStatus())
	        .build();
	    //@formatter:on
		return create(problem, request);
	}
}
