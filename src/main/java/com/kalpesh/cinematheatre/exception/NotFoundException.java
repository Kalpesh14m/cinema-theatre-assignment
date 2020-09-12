package com.kalpesh.cinematheatre.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private int statusCode;
	private String message;

	public NotFoundException(String message) {
		super(message);
		this.message = message;
	}

	public NotFoundException(String message, int status) {
		super(message);
		this.message = message;
		this.statusCode = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setStatus(int status) {
		this.statusCode = status;
	}

	public int getStatus() {
		return statusCode;
	}

}