package com.iktpreobuka.elektronski_dnevnik.dto.responses;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;

import com.iktpreobuka.elektronski_dnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.PredmetEntity;

public class NastavnikServiceResponse {
	
	private HttpStatus httpResponseCode;
	private String code;
	private String message;
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

	public NastavnikServiceResponse(HttpStatus httpResponseCode, String code, String message,
			ArrayList<PredmetEntity> predmeti, OdeljenjeEntity odeljenje) {
		super();
		this.httpResponseCode = httpResponseCode;
		this.code = code;
		this.message = message;
		this.odeljenje = odeljenje;
		predmeti.forEach(predmet -> {
			this.predmeti.add(predmet);
		});
	}
	
	public NastavnikServiceResponse(HttpStatus httpResponseCode, String code, String message,
			PredmetEntity predmet, OdeljenjeEntity odeljenje) {
		super();
		this.httpResponseCode = httpResponseCode;
		this.code = code;
		this.message = message;
		this.odeljenje = odeljenje;
		this.predmeti.add(predmet);
	}
	
	
}
