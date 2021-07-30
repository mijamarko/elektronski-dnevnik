package com.iktpreobuka.elektronski_dnevnik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.elektronski_dnevnik.security.Views;

@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@Entity
@Table(name = "nastavnici")
@DiscriminatorValue("nastavnik")
public class NastavnikEntity extends KorisnikEntity{
	

	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "odeljenje_id", referencedColumnName = "odeljenje_id")
	@JsonManagedReference
	@JsonView(Views.Nastavnik.class)
	private OdeljenjeEntity odeljenjeKomJeRazredni;
	
	@ManyToMany(mappedBy = "predavaci")
	@JsonView(Views.Nastavnik.class)
	private List<PredmetEntity> predmetiKojePredaje = new ArrayList<PredmetEntity>();
	
	@OneToMany(mappedBy = "nastavnikKojiJeDaoOcenu")
	@JsonManagedReference(value = "nastavnik_ocena")
	@JsonView(Views.Nastavnik.class)
	private List<OcenaEntity> dodeljeneOcene = new ArrayList<OcenaEntity>();

	public NastavnikEntity() {
		super();
	}

	public OdeljenjeEntity getOdeljenjeKomJeRazredni() {
		return odeljenjeKomJeRazredni;
	}


	public void setOdeljenjeKomJeRazredni(OdeljenjeEntity odeljenjeKomJeRazredni) {
		this.odeljenjeKomJeRazredni = odeljenjeKomJeRazredni;
	}

	public List<PredmetEntity> getPredmetiKojePredaje() {
		return predmetiKojePredaje;
	}

	public void setPredmetiKojePredaje(List<PredmetEntity> predmetiKojePredaje) {
		this.predmetiKojePredaje = predmetiKojePredaje;
	}

	public List<OcenaEntity> getDodeljeneOcene() {
		return dodeljeneOcene;
	}

	public void setDodeljeneOcene(List<OcenaEntity> dodeljeneOcene) {
		this.dodeljeneOcene = dodeljeneOcene;
	}
	
	
	
	
}
