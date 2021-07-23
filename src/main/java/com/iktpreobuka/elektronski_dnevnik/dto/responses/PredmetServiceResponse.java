package com.iktpreobuka.elektronski_dnevnik.dto.responses;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;

import com.iktpreobuka.elektronski_dnevnik.entities.PredmetEntity;

public class PredmetServiceResponse{
	
	protected HttpStatus httpResponseCode;
	protected String kod;
	protected String poruka;
	private ArrayList<PredmetEntity> predmeti = new ArrayList<PredmetEntity>();
	private PredmetEntity predmet;
	
	public PredmetServiceResponse() {
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

	public ArrayList<PredmetEntity> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(ArrayList<PredmetEntity> predmeti) {
		this.predmeti = predmeti;
	}
	
	public PredmetEntity getPredmet() {
		return predmet;
	}
	
	public void setPredmet(PredmetEntity predmet) {
		this.predmeti.add(predmet) ;
	}

	public PredmetServiceResponse(HttpStatus httpResponseCode, String poruka,
			ArrayList<PredmetEntity> predmeti) {
		super();
		this.httpResponseCode = httpResponseCode;
		this.poruka = poruka;
		predmeti.forEach(predmet -> {
			this.predmeti.add(predmet);
		});
	}
	
	public PredmetServiceResponse(HttpStatus httpResponseCode, String poruka,
			PredmetEntity predmet) {
		super();
		this.httpResponseCode = httpResponseCode;
		this.poruka = poruka;
		this.predmet = predmet;
	}
	
	public PredmetServiceResponse(HttpStatus httpResponseCode, String kod, String poruka) {
		super();
		this.httpResponseCode = httpResponseCode;
		this.kod = kod;
		this.poruka = poruka;
	}
	
	public PredmetServiceResponse(HttpStatus httpResponseCode, String poruka) {
		super();
		this.httpResponseCode = httpResponseCode;
		this.poruka = poruka;
	}

}
