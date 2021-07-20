package com.iktpreobuka.elektronski_dnevnik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "korisnici")
public class KorisnikEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Integer id;
	
	@NotBlank(message = "Korisnicko ime ne moze biti prazno.")
	@Column(name = "korisnicko_ime")
	@Size(min = 5, max = 25, message = "Korisnicko ime mora biti duzine izmedju {min} i {max} karaktera.")
	private String korisnickoIme;
	
	@JsonIgnore
	@NotBlank(message = "Lozinka ne moze biti prazna.")
	@Size(min = 8, max = 20, message = "Lozinka mora biti duzine izmedju {min} i {max} karaktera.")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",
	message = "Lozinka mora biti duzine izmedju {min} i {max} karaktera. Lozinka mora sadrzati barem jedno veliko slovo, barem jedno malo slovo,"
			+ " barem jedan broj, i barem jedan specijalni karakter.")
	private String sifra;
	
	@NotBlank(message = "Ime ne moze biti prazno.")
	@Size(min = 3, max = 15, message = "Ime mora biti duzine izmedju {min} i {max} karaktera.")
	private String ime;
	
	@NotBlank(message = "Prezime ne moze biti prazno.")
	@Size(min = 3, max = 15, message = "Prezime mora biti duzine izmedju {min} i {max} karaktera.")
	private String prezime;
	
	@NotBlank(message = "Email polje ne moze biti prazno.")
	@Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$", message = "Niste uneli validnu email adresu.")
	private String email;
	
	@Version
	private Integer version;

	@ManyToMany(mappedBy = "users")
	@JsonBackReference(value = "user_roles")
	private List<RoleEntity> roles = new ArrayList<RoleEntity>();

	public KorisnikEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getSifra() {
		return sifra;
	}

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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public List<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}
	
	public void addRole(RoleEntity role) {
		this.roles.add(role);
	}

	

}
