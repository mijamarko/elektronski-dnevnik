package com.iktpreobuka.elektronski_dnevnik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })

@Entity
@Table(name = "nastavnici")
public class NastavnikEntity extends ProsvetniRadnikEntity {
	
	@ManyToMany
	private List<PredmetEntity> predmetiKojePredaje = new ArrayList<PredmetEntity>();
	
	@OneToMany
	private List<OcenaEntity> upisaneOcene = new ArrayList<OcenaEntity>();

	public NastavnikEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<PredmetEntity> getPredmetiKojePredaje() {
		return predmetiKojePredaje;
	}

	public void setPredmetiKojePredaje(List<PredmetEntity> predmetiKojePredaje) {
		this.predmetiKojePredaje = predmetiKojePredaje;
	}

	public List<OcenaEntity> getUpisaneOcene() {
		return upisaneOcene;
	}

	public void setUpisaneOcene(List<OcenaEntity> upisaneOcene) {
		this.upisaneOcene = upisaneOcene;
	}

	@Override
	public String toString() {
		return "NastavnikEntity [predmetiKojePredaje=" + predmetiKojePredaje + ", upisaneOcene=" + upisaneOcene + "]";
	}
	
	//TODO povezati sa ocenaentity
	
	
}
