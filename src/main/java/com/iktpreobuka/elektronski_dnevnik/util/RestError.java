package com.iktpreobuka.elektronski_dnevnik.util;

public class RestError {
	
	private String code;
	private String message;
	
	public RestError() {
		super();
	}
	public RestError(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
