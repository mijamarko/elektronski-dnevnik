package com.iktpreobuka.elektronski_dnevnik.dto;

import javax.validation.constraints.Pattern;

import com.iktpreobuka.elektronski_dnevnik.enums.EIzostanak;
import com.iktpreobuka.elektronski_dnevnik.enums.EPolugodiste;

public class KorisnikDTO {
	
	private Integer korisnikId;
	private Integer roleId;
	private Integer nastavnikId;
	private Integer predmetId;
	private Integer odeljenjeId;
	private Integer ucenikId;
	private Integer roditeljId;
	private Integer izostanakId;
	
	private String roleName;
	private String predmetName;
	private String email;
	
	@Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$", message = "Niste uneli validnu email adresu.")
	private String noviEmail;
	
	private String sifra;
	
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",
			message = "Lozinka mora biti duzine izmedju {min} i {max} karaktera. Lozinka mora sadrzati barem jedno veliko slovo, barem jedno malo slovo,"
					+ " barem jedan broj, i barem jedan specijalni karakter.")
	private String novaSifra;
	
	private EPolugodiste polugodiste;
	private EIzostanak izostanak;

	public KorisnikDTO() {
		super();
	}

	public Integer getKorisnikId() {
		return korisnikId;
	}

	public void setKorisnikId(Integer korisnikId) {
		this.korisnikId = korisnikId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getNastavnikId() {
		return nastavnikId;
	}

	public void setNastavnikId(Integer nastavnikId) {
		this.nastavnikId = nastavnikId;
	}

	public Integer getPredmetId() {
		return predmetId;
	}

	public void setPredmetId(Integer predmetId) {
		this.predmetId = predmetId;
	}

	public Integer getOdeljenjeId() {
		return odeljenjeId;
	}

	public void setOdeljenjeId(Integer odeljenjeId) {
		this.odeljenjeId = odeljenjeId;
	}

	public Integer getUcenikId() {
		return ucenikId;
	}

	public void setUcenikId(Integer ucenikId) {
		this.ucenikId = ucenikId;
	}

	public Integer getRoditeljId() {
		return roditeljId;
	}

	public void setRoditeljId(Integer roditeljId) {
		this.roditeljId = roditeljId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getPredmetName() {
		return predmetName;
	}

	public void setPredmetName(String predmetName) {
		this.predmetName = predmetName;
	}

	public EPolugodiste getPolugodiste() {
		return polugodiste;
	}

	public void setPolugodiste(EPolugodiste polugodiste) {
		this.polugodiste = polugodiste;
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

	public String getNovaSifra() {
		return novaSifra;
	}

	public void setNovaSifra(String novaSifra) {
		this.novaSifra = novaSifra;
	}

	public Integer getIzostanakId() {
		return izostanakId;
	}

	public void setIzostanakId(Integer izostanakId) {
		this.izostanakId = izostanakId;
	}

	public EIzostanak getIzostanak() {
		return izostanak;
	}

	public void setIzostanak(EIzostanak izostanak) {
		this.izostanak = izostanak;
	}

	

}
