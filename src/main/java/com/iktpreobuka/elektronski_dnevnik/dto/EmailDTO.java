package com.iktpreobuka.elektronski_dnevnik.dto;

public class EmailDTO {
	
	private String email;
	private String noviEmail;
	private String sifra;
	
	public EmailDTO() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
