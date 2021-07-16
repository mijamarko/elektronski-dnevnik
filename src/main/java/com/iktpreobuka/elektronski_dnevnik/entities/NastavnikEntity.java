package com.iktpreobuka.elektronski_dnevnik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.iktpreobuka.elektronski_dnevnik.entities.relationships.NastavnikPredajePredmet;

@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@Entity
public class NastavnikEntity extends KorisnikEntity{

	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "odeljenje")
	@JsonManagedReference(value = "razredni")
	protected OdeljenjeEntity odeljenje;
	
	@OneToMany(mappedBy = "nastavnik")
	private List<NastavnikPredajePredmet> predmetiKojePredaje = new ArrayList<NastavnikPredajePredmet>();
	
}
