package com.iktpreobuka.elektronski_dnevnik.dto;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;

import com.iktpreobuka.elektronski_dnevnik.entities.KorisnikEntity;

public class KorisnikServiceResponse {
	
	private HttpStatus httpResponseCode;
	private String code;
	private String message;
	private ArrayList<KorisnikEntity> korisnici = new ArrayList<KorisnikEntity>();
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
	public ArrayList<KorisnikEntity> getKorisnici() {
		return korisnici;
	}
	public void setKorisnici(ArrayList<KorisnikEntity> korisnici) {
		this.korisnici = korisnici;
	}
	public void setKorisnici(KorisnikEntity korisnik) {
		this.korisnici.add(korisnik);
	}
	
	public KorisnikServiceResponse(HttpStatus httpResponseCode, String code, String message, ArrayList<KorisnikEntity> korisnici) {
		super();
		this.httpResponseCode = httpResponseCode;
		this.code = code;
		this.message = message;
		korisnici.forEach((korisnik) -> {
			this.korisnici.add(korisnik);
		});
	}
	
	public KorisnikServiceResponse(HttpStatus httpResponseCode, String code, String message, KorisnikEntity korisnik) {
		super();
		this.httpResponseCode = httpResponseCode;
		this.code = code;
		this.message = message;
		this.korisnici.add(korisnik);
	}
	

}
