package com.iktpreobuka.elektronski_dnevnik.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.elektronski_dnevnik.enums.EIzostanak;
import com.iktpreobuka.elektronski_dnevnik.security.Views;

@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })

@Entity
@Table(name = "izostanci")
public class IzostanakEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.Admin.class)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	@JsonView(Views.Nastavnik.class)
	private EIzostanak tipIzostanka;
	
	@JsonFormat(shape = Shape.STRING,
			pattern = "dd-MM-yyyy")
	@PastOrPresent
	@JsonView(Views.Nastavnik.class)
	private Date datumIzostanka;
	
	@JsonFormat(shape = Shape.STRING,
			pattern = "dd-MM-yyyy")
	@PastOrPresent
	@JsonView(Views.Nastavnik.class)
	private Date datumUpisivanja;
	
	@JsonFormat(shape = Shape.STRING,
			pattern = "dd-MM-yyyy")
	@PastOrPresent
	@JsonView(Views.Nastavnik.class)
	private Date datumIzmene;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "ucenikKojiJeIzostao")
	@JsonBackReference(value = "izostanci")
	@JsonView(Views.Nastavnik.class)
	private UcenikEntity ucenikKojiJeIzostao;

	public IzostanakEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EIzostanak getTipIzostanka() {
		return tipIzostanka;
	}

	public void setTipIzostanka(EIzostanak tipIzostanka) {
		this.tipIzostanka = tipIzostanka;
	}

	public Date getDatumIzostanka() {
		return datumIzostanka;
	}

	public void setDatumIzostanka(Date datumIzostanka) {
		this.datumIzostanka = datumIzostanka;
	}

	public UcenikEntity getUcenikKojiJeIzostao() {
		return ucenikKojiJeIzostao;
	}

	public void setUcenikKojiJeIzostao(UcenikEntity ucenikKojiJeIzostao) {
		this.ucenikKojiJeIzostao = ucenikKojiJeIzostao;
	}

	public Date getDatumUpisivanja() {
		return datumUpisivanja;
	}

	public void setDatumUpisivanja(Date datumUpisivanja) {
		this.datumUpisivanja = datumUpisivanja;
	}

	public Date getDatumIzmene() {
		return datumIzmene;
	}

	public void setDatumIzmene(Date datumIzmene) {
		this.datumIzmene = datumIzmene;
	}
	
	
	
	

}
