package com.iktpreobuka.elektronski_dnevnik.dto.responses;

import org.springframework.http.HttpStatus;

import com.iktpreobuka.elektronski_dnevnik.entities.OdeljenjeEntity;

public class OdeljenjeServiceResponse {

	protected HttpStatus httpResponseCode;
	protected String kod;
	protected String poruka;
	private OdeljenjeEntity odeljenje;

	public OdeljenjeServiceResponse() {
		super();
	}
	
	public HttpStatus getHttpResponseCode() {
		return httpResponseCode;
	}

	public void setHttpResponseCode(HttpStatus httpResponseCode) {
		this.httpResponseCode = httpResponseCode;
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

	public OdeljenjeEntity getOdeljenje() {
		return odeljenje;
	}

	public void setOdeljenje(OdeljenjeEntity odeljenje) {
		this.odeljenje = odeljenje;
	}
	
	public OdeljenjeServiceResponse(HttpStatus httpResponseCode, String poruka,
			OdeljenjeEntity odeljenje) {
		super();
		this.httpResponseCode = httpResponseCode;
		this.poruka = poruka;
		this.odeljenje = odeljenje;
	}
	
	public OdeljenjeServiceResponse(HttpStatus httpResponseCode, String kod, String poruka) {
		super();
		this.httpResponseCode = httpResponseCode;
		this.kod = kod;
		this.poruka = poruka;
	}
	
	public OdeljenjeServiceResponse(HttpStatus httpResponseCode, String poruka) {
		super();
		this.httpResponseCode = httpResponseCode;
		this.poruka = poruka;
	}

}
