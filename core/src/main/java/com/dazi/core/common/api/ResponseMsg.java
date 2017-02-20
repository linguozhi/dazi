/*
 * Copyright (c) 2004-2013 by UCweb All rights reserved
 */
package com.dazi.core.common.api;

import java.util.Map;

/**
 * @description 响应信息
 * @author:guice_lin@126.com
 * @date: 2016/3/1
 */
public class ResponseMsg {
	
	/** 
	 * id 
	 */  
	private long id;
	
	/** 
	 * state
	 */  
	private Map<String, String> state;
	
	/** 
	 * data 
	 */  
	private Map<String, String> data;

	/** 
	 * get id 
	 * 
	 * @return id id 
	 */
	public long getId() {
		return id;
	}

	/** 
	 * set id 
	 * 
	 * @param id id 
	 */
	public void setId(long id) {
		this.id = id;
	}

	/** 
	 * get state 
	 * 
	 * @return state state 
	 */
	public Map<String, String> getState() {
		return state;
	}

	/** 
	 * set state 
	 * 
	 * @param state state 
	 */
	public void setState(Map<String, String> state) {
		this.state = state;
	}

	/** 
	 * get data 
	 * 
	 * @return data data 
	 */
	public Map<String, String> getData() {
		return data;
	}

	/** 
	 * set data 
	 * 
	 * @param data data 
	 */
	public void setData(Map<String, String> data) {
		this.data = data;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ResponseMsg [id=" + id + ", state=" + state + ", data=" + data + "]";
	}

	public boolean isSuccess() {
		return this.state != null && "2000000".equals(this.state.get("code"));
	}
}
