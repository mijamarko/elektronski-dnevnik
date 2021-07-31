package com.iktpreobuka.elektronski_dnevnik.util;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.elektronski_dnevnik.security.Views;

@JsonView(Views.Ucenik.class)
public class RestError {
	

	private String kod;

	private String poruka;
	
	public RestError() {
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
	
	public RestError(String kod, String poruka) {
		super();
		this.kod = kod;
		this.poruka = poruka;
	}

}
