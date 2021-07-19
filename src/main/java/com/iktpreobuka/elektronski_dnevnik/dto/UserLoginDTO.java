package com.iktpreobuka.elektronski_dnevnik.dto;

public class UserLoginDTO {
	
	private String email;
	private String sifra;
	public UserLoginDTO() {
		super();
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSifra() {
		return sifra;
	}
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
	
	

}
