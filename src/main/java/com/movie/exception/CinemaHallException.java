package com.movie.exception;

public class CinemaHallException extends Exception {
	private static final long serialVersionUID = 1L;
	private int statusCode;
	private String message;

	public CinemaHallException(String message) {
		super(message);
		this.message = message;
	}

	public CinemaHallException(String message, int status) {
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