package com.iktpreobuka.elektronski_dnevnik.dto.responses;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.iktpreobuka.elektronski_dnevnik.entities.IzostanakEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.OcenaEntity;
import com.iktpreobuka.elektronski_dnevnik.entities.OdeljenjeEntity;

public class UcenikServiceResponse {
	
	private HttpStatus httpResponseCode;
	private String kod;
	private String poruka;
	private ArrayList<OcenaEntity> ocene = new ArrayList<OcenaEntity>();
	private ArrayList<IzostanakEntity> izostanci = new ArrayList<IzostanakEntity>();
	private OdeljenjeEntity odeljenje;
	
	public UcenikServiceResponse() {
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

	public ArrayList<OcenaEntity> getOcene() {
		return ocene;
	}

	public void setOcene(ArrayList<OcenaEntity> ocene) {
		this.ocene = ocene;
	}

	public ArrayList<IzostanakEntity> getIzostanci() {
		return izostanci;
	}

	public void setIzostanci(ArrayList<IzostanakEntity> izostanci) {
		this.izostanci = izostanci;
	}

	public OdeljenjeEntity getOdeljenje() {
		return odeljenje;
	}

	public void setOdeljenje(OdeljenjeEntity odeljenje) {
		this.odeljenje = odeljenje;
	}

	public UcenikServiceResponse(HttpStatus httpResponseCode, String poruka, List<?> lista) {
		super();
		this.httpResponseCode = httpResponseCode;
		this.poruka = poruka;
		if(lista.get(0) instanceof OcenaEntity) {
			lista.forEach(o -> ocene.add((OcenaEntity) o));
		} else {
			lista.forEach(i -> izostanci.add((IzostanakEntity) i));
		}
	}
	
	public UcenikServiceResponse(HttpStatus httpResponseCode, String kod, String poruka, OdeljenjeEntity odeljenje) {
		super();
		this.httpResponseCode = httpResponseCode;
		this.kod = kod;
		this.poruka = poruka;
		this.odeljenje = odeljenje;
	}
	
	public UcenikServiceResponse(HttpStatus httpResponseCode, String kod, String poruka) {
		super();
		this.httpResponseCode = httpResponseCode;
		this.kod = kod;
		this.poruka = poruka;
	}
	
	public UcenikServiceResponse(HttpStatus httpResponseCode, String poruka) {
		super();
		this.httpResponseCode = httpResponseCode;
		this.poruka = poruka;
	}

}
