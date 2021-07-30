package com.iktpreobuka.elektronski_dnevnik.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.elektronski_dnevnik.security.Views;

@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@Entity
@Table(name = "korisnici")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tip_korisnika", discriminatorType = DiscriminatorType.STRING)
public class KorisnikEntity {	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	@JsonView(Views.Admin.class)
	private Integer id;

	@NotBlank(message = "Korisnicko ime ne moze biti prazno.")
	@Column(name = "korisnicko_ime")
	@Size(min = 5, max = 25, message = "Korisnicko ime mora biti duzine izmedju {min} i {max} karaktera.")
	@JsonView(Views.Ucenik.class)
	private String korisnickoIme;

	@NotBlank(message = "Lozinka ne moze biti prazna.")
	@Size(min = 8, max = 100, message = "Lozinka mora biti duzine izmedju {min} i {max} karaktera.")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,100}$",
			message = "Sifra mora imati najmanje jedan lowercase karakter, jedan uppercase karakter, jedan broj, i jedan specijalni karakter.")
	@JsonView(Views.Admin.class)
	private String sifra;

	@NotBlank(message = "Ime ne moze biti prazno.")
	@Size(min = 3, max = 15, message = "Ime mora biti duzine izmedju {min} i {max} karaktera.")
	@JsonView(Views.Ucenik.class)
	private String ime;

	@NotBlank(message = "Prezime ne moze biti prazno.")
	@Size(min = 3, max = 15, message = "Prezime mora biti duzine izmedju {min} i {max} karaktera.")
	@JsonView(Views.Ucenik.class)
	private String prezime;

	@NotBlank(message = "Email polje ne moze biti prazno.")
	@Pattern(regexp = "^[a-zA-Z0-9_!#$%&*+/=?{}~^.-]+@[a-zA-Z0-9.-]+$")
	@JsonView(Views.Ucenik.class)
	private String email;

	@Version
	@JsonView(Views.Admin.class)
	private Integer version;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "role")
	@JsonView(Views.Admin.class)
	private RoleEntity role;

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	@JsonIgnore
	public String getSifra() {
		return sifra;
	}
	@JsonProperty
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}
	
	public Integer getId() {
		return id;
	}
	
}
