package com.sqlite.model;

import java.util.HashMap;
import java.util.Map;

public class InspectResult {
	
	private boolean isGood;
	
	private String msg;
	private String statusCode;
	private Map<String, Object> detail;

	public String getStatusCode() {
		return statusCode;
	}
	
	public void addDetail(String key, Object val) {
		if(detail == null) {
			detail = new HashMap<>();
		} 
		
		detail.put(key, val);
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isGood() {
		return isGood;
	}

	public void setGood(boolean isGood) {
		this.isGood = isGood;
	}

}
