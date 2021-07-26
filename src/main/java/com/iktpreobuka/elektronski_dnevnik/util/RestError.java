package com.iktpreobuka.elektronski_dnevnik.util;

public class RestError {
	
	private String kod;
	private String poruka;
	
	public RestError() {
		super();
	}
	public RestError(String kod, String poruka) {
		super();
		this.kod = kod;
		this.poruka = poruka;
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
	
	

}
