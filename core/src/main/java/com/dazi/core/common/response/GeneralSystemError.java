package com.dazi.core.common.response;

public class GeneralSystemError extends RuntimeException {
	private static final long serialVersionUID = -1219400991991407417L;

	private String code;

	public GeneralSystemError() {
		super("系统异常");
		this.code = "SYSTEM_ERROR";
	}
	
	public GeneralSystemError(String message) {
		super(message);
		this.code = "SYSTEM_ERROR";
	}
	
	public GeneralSystemError(String code, String message) {
		super(message);
		this.code = code;
	}

	public GeneralSystemError(ResultVal resultVal) {
		super(resultVal.getMsg());
		this.code = String.valueOf(resultVal.getCode());
	}
	
	public String getCode() {
		return this.code;
	}
}
