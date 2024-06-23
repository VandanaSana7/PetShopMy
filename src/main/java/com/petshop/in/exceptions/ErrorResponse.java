package com.petshop.in.exceptions;

import java.time.LocalDate;

public class ErrorResponse {
	
	private String message;
	private String status;
	private LocalDate timeStamp;
	
	public ErrorResponse(String message, String status, LocalDate timeStamp) {
		super();
		this.message = message;
		this.status = status;
		this.timeStamp = timeStamp;
	}
	
	public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public LocalDate getTimeStamp() {
		return timeStamp;
	}
	
	public void setTimeStamp(LocalDate timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	

}
