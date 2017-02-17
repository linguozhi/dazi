package com.dazi.core.common.response;

public class IllegalArgument extends GeneralSystemError {
	private static final long serialVersionUID = 6304001918253219987L;

	public IllegalArgument() {
		super(String.valueOf(ResultVal.ILLEGAL_PARAM.getCode()), ResultVal.ILLEGAL_PARAM.getMsg());
	}
	
	public IllegalArgument(String message) {
		super(String.valueOf(ResultVal.ILLEGAL_PARAM.getCode()), ResultVal.ILLEGAL_PARAM.getMsg() + "：" + message);
	}
	
	public IllegalArgument(ResultVal resultVal) {
		super(resultVal);
	}
}
