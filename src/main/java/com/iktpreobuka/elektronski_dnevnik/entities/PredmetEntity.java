package com.iktpreobuka.elektronski_dnevnik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iktpreobuka.elektronski_dnevnik.entities.relationships.NastavnikPredajePredmet;

@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })

@Entity
@Table(name = "predmeti")
public class PredmetEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	@Column(name = "predmet_id")
	private Integer id;
	
	@Column(name = "naziv_predmeta")
	@NotBlank(message = "Naziv predmeta ne sme biti prazan.")
	@Size(min = 5, message = "Naziv predmeta mora biti duzi od {min} karaktera.")
	private String nazivPredmeta;

	
	@OneToMany(mappedBy = "predmet")
	private List<NastavnikPredajePredmet> predavaci = new ArrayList<NastavnikPredajePredmet>();

	public PredmetEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNazivPredmeta() {
		return nazivPredmeta;
	}

	public void setNazivPredmeta(String nazivPredmeta) {
		this.nazivPredmeta = nazivPredmeta;
	}

	public List<NastavnikPredajePredmet> getPredavaci() {
		return predavaci;
	}

	public void setPredavaci(List<NastavnikPredajePredmet> predavaci) {
		this.predavaci = predavaci;
	}
	

}
