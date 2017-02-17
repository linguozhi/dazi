package com.dazi.core.common.response;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 对外接口通用返回对象，用于序列化成JSON、XML等
 *
 * @version 1.0.0, 2016年2月29日, Gaven
 */
public class GeneralResponse {
	private ResponseHead head;
	private Object body;

	public GeneralResponse() {
	}

	public GeneralResponse(ResponseHead head) {
		this.head = head;
	}
	
	public GeneralResponse(ResponseHead head, Object body) {
		this.head = head;
		this.body = body;
	}
	
	public GeneralResponse(ResultVal resultVal) {
		this.head = new ResponseHead();
		this.head.setResult(resultVal.getCode() + "");
		String randomCode = "";
		if(resultVal.getCode() != ResultVal.SUCCESS.getCode()) {
			randomCode = "[" + RandomStringUtils.randomAlphanumeric(6) + "]";
		}
		this.head.setMessage(resultVal.getMsg() + randomCode);
	}
	
	public ResponseHead getHead() {
		return head;
	}

	public void setHead(ResponseHead head) {
		this.head = head;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}
}
