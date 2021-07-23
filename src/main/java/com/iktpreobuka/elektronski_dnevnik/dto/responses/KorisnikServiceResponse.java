package com.iktpreobuka.elektronski_dnevnik.dto.responses;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;

import com.iktpreobuka.elektronski_dnevnik.entities.KorisnikEntity;

public class KorisnikServiceResponse {
	
	protected HttpStatus httpResponseCode;
	protected String kod;
	protected String poruka;
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
	
	public ArrayList<KorisnikEntity> getKorisnici() {
		return korisnici;
	}
	
	public void setKorisnici(ArrayList<KorisnikEntity> korisnici) {
		this.korisnici = korisnici;
	}
	
	public void setKorisnici(KorisnikEntity korisnik) {
		this.korisnici.add(korisnik);
	}
	
	public KorisnikServiceResponse(HttpStatus httpResponseCode, String poruka, ArrayList<KorisnikEntity> korisnici) {
		super();
		this.httpResponseCode = httpResponseCode;
		this.poruka = poruka;
		korisnici.forEach((korisnik) -> {
			this.korisnici.add(korisnik);
		});
	}
	
	public KorisnikServiceResponse(HttpStatus httpResponseCode, String poruka, KorisnikEntity korisnik) {
		super();
		this.httpResponseCode = httpResponseCode;
		this.poruka = poruka;
		this.korisnici.add(korisnik);
	}
	
	public KorisnikServiceResponse(HttpStatus httpResponseCode, String kod, String poruka) {
		super();
		this.httpResponseCode = httpResponseCode;
		this.kod = kod;
		this.poruka = poruka;
	}
	
	public KorisnikServiceResponse(HttpStatus httpResponseCode, String poruka) {
		super();
		this.httpResponseCode = httpResponseCode;
		this.poruka = poruka;
	}
	

}
