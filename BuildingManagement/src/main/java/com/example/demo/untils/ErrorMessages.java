package com.example.demo.untils;

import java.util.Date;

public class ErrorMessages {
	private Date timestamp;
	private String message;

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorMessages(Date timestamp, String message) {
		super();
		this.timestamp = timestamp;
		this.message = message;
	}

	public ErrorMessages() {
		super();
	}

}
