package com.dazi.core.common.response;

public class ErrorResponse extends GeneralResponse {
	private static final String result = "SYSTEM_ERROR";
	private static final String message = "系统异常";

	public ErrorResponse() {
		this.setHead(new ResponseHead(result, message));
	}
	
	public ErrorResponse(String message) {
		this.setHead(new ResponseHead(result, message));
	}
	
	public ErrorResponse(String result, String message) {
		this.setHead(new ResponseHead(result, message));
	}
	
	public ErrorResponse(ResultVal resultVal) {
		this.setHead(new ResponseHead(resultVal));
	}
	
	public ErrorResponse(ResultVal resultVal, String message) {
		this.setHead(new ResponseHead(resultVal, message));
	}
}
