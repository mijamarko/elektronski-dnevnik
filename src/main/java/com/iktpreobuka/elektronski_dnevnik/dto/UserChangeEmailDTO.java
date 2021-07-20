package com.iktpreobuka.elektronski_dnevnik.dto;

public class UserChangeEmailDTO {
	
	private String stariEmail;
	private String noviEmail;
	private String sifra;
	public UserChangeEmailDTO() {
		super();
	}
	public String getStariEmail() {
		return stariEmail;
	}
	public void setStariEmail(String stariEmail) {
		this.stariEmail = stariEmail;
	}
	public String getNoviEmail() {
		return noviEmail;
	}
	public void setNoviEmail(String noviEmail) {
		this.noviEmail = noviEmail;
	}
	public String getSifra() {
		return sifra;
	}
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}	

}
