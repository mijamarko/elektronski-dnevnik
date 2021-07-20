package com.iktpreobuka.elektronski_dnevnik.dto;

public class UserChangePassDTO {
	
	private String email;
	private String staraSifra;
	private String novaSifra;
	public UserChangePassDTO() {
		super();
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStaraSifra() {
		return staraSifra;
	}
	public void setStaraSifra(String staraSifra) {
		this.staraSifra = staraSifra;
	}
	public String getNovaSifra() {
		return novaSifra;
	}
	public void setNovaSifra(String novaSifra) {
		this.novaSifra = novaSifra;
	}
	
	

}
