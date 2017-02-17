package com.dazi.core.common.response;

/**
 * 对外接口通用返回对象头
 *
 * @version 1.0.0, 2016年2月29日, Gaven
 */
public class ResponseHead {
	private String result;
	private String message;

	public ResponseHead() {
	}
	
	public ResponseHead(ResultVal resultVal) {
		this.result = String.valueOf(resultVal.getCode());
		this.message = resultVal.getMsg();
	}
	
	public ResponseHead(ResultVal resultVal, String message) {
		this.result = String.valueOf(resultVal.getCode());
		this.message = resultVal.getMsg() + ". " + message;
	}

	public ResponseHead(String result, String message) {
		this.result = result;
		this.message = message;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
