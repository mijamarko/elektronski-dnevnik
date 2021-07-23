package com.iktpreobuka.elektronski_dnevnik.dto.responses;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;

import com.iktpreobuka.elektronski_dnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.PredmetEntity;

public class NastavnikServiceResponse {
	
	protected HttpStatus httpResponseCode;
	protected String kod;
	protected String poruka;
	private ArrayList<PredmetEntity> predmeti = new ArrayList<PredmetEntity>();
	private OdeljenjeEntity odeljenje;
	
	public NastavnikServiceResponse() {
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
	
	public void setPredmeti(PredmetEntity predmet) {
		this.predmeti.add(predmet) ;
	}

	public OdeljenjeEntity getOdeljenje() {
		return odeljenje;
	}

	public void setOdeljenje(OdeljenjeEntity odeljenje) {
		this.odeljenje = odeljenje;
	}

	public NastavnikServiceResponse(HttpStatus httpResponseCode, String poruka,
			ArrayList<PredmetEntity> predmeti) {
		super();
		this.httpResponseCode = httpResponseCode;
		this.poruka = poruka;
		predmeti.forEach(predmet -> {
			this.predmeti.add(predmet);
		});
	}
	
	public NastavnikServiceResponse(HttpStatus httpResponseCode, String poruka,
			PredmetEntity predmet) {
		super();
		this.httpResponseCode = httpResponseCode;
		this.poruka = poruka;
		this.predmeti.add(predmet);
	}
	
	public NastavnikServiceResponse(HttpStatus httpResponseCode, String poruka,
			OdeljenjeEntity odeljenje) {
		super();
		this.httpResponseCode = httpResponseCode;
		this.poruka = poruka;
		this.odeljenje = odeljenje;
	}	
	
	public NastavnikServiceResponse(HttpStatus httpResponseCode, String kod, String poruka) {
		super();
		this.httpResponseCode = httpResponseCode;
		this.kod = kod;
		this.poruka = poruka;
	}
	
	public NastavnikServiceResponse(HttpStatus httpResponseCode, String poruka) {
		super();
		this.httpResponseCode = httpResponseCode;
		this.poruka = poruka;
	}
	
}
