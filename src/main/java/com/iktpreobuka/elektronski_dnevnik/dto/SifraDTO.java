package com.iktpreobuka.elektronski_dnevnik.dto;

public class SifraDTO {
	
	private String email;
	private String sifra;
	private String novaSifra;
	
	public SifraDTO() {
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

	public String getNovaSifra() {
		return novaSifra;
	}

	public void setNovaSifra(String novaSifra) {
		this.novaSifra = novaSifra;
	}

}
