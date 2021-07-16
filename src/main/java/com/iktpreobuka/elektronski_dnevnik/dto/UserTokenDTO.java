package com.iktpreobuka.elektronski_dnevnik.dto;

public class UserTokenDTO {
	
	private String korisnickoIme;
	private String token;
	public UserTokenDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public UserTokenDTO(String korisnickoIme, String token) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.token = token;
	}


	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

}
