package com.sky.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.sky.response.ErrorResponse;

@ControllerAdvice
public class GlobalExceptions {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> otherExceptionHandler(Exception e, WebRequest request) {
		ErrorResponse error = new ErrorResponse(e.getMessage(), request.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_GATEWAY);
	}
}
