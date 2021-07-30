package com.iktpreobuka.elektronski_dnevnik.dto;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.elektronski_dnevnik.security.Views;

@JsonView(Views.Ucenik.class)
public class ServiceResponse {
	
	private String kod;
	private String poruka;
	private HttpStatus httpStatus;
	private Object value;
	
	public ServiceResponse() {
		super();
	}

	public String getKod() {
		return kod;
	}

	public void setKod(String kod) {
		this.kod = kod;
	}

	public String getPoruka() {
		return poruka;
	}

	public void setPoruka(String poruka) {
		this.poruka = poruka;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public ServiceResponse(String kod, String poruka, HttpStatus httpStatus) {
		super();
		this.kod = kod;
		this.poruka = poruka;
		this.httpStatus = httpStatus;
	}

	public ServiceResponse(String kod, String poruka, HttpStatus httpStatus, Object obj) {
		super();
		this.kod = kod;
		this.poruka = poruka;
		this.httpStatus = httpStatus;
		this.value = obj;
	}

	public ServiceResponse(String poruka, HttpStatus httpStatus) {
		super();
		this.poruka = poruka;
		this.httpStatus = httpStatus;
	}

	public ServiceResponse(String poruka, HttpStatus httpStatus, Object obj) {
		super();
		this.poruka = poruka;
		this.httpStatus = httpStatus;
		this.value = obj;
	}
	
	
	

}
