package com.movie.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.kalpesh.cinematheatre.response.Response;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Response customErrorDetails = new Response(LocalDateTime.now(), ex.getMessage(),
				HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Response customErrorDetails = new Response(LocalDateTime.now(), ex.getMessage(),
				HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<>(customErrorDetails, HttpStatus.METHOD_NOT_ALLOWED);

	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Response> handleNotFoundException(NotFoundException ex) {
		Response customErrorDetails = new Response(LocalDateTime.now(), ex.getLocalizedMessage(),
				HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(AlreadyRegisteredException.class)
	public ResponseEntity<Response> handleAlreadyRegisteredException(AlreadyRegisteredException ex) {
		Response customErrorDetails = new Response(LocalDateTime.now(), ex.getLocalizedMessage(),
				HttpStatus.ALREADY_REPORTED.value());

		return new ResponseEntity<>(customErrorDetails, HttpStatus.ALREADY_REPORTED);
	}

}