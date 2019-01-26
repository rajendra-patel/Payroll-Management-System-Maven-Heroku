package com.management.exceptions;

public class AppException extends Exception {

	private static final long serialVersionUID = 1L;
	
	Integer status;

	public AppException() { }
	
	public AppException(int status, String message) {
		super(message);
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}					
}