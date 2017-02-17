package com.dazi.core.common.response;

public enum ResultVal {
	SUCCESS(0, "SUCCESS"),
	FAIL(-1, "FAIL"),
	
	//***********************-1 ~ -100 通用错误***********************
	/** -1, 系统异常  */
	SYSTEM_ERROR(-1, "系统异常"),
	CANNOT_READ_REQUEST(-2, "无法读取请求报文"),
	CANNOT_SERIALIZE(-3, "无法序列化对象"),
	PARAM_FORMAT_ERROR(-4, "输入参数格式错误"),
	ILLEGAL_PARAM(-5, "参数错误"),
	PARAM_IS_NULL(-6, "参数为空"),
	SYSTEM_DATABASE_EXCEPTION(-7, "数据库操作异常"),

	//***********************-101 ~ -200 支付接口错误***********************
	REST_PAYCENTER_EXCEPRION(-101, "请求支付中心异常"),
	REST_PAYCENTER_FAIL(-102, "请求支付中心失败"),
	PAY_ACTIVE_IS_NULL(-103, "活动已经过期"),
	PAY_ACTIVE_OUTDATE(-104, "活动已经过期"),
	PAY_CREATE_ORDER_FAIL(-105, "创建订单失败"),
	REPEAT_ORDER(-106, "重复提交订单"),
	;

	private int code;
	
	private String msg;
	
	
	ResultVal(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public boolean isSuccess() {
		if (this.code == ResultVal.SUCCESS.code) {
			return true;
		}
		return false;
	}
	
	public boolean isFail() {
		if (this.code != ResultVal.SUCCESS.code) {
			return true;
		}
		return false;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String toString() {
		return "{\"code\":" + this.code + ", \"msg\":\"" + msg + "\"}";
	}
	
}