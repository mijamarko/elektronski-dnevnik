package com.iktpreobuka.elektronski_dnevnik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.iktpreobuka.elektronski_dnevnik.entities.relationships.NastavnikPredajePredmet;

@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@Entity
@Table(name = "nastavnici")
@DiscriminatorValue("nastavnik")
public class NastavnikEntity extends KorisnikEntity{
	

	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "odeljenje_id", referencedColumnName = "odeljenje_id")
	@JsonManagedReference
	private OdeljenjeEntity odeljenjeKomJeRazredni;
	
	@OneToMany(mappedBy = "nastavnik")
	private List<NastavnikPredajePredmet> predmetiKojePredaje = new ArrayList<NastavnikPredajePredmet>();

	public NastavnikEntity() {
		super();
	}

	public OdeljenjeEntity getOdeljenjeKomJeRazredni() {
		return odeljenjeKomJeRazredni;
	}


	public void setOdeljenjeKomJeRazredni(OdeljenjeEntity odeljenjeKomJeRazredni) {
		this.odeljenjeKomJeRazredni = odeljenjeKomJeRazredni;
	}


	public OdeljenjeEntity getOdeljenje() {
		return odeljenjeKomJeRazredni;
	}

	public void setOdeljenje(OdeljenjeEntity odeljenje) {
		this.odeljenjeKomJeRazredni = odeljenje;
	}

	public List<NastavnikPredajePredmet> getPredmetiKojePredaje() {
		return predmetiKojePredaje;
	}

	public void setPredmetiKojePredaje(List<NastavnikPredajePredmet> predmetiKojePredaje) {
		this.predmetiKojePredaje = predmetiKojePredaje;
	}
	
	
	
}
