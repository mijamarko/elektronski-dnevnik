package com.iktpreobuka.elektronski_dnevnik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.elektronski_dnevnik.security.Views;

@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@Entity
@Table(name = "odeljenja")
public class OdeljenjeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "odeljenje_id")
	@JsonView(Views.Admin.class)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JsonBackReference(value = "odeljenja")
	@JoinColumn(name = "razred_id", nullable = false)
	@JsonView(Views.Nastavnik.class)
	private RazredEntity razred;
	
	@Min(value = 1, message = "Odeljenje ne moze biti nulto.")
	@JsonView(Views.Nastavnik.class)
	private Integer brojOdeljenja;
	
	@OneToMany(mappedBy = "odeljenjeKojePohadja", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JsonManagedReference(value = "odeljenjeKojePohadja")
	@JsonView(Views.Nastavnik.class)
	private List<UcenikEntity> ucenici = new ArrayList<UcenikEntity>();
	
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "odeljenjeKomJeRazredni")
	@JsonBackReference
	@JsonView(Views.Ucenik.class)
	private NastavnikEntity razredniStaresina;
	
	@ManyToMany(mappedBy = "odeljenjaKojaSlusajuPredmet")
	@JsonView(Views.Ucenik.class)
	private List<PredmetEntity> predmetiKojeOdeljenjeSlusa = new ArrayList<PredmetEntity>();
	
	@Version
	private Integer version;

	public OdeljenjeEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RazredEntity getRazred() {
		return razred;
	}

	public void setRazred(RazredEntity razred) {
		this.razred = razred;
	}

	public Integer getBrojOdeljenja() {
		return brojOdeljenja;
	}

	public void setBrojOdeljenja(Integer brojOdeljenja) {
		this.brojOdeljenja = brojOdeljenja;
	}

	public List<UcenikEntity> getUcenici() {
		return ucenici;
	}

	public void setUcenici(List<UcenikEntity> ucenici) {
		this.ucenici = ucenici;
	}

	public NastavnikEntity getRazredniStaresina() {
		return razredniStaresina;
	}

	public void setRazredniStaresina(NastavnikEntity razredniStaresina) {
		this.razredniStaresina = razredniStaresina;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public List<PredmetEntity> getPredmetiKojeOdeljenjeSlusa() {
		return predmetiKojeOdeljenjeSlusa;
	}

	public void setPredmetiKojeOdeljenjeSlusa(List<PredmetEntity> predmetiKojeOdeljenjeSlusa) {
		this.predmetiKojeOdeljenjeSlusa = predmetiKojeOdeljenjeSlusa;
	}
	
	

}
