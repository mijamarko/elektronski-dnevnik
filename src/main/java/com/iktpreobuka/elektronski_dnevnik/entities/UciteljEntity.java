package com.iktpreobuka.elektronski_dnevnik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
@Entity
@Table(name = "ucitelji")
public class UciteljEntity extends ProsvetniRadnikEntity {
	
	@ManyToMany
	private List<PredmetEntity> predmetiKojePredaje = new ArrayList<PredmetEntity>();
	
	@OneToOne
	private OdeljenjeEntity odeljenjeKojemPredaje;
	
	@OneToMany
	private List<OcenaEntity> upisaneOcene = new ArrayList<OcenaEntity>();

	public UciteljEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<PredmetEntity> getPredmetiKojePredaje() {
		return predmetiKojePredaje;
	}

	public void setPredmetiKojePredaje(List<PredmetEntity> predmetiKojePredaje) {
		this.predmetiKojePredaje = predmetiKojePredaje;
	}

	public OdeljenjeEntity getOdeljenjeKojemPredaje() {
		return odeljenjeKojemPredaje;
	}

	public void setOdeljenjeKojemPredaje(OdeljenjeEntity odeljenjeKojemPredaje) {
		this.odeljenjeKojemPredaje = odeljenjeKojemPredaje;
	}

	public List<OcenaEntity> getUpisaneOcene() {
		return upisaneOcene;
	}

	public void setUpisaneOcene(List<OcenaEntity> upisaneOcene) {
		this.upisaneOcene = upisaneOcene;
	}

	@Override
	public String toString() {
		return "UciteljEntity [predmetiKojePredaje=" + predmetiKojePredaje + ", odeljenjeKojemPredaje="
				+ odeljenjeKojemPredaje + ", upisaneOcene=" + upisaneOcene + "]";
	}
	
	

}
