package com.auth.server.infra.exception;

import static org.springframework.http.HttpStatus.FORBIDDEN;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.auth.server.web.dto.response.StatusResponse;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(InvalidTokenException.class)
	protected ResponseEntity<Object> handleInvalidTokenException(InvalidTokenException ex) {
		StatusResponse apiStatus = new StatusResponse(FORBIDDEN.value());
		apiStatus.setMessage(FORBIDDEN.name());
		apiStatus.setDebugMessage(ex.getMessage());
		return buildResponseEntity(apiStatus);
	}
	
	private ResponseEntity<Object> buildResponseEntity(StatusResponse apiStatus) {
		return new ResponseEntity<>(apiStatus, HttpStatus.valueOf(apiStatus.getStatus()));
	}
}
