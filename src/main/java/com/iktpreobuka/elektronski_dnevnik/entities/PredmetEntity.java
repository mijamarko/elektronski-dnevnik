package com.iktpreobuka.elektronski_dnevnik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	
	private String nazivPredmeta;
	
	@ManyToOne
	private RazredEntity razredUKomSePredaje;
	
	@OneToMany(mappedBy = "predmet")
	private List<NastavnikPredajePredmet> predavaci = new ArrayList<NastavnikPredajePredmet>();
	
	@OneToMany
	private List<OcenaEntity> ocene = new ArrayList<OcenaEntity>();

	public PredmetEntity() {
		super();
		// TODO Auto-generated constructor stub
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

	public RazredEntity getRazredUKomSePredaje() {
		return razredUKomSePredaje;
	}

	public void setRazredUKomSePredaje(RazredEntity razredUKomSePredaje) {
		this.razredUKomSePredaje = razredUKomSePredaje;
	}

	public List<NastavnikPredajePredmet> getPredavaci() {
		return predavaci;
	}

	public void setPredavaci(List<NastavnikPredajePredmet> predavaci) {
		this.predavaci = predavaci;
	}

	@Override
	public String toString() {
		return "PredmetEntity [id=" + id + ", nazivPredmeta=" + nazivPredmeta + ", razredUKomSePredaje="
				+ razredUKomSePredaje + ", predavaci=" + predavaci + "]";
	}
	
	

}
