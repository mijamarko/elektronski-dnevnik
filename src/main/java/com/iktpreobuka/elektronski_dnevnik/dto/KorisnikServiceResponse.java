package com.iktpreobuka.elektronski_dnevnik.dto;

import org.springframework.http.HttpStatus;

import com.iktpreobuka.elektronski_dnevnik.entities.KorisnikEntity;

public class KorisnikServiceResponse {
	
	private HttpStatus httpResponseCode;
	private String code;
	private String message;
	private KorisnikEntity korisnik;
	public KorisnikServiceResponse() {
		super();
		}
	public HttpStatus getHttpResponseCode() {
		return httpResponseCode;
	}
	public void setHttpResponseCode(HttpStatus httpResponseCode) {
		this.httpResponseCode = httpResponseCode;
	}
	public String getCode() {
		return code;
	}
	public void setCode (String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public KorisnikEntity getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(KorisnikEntity korisnik) {
		this.korisnik = korisnik;
	}
	public KorisnikServiceResponse(HttpStatus httpResponseCode, String code, String message, KorisnikEntity korisnik) {
		super();
		this.httpResponseCode = httpResponseCode;
		this.code = code;
		this.message = message;
		this.korisnik = korisnik;
	}
	

}
